package com.example.jerry.mvvmpractice.model.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.jerry.mvvmpractice.model.data.Article
import com.example.jerry.mvvmpractice.model.local.dao.PaoDao

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun paoDao(): PaoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app.db"
            )
                .build()
    }
}