package com.lazygeniouz.roomdb_practise.databse.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An Entity or a POJO class that holds our data.
 *
 * Has the table name **"test_entities"**
 */
@Entity(tableName = "test_entities")
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)