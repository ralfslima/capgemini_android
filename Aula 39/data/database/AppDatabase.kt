package com.example.projetosqlitecapgemini.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projetosqlitecapgemini.data.dao.PersonDao
import com.example.projetosqlitecapgemini.data.entity.Address
import com.example.projetosqlitecapgemini.data.entity.Person

@Database(
    entities = [Person::class, Address::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {

        // ==================== MIGRATIONS
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE persons ADD COLUMN email TEXT NOT NULL DEFAULT ''")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE persons DROP COLUMN email")
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS addresses (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        street TEXT NOT NULL,
                        city TEXT NOT NULL,
                        personId INTEGER NOT NULL,
                        FOREIGN KEY(personId) REFERENCES persons(id) ON DELETE CASCADE
                    )
                """)
            }
        }


        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    //.fallbackToDestructiveMigration(true) <- REMOVE TODAS AS TABELAS
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()
                INSTANCE = instance
                instance // Retorna a instância criada
            }
        }

    }

}








