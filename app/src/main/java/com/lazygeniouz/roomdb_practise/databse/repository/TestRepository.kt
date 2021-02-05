package com.lazygeniouz.roomdb_practise.databse.repository

import android.content.Context
import com.github.javafaker.Faker
import com.lazygeniouz.roomdb_practise.databse.room.TestAppDatabase
import com.lazygeniouz.roomdb_practise.databse.room.TestDao
import com.lazygeniouz.roomdb_practise.databse.room.TestEntity
import kotlinx.coroutines.flow.Flow

/**
 * Class to connect with the [TestDao]
 * & follow the classic **Repository** pattern
 */
class TestRepository(context: Context) {

    private val faker by lazy { Faker() }
    private val dao by lazy { database.testDao() }
    private val database by lazy { TestAppDatabase.getInstance(context) }

    /**
     * Get all Entities as [Flow]
     */
    fun getEntities() = dao.getAll()

    /**
     * Add a randomly generated Entity to the Database
     */
    suspend fun insertEntity() {
        dao.insertEntity(
            TestEntity(
                firstName = faker.name().firstName(), lastName = faker.name().lastName()
            )
        )
    }

    /**
     * Delete all the entities in database
     */
    suspend fun nukeAllEntities() = dao.nukeAllEntities()

    /**
     * Delete a specific Entity having the passed Id ([TestEntity.uid])
     */
    suspend fun deleteEntity(id: Int) = dao.deleteEntityById(id)
}