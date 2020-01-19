package com.luc.base.core.base

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.luc.base.R
import com.luc.base.core.Constant
import com.luc.base.core.helper.Common
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var grantResult: (Int, PermissionResult) -> Unit


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun showProgress() {
        Common.showProgressDialog(this, R.string.message_loading)
    }

    fun hideProgress() {
        Common.dismissProgressDialog()
    }

    fun requestForPermissions(permissions: Array<out String>, requestCode: Int, grantResults: (Int, PermissionResult) -> Unit) {
        grantResult = grantResults

        if (permissions.all { convertToPermissionResult(it) == PermissionResult.GRANTED })
            grantResults.invoke(requestCode, PermissionResult.GRANTED)
        else {
            run breaker@{
                permissions.forEach {
                    if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            this.requestPermissions(permissions, requestCode)
                        return@breaker
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constant.REQUEST_LOCATION -> {
                var permissionResult: PermissionResult = PermissionResult.GRANTED
                run breaker@{
                    permissions.forEach {
                        permissionResult = convertToPermissionResult(it)
                        if (permissionResult != PermissionResult.GRANTED) {
                            return@breaker
                        }
                    }
                }
                grantResult(requestCode, permissionResult)

            }
        }
    }

    fun Activity.convertToPermissionResult(permission: String): PermissionResult {
        return if ((ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED)) {
            PermissionResult.GRANTED
        } else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            PermissionResult.DO_NOT_ASK_AGAIN
        } else {
            PermissionResult.NOT_GRANTED
        }
    }
}

enum class PermissionResult {
    GRANTED,
    NOT_GRANTED,
    DO_NOT_ASK_AGAIN
}