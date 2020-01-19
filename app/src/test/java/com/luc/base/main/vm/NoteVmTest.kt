package com.luc.base.main.vm

import com.luc.base.base.BaseVmTest
import com.luc.base.core.api.Resource
import com.luc.base.database.entity.Note
import com.luc.base.repository.NoteRepo
import com.luc.base.ui.notes.NotesVm
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.inject
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NoteVmTest : BaseVmTest() {
    private val vm: NotesVm by inject()
    private val repo = mock(NoteRepo::class.java)

    @Test
    fun validateInsert() {
        runBlocking {
            vm.insert(
                { assert(false) },
                { assert(false) },
                {
                    assert(it == "Harap isi judul")
                })
        }
    }

    @Test
    fun errorInsert() {
        val expectedMessage = "Gagal menambah data"
        runBlocking {
            `when`(repo.insert(any())).thenReturn(Resource.Error(expectedMessage))
            vm.data.postValue(getValidData())
            vm.insert(
                { assert(false) },
                {
                    assert(it.message == expectedMessage)
                },
                { assert(false) }
            )
        }
    }

    @Test
    fun successInsert() {
        val expectedData = getValidData()
        runBlocking {
            `when`(repo.insert(any())).thenReturn(Resource.Success(expectedData))
            vm.data.postValue(expectedData)
            vm.insert(
                {
                    assert(it.data == expectedData)
                },
                { assert(false) },
                { assert(false) }
            )
        }
    }

    private fun getValidData(): Note {
        val note = Note()
        note.title = "Judul"
        note.content = "Content"
        return note
    }
}