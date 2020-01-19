package com.luc.base.base

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.luc.base.App
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
open class BaseTest : KoinComponent {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun beforeEach() {
        val app = getApplicationContext<App>()

        MockitoAnnotations.initMocks(this)
        if (GlobalContext.getOrNull() == null)
            startKoin { androidContext(app) }
    }

    @After
    fun afterEach() {
        stopKoin()
        try {
            Mockito.validateMockitoUsage()
        } catch (t: Throwable) {
            println("There could be no Mockito usage to validate")
        }
    }
}
