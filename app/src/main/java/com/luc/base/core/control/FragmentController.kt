package com.luc.base.core.control

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.luc.base.R

object FragmentController {
    fun navigateTo(
        context: FragmentActivity?,
        fragmentName: String,
        args: Bundle? = null,
        addToBackStack: Boolean = false,
        reorderingAllowed: Boolean = false
    ) {
        updateFragmentTransaction(context, fragmentName, args, addToBackStack, reorderingAllowed)
    }

    private fun updateFragmentTransaction(
        context: FragmentActivity?,
        fragmentName: String,
        args: Bundle?,
        addToBackStack: Boolean,
        reorderingAllowed: Boolean
    ) {
        val fm: FragmentManager? = context?.supportFragmentManager
        val ft = fm?.beginTransaction()
        val fragment = context?.let {
            fm?.fragmentFactory?.instantiate(ClassLoader.getSystemClassLoader(), fragmentName)
//            Fragment.instantiate(it, fragmentName, args)
        }
        fragment?.arguments = args
        fragment?.let { ft?.replace(R.id.container, it, fragmentName) }
        if (addToBackStack) ft?.addToBackStack(fragmentName)
        if (reorderingAllowed) ft?.setReorderingAllowed(true)
        ft?.commitAllowingStateLoss()
    }

    fun popBack(activity: FragmentActivity?, targetFragmentName: String = "") {
        val fm = activity?.supportFragmentManager
        if (targetFragmentName.isNotEmpty()) fm?.popBackStack(targetFragmentName, 0)
        else fm?.popBackStackImmediate()
    }

}