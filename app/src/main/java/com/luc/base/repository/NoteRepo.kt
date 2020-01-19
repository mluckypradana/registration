package com.luc.base.repository

import androidx.lifecycle.LiveData
import com.luc.base.core.Constant
import com.luc.base.core.api.Resource
import com.luc.base.core.base.BaseRepository
import com.luc.base.database.dao.NoteDao
import com.luc.base.database.entity.Note

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NoteRepo(private val noteDao: NoteDao) : BaseRepository() {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the message has changed.
    val list: LiveData<List<Note>> = noteDao.getAlphabetizedNotes()

    /** Validate, post to server, save to database*/
    suspend fun insert(data: Note): Resource<Note> {
        return try {
            val response = api.createNote(data)
            if (response.isSuccessful) {
                noteDao.insert(data)
                sessionStorage.put(Constant.NOTE_SAVED, "dummy")
                val res = response.getResponse()
                Resource.Success(res.data, res.message)
            } else Resource.Error(getErrorMessage(response))
        } catch (e: Exception) {
            Resource.Error(getErrorMessage(e))
        }
    }

}