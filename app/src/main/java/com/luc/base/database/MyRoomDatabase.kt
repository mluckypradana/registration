package com.luc.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luc.base.database.dao.UserDao
import com.luc.base.database.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}