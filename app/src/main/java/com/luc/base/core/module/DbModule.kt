package com.luc.base.core.module

import android.content.Context
import android.database.sqlite.SQLiteException
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.luc.base.core.helper.SessionStorage
import com.luc.base.database.MyRoomDatabase
import com.luc.base.repository.NoteRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DbModule {
    val dbModule = module {
        single { SessionStorage(androidContext()) }
        single { provideDatabase(androidContext()) }
        single { get<MyRoomDatabase>().noteDao() }
        single { NoteRepo(get()) }
    }

    private fun provideDatabase(context: Context): MyRoomDatabase {
        val migration1To2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("")
            }
        }
        val migration2To3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    database.execSQL(
                        "ALTER TABLE note_table "
                                + "ADD COLUMN content TEXT"
                    )
                } catch (e: SQLiteException) {
                    e.printStackTrace()
                }
            }
        }

        return Room.databaseBuilder(
            context.applicationContext,
            MyRoomDatabase::class.java,
            MyRoomDatabase.APP_NAME + "_database"
        )
//                    .fallbackToDestructiveMigration()
            .addMigrations(migration1To2, migration2To3)
            .build()
    }
}
