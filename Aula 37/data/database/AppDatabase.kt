package com.example.projetosqlitecapgemini.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projetosqlitecapgemini.data.dao.PersonDao
import com.example.projetosqlitecapgemini.data.entity.Person

@Database(
    entities = [Person::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder (
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }

    }

}








