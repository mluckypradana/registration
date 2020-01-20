package com.luc.base.core.module

import android.content.Context
import androidx.room.Room
import com.luc.base.R
import com.luc.base.core.extension.getString
import com.luc.base.core.helper.SessionStorage
import com.luc.base.database.MyRoomDatabase
import com.luc.base.repository.UserRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DbModule {
    val dbModule = module {
        single { SessionStorage(androidContext()) }
        single { provideDatabase(androidContext()) }
        single { get<MyRoomDatabase>().userDao() }
        single { UserRepo(get()) }
    }

    private fun provideDatabase(context: Context): MyRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyRoomDatabase::class.java,
            getString(R.string.app_name) + "_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
