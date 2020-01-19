package com.luc.base.ui.notify

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.luc.base.core.base.BaseViewModel
import org.koin.core.KoinComponent

class NotifyVm(context: Application) : BaseViewModel(context), KoinComponent {
    val message: MutableLiveData<String> = MutableLiveData("")
    val dummyImageUrl: MutableLiveData<String> = MutableLiveData("https://pngriver.com/wp-content/uploads/2018/04/Download-Success-PNG-Image.png")
}