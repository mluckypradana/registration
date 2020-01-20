package com.luc.base.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.luc.base.R
import com.luc.base.core.helper.Common
import com.luc.base.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var bind: ActivityLoginBinding
    private val vm: LoginVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_login)
        bind.vm = vm
        bind.bProceed.setOnClickListener {
            showProgress()
            vm.login(
                { hideProgress() },
                {
                    hideProgress()
                    Common.showMessageDialog(this@LoginActivity, it)
                },
                {
                    hideProgress()
                    Common.showMessageDialog(this@LoginActivity, vm.getGreetingsMessage(it))
                }
            )
        }
        setSupportActionBar(bind.toolbar.toolbar)
    }

    private fun showProgress() = Common.showProgressDialog(this, R.string.message_loading)

    private fun hideProgress() = Common.dismissProgressDialog()
}