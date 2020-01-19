package com.luc.base.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.luc.base.R
import com.luc.base.base.BaseListAdapter
import com.luc.base.core.Constant
import com.luc.base.core.base.BaseFragment
import com.luc.base.core.control.ActivityController
import com.luc.base.core.helper.Common
import com.luc.base.core.helper.json
import com.luc.base.databinding.FragmentNotesBinding
import com.luc.base.ui.notify.NotifyActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesFragment : BaseFragment(), NoteAddDialogFragment.OnFragmentInteractionListener {
    private lateinit var adapter: BaseListAdapter
    private lateinit var fragment: NoteAddDialogFragment
    private val vm: NotesVm by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentNotesBinding.inflate(inflater, container, false)
        bind.vm = vm
        bind.bProceed.setOnClickListener { showAddPage() }
        vm.list.observe(viewLifecycleOwner, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        adapter = BaseListAdapter(R.layout.item_note)
        adapter.onItemClick = { _, position ->
            Common.showToast(vm.list.value?.get(position)?.title)
        }
        bind.rvMain.adapter = adapter
        return bind.root
    }

    private fun showAddPage() {
        childFragmentManager.let {
            fragment = NoteAddDialogFragment.newInstance()
            fragment.vm = vm
            fragment.listener = this
            fragment.show(it, "add")
        }
    }

    override fun onAddNote() {
        fragment.dismiss()
        showProgress()
        vm.insert({
            hideProgress()
            fragment.clearData()
            ActivityController.navigateTo(
                mActivity, NotifyActivity::class.java, false, bundleOf(
                    Constant.BUNDLE_DATA to it.json()
                )
            )
        }, {
            hideProgress()
            Common.showToast(it.message)
        }, {
            fragment.show(childFragmentManager, "add")
            Common.showToast(it)
        })
    }
}