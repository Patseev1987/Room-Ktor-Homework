package ru.bogdan.application.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.bogdan.application.data.db.model.ToolDB

@Dao
interface DaoToolDB {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToolDB(toolDB: ToolDB): Long

    @Delete
    suspend fun removeToolDB(toolDB: ToolDB)
}