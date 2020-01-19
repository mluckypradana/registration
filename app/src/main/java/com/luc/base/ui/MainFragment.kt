package com.luc.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.luc.base.R
import com.luc.base.core.base.BaseFragment
import com.luc.base.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navController = view.findNavController()

        navController = view.findNavController()
        val setOf = setOf(
            R.id.homeFragment,
            R.id.notesFragment
        )
        AppBarConfiguration(setOf)
        binding.navView.setupWithNavController(navController)
    }
}