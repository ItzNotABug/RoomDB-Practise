package com.lazygeniouz.roomdb_practise.databse.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * A DAO (Data Access Object) Interface to handle Room Database Operations
 */
@Dao
interface TestDao {

    /**
     * Get all entities from database as [Flow]
     */
    @Query("select * from test_entities")
    fun getAll(): Flow<List<TestEntity>>

    /**
     * Get a specif Entity having uid == `id`
     */
    @Query("select * from test_entities where uid in (:id)")
    suspend fun getEntityById(id: Int): TestEntity

    /**
     * Insert an Entity in to the Database
     */
    // Afaik, there won't be any conflict
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(testEntity: TestEntity)

    /**
     * Delete a specific Entity having uid == `id`
     */
    @Query("delete from test_entities where uid in (:id)")
    suspend fun deleteEntityById(id: Int)

    /**
     * Delete all Entities in Database
     */
    @Query("delete from test_entities")
    suspend fun nukeAllEntities()
}