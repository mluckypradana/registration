package com.luc.base.ui.register

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.luc.base.R
import com.luc.base.core.helper.Common
import com.luc.base.database.entity.User
import com.luc.base.databinding.ActivityRegisterBinding
import com.luc.base.helper.ProfileHelper
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
                {
                    hideProgress()
                },
                {
                    hideProgress()
                },
                {
                    hideProgress()
                    enableLoginButton()
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
        setSupportActionBar(bind.toolbar.toolbar)
    }

    private fun enableLoginButton() {
        bind.lForm.alpha = 0.5f
        bind.bLogin.visibility = View.VISIBLE
    }

    private fun showProgress() = Common.showProgressDialog(this, R.string.message_loading)

    private fun hideProgress() = Common.dismissProgressDialog()

}