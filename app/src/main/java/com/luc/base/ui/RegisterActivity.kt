package com.luc.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.luc.base.R
import com.luc.base.databinding.ActivityRegisterBinding
import com.luc.base.ui.register.RegisterVm
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var bind: ActivityRegisterBinding
    private val vm: RegisterVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_register)
        bind.vm = vm
        bind.bProceed.setOnClickListener {
            vm.register(
                {},
                {},
                {}
            )
        }
        setSupportActionBar(bind.toolbar.toolbar)
    }
}