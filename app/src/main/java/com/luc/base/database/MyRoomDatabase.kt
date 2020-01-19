package com.luc.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luc.base.database.dao.NoteDao
import com.luc.base.database.entity.Note

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Note::class], version = 3)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val APP_NAME = "note"
    }
}