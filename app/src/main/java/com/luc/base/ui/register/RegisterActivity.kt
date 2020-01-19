package com.luc.base.ui.register

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import com.luc.base.R
import com.luc.base.core.control.ActivityController
import com.luc.base.core.helper.Common
import com.luc.base.database.entity.User
import com.luc.base.databinding.ActivityRegisterBinding
import com.luc.base.helper.ProfileHelper
import com.luc.base.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterActivity : AppCompatActivity() {

    private lateinit var bind: ActivityRegisterBinding
    private val vm: RegisterVm by viewModel()
    private val helper = ProfileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_register)
        bind.vm = vm
        bind.bProceed.setOnClickListener {
            showProgress()
            vm.register(
                { hideProgress() },
                { hideProgress() },
                {
                    hideProgress()
                    enableLoginState()
                }
            )
        }
        bind.rbMale.setOnClickListener { vm.setGender(User.GENDER_MALE) }
        bind.rbFemale.setOnClickListener { vm.setGender(User.GENDER_FEMALE) }
        bind.etDob.setOnClickListener {
            helper.pickDate(this, vm.getMinAgeForDatePicker()) {
                vm.setDob(it)
                bind.etDob.setText(vm.data.get()?.getFormattedBirthDate())
            }
        }
        bind.bLogin.setOnClickListener { ActivityController.navigateTo(this, LoginActivity::class.java) }
        setSupportActionBar(bind.toolbar.toolbar)
    }

    private fun enableLoginState() {
        bind.lForm.alpha = 0.5f
        bind.lForm.forEach { it.isEnabled = false }
        bind.bLogin.visibility = View.VISIBLE
    }

    private fun showProgress() = Common.showProgressDialog(this, R.string.message_loading)

    private fun hideProgress() = Common.dismissProgressDialog()

}