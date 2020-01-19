package com.luc.base.main.repo

import com.luc.base.base.BaseRepoTest
import com.luc.base.core.api.MockInterceptor
import com.luc.base.database.dao.NoteDao
import com.luc.base.database.entity.Note
import com.luc.base.repository.NoteRepo
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NoteRepoTest : BaseRepoTest() {
    private val repo = NoteRepo(mock(NoteDao::class.java))

    @Test
    fun insertTest() {
        runBlocking {
            MockInterceptor.setResponse(200, readFromFile("/note_create.json"))
            val result = repo.insert(Note())
            assert(result.message == "Success adding a note")
        }
    }
}