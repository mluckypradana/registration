package com.luc.base.ui

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.luc.base.R
import com.luc.base.core.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleIntent()

        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    private fun handleIntent() {
        val action: String? = intent?.action
        val data: Uri? = intent?.data
        if (action.isNullOrEmpty() && data == null)
            return
    }
}
