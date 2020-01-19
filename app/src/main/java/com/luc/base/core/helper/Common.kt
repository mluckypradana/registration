package com.luc.base.core.helper

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.luc.base.BuildConfig
import com.luc.base.R
import com.luc.base.core.extension.getAppContext
import com.luc.base.core.extension.getString


/**
 * Created by Alvin Rusli on 1/24/2016.
 *
 * A class that handles basic universal methods.
 */
object Common {

    /** The loading progress dialog object */
    @SuppressLint("StaticFieldLeak")
    private var progressDialog: ProgressDialog? = null
    const val MY_PERMISSIONS_REQUEST_LOCATION = 99

    fun checkLocationPermission(activity: Activity): Boolean {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Izinkan akses Lokasi")
                builder.setMessage("akses lokasi dibutuhkan untuk keperluan produk")
                builder.setPositiveButton(R.string.action_ok) { dialog, which ->
                    run {
                        ActivityCompat.requestPermissions(
                            activity,
                            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                            MY_PERMISSIONS_REQUEST_LOCATION
                        )
                    }
                }
                builder.setNegativeButton(R.string.action_cancel) { dialog, which -> dialog.cancel() }
                val dialog = builder.show()
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(activity, R.color.colorAccent))

            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
            return false
        } else {
            return true
        }
    }


    fun isProd(): Boolean {
        return BuildConfig.FLAVOR == "prod"
    }

    /**
     * Shows a loading progress dialog.
     * @param context the context
     * @param stringRes the dialog message string resource id
     * @param onBackPressListener the back button press listener when loading is shown
     */
    fun showProgressDialog(
        context: Context?,
        stringRes: Int = -1,
        onBackPressListener: ProgressDialog.OnBackPressListener? = null,
        cancelable: Boolean? = false
    ) {
        dismissProgressDialog()
        if (context == null) return
        progressDialog = ProgressDialog(context)
        progressDialog!!.setText(stringRes)
        if (cancelable != null)
            progressDialog!!.setCancelable(cancelable)
        progressDialog!!.backPressListener = onBackPressListener
        if (context is Activity && !context.isFinishing) progressDialog!!.show()
    }

    /** Hides the currently shown loading progress dialog */
    fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    /**
     * Sets the progress dialog progress in percent.
     * @param progress The loading progress in percent
     */
    fun setProgressDialogProgress(progress: Int) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.setProgress(progress)
            progressDialog!!.setText(progress.toString() + "%")
        }
    }

    /**
     * Sets the progress dialog progress indeterminate state.
     * @param isIndeterminate Determines if progress dialog is indeterminate
     */
    fun setProgressDialogIndeterminate(isIndeterminate: Boolean) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.setIndeterminate(isIndeterminate)
        }
    }

    /**
     * Sets the progress dialog message.
     * @param message The dialog message string
     */
    fun setProgressDialogText(message: String) {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.setText(message)
        }
    }

    /**
     * Display a simple [Toast].
     * @param stringRes The message string resource id
     */
    fun showToast(stringRes: Int) {
        showToast(getString(stringRes))
    }

    /**
     * Display a simple [Toast].
     * @param message The message string
     */
    fun showToast(message: String?) {
        dismissProgressDialog()
        message?.let {
            val toast = Toast.makeText(getAppContext(), message, Toast.LENGTH_SHORT)
            val v = toast.view.findViewById(android.R.id.message) as TextView?
            if (v != null) v.gravity = Gravity.CENTER
            toast.show()
        }
    }

    /**
     * Display a simple [AlertDialog] with a simple OK button.
     * If the dismiss listener is specified, the dialog becomes uncancellable
     * @param context The context
     * @param title The title string
     * @param message The message string
     * @param dismissListener The dismiss listener
     */
    fun showMessageDialog(
        context: Context? = getAppContext(),
        title: String? = null,
        message: String?,
        dismissListener: DialogInterface.OnDismissListener? = null
    ) {
        if (context == null) return
        val builder = AlertDialog.Builder(context, R.style.AppTheme_Dialog_Alert)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        if (dismissListener != null)
            dialog.setOnDismissListener(dismissListener)
        dialog.show()
        val textView = dialog.findViewById<TextView>(android.R.id.message)
        textView?.textSize = 16f
    }

    fun showMessageDialog(context: Context?, message: String?) {
        if (context == null || message == null) return
        showMessageDialog(context = context, title = null, message = message)
    }

    /**
     * Prints an exception's stack trace.
     * Stack traces printed via this method will only show up on debug builds.
     * @param throwable the throwable
     */
    fun printStackTrace(throwable: Throwable) {
        if (BuildConfig.DEBUG) throwable.printStackTrace()
    }

    /**
     * Prints a [Log] message.
     * Log messages printed via this method will only show up on debug builds.
     * @param type The specified log type, may be [Log.DEBUG], [Log.INFO], and other log types
     * @param tag The log tag to print
     * @param message The log message to print
     */
    fun log(type: Int = Log.DEBUG, tag: String? = "BaseProject", message: String?) {
        if (BuildConfig.DEBUG) {
            var logMessage = message
            if (logMessage.isNullOrEmpty()) logMessage = "Message is null, what exactly do you want to print?"
            Log.println(type, tag, logMessage)
        }
    }


    /** Prevent null string to "" */
    fun preventNull(text: String?): String {
        return if (text == null) "" else text
    }

    fun hideKeyboard(context: Context, currentFocus: View?) {
        // Check if no view has focus:
        if (currentFocus != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}
