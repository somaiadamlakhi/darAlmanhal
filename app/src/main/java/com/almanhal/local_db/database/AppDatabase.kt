package com.android.test.local_db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.*
import androidx.room.RoomDatabase
import com.almanhal.local_db.entity.BookModel
import com.almanhal.local_db.entity.SubCategoryModel
import com.android.test.local_db.dao.BookDao
import com.android.test.local_db.dao.CategoryDao

@Database(entities = arrayOf(BookModel::class,SubCategoryModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun booksDao(): BookDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also {
                        INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "DarAlmanhal.db"
            )
                .build()
    }
}