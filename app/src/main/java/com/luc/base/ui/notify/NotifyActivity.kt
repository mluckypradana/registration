package com.luc.base.ui.notify

import android.os.Bundle
import com.luc.base.core.Constant
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseActivity
import com.luc.base.core.helper.toGson
import com.luc.base.databinding.ActivityNotifyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotifyActivity : BaseActivity() {
    private val vm: NotifyVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityNotifyBinding.inflate(layoutInflater)
        bind.vm = vm
        bind.bProceed.setOnClickListener { finish() }
        initData()
        setContentView(bind.root)
    }

    private fun initData() {
        val resource = intent.extras?.getString(Constant.BUNDLE_DATA)?.toGson(Resource.Success::class.java)
        vm.message.value = resource?.message
    }
}