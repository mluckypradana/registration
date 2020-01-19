package com.luc.base.ui.notes

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luc.base.R
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.getString
import com.luc.base.database.entity.Note
import com.luc.base.repository.NoteRepo
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotesVm(context: Application) : BaseViewModel(context), KoinComponent {
    private val repo: NoteRepo by inject()
    var data: MutableLiveData<Note> = MutableLiveData(Note())
    val list: LiveData<List<Note>>

    init {
        list = repo.list
    }

    fun insert(
        onSuccess: (Resource<Note>) -> Unit,
        onError: (Resource<Note>) -> Unit,
        onValidate: (String) -> Unit
    ) = viewModelScope.launch {
        val data = data.value
        val errorResId = when {
            data == null -> R.string.error_common
            data.title.isEmpty() -> R.string.error_empty_title
            else -> 0
        }
        if (errorResId != 0) {
            onValidate(getString(errorResId))
            return@launch
        }
        when (val res = repo.insert(data!!)) {
            is Resource.Success<*> -> onSuccess(res)
            is Resource.Error -> onError(res)
        }
    }
}