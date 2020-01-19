package com.luc.base

import androidx.multidex.MultiDexApplication
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.persistence.GanderPersistence
import com.luc.base.core.module.DbModule.dbModule
import com.luc.base.core.module.NetworkModule.networkMockModule
import com.luc.base.core.module.NetworkModule.networkModule
import com.luc.base.core.module.VmModule.vmModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    companion object {
        var forTesting: Boolean = true
    }

    override fun onCreate() {
        super.onCreate()
        Gander.setGanderStorage(GanderPersistence.getInstance(this))
        Hawk.init(this).build()

        startKoin {
            androidContext(this@App)
            modules(if (forTesting) networkMockModule else networkModule)
            modules(vmModule)
            modules(dbModule)
        }
    }

    fun setForTesting() {
        forTesting = true
    }
}