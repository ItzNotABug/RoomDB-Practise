package com.lazygeniouz.roomdb_practise.databse.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Abstract class to build the [RoomDatabase]
 */
@Database(entities = [TestEntity::class], version = 1, exportSchema = false)
abstract class TestAppDatabase : RoomDatabase() {

    /**
     * Get access to DAO ([TestDao])
     */
    abstract fun testDao(): TestDao

    companion object {
        @Volatile
        private var instance: TestAppDatabase? = null


        /**
         * Get [TestAppDatabase] via Singleton Pattern
         */
        fun getInstance(context: Context): TestAppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildTestDatabase(context).also { instance = it }
            }
        }

        // Build and return an instance of Room Database
        private fun buildTestDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                TestAppDatabase::class.java,
                "TEST_DATABASE"
            ).build()

    }
}