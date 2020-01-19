package com.luc.base.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.luc.base.R
import com.luc.base.databinding.FragmentNoteAddDialogBinding
import org.koin.core.KoinComponent

class NoteAddDialogFragment : DialogFragment(), KoinComponent {
    private lateinit var bind: FragmentNoteAddDialogBinding
    var vm: NotesVm? = null
    internal var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_note_add_dialog, container, false)
        bind.viewModel = vm
        bind.bProceed.setOnClickListener { listener?.onAddNote() }
        return bind.root
    }

    fun clearData() {
        bind.etTitle.setText("")
    }

    interface OnFragmentInteractionListener {
        fun onAddNote()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NoteAddDialogFragment()
//                .apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}
