package com.luc.base.core.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    lateinit var mActivity: BaseActivity
    val bundle = Bundle()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity as BaseActivity
    }

    protected fun showProgress() {
        mActivity.showProgress()
    }

    protected fun hideProgress() {
        mActivity.hideProgress()
    }
}