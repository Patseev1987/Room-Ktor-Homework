package ru.bogdan.application.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.bogdan.application.data.db.model.EmployeeDBToolDBCross

@Dao
interface DaoEmployeeDBToolDBCross {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCross(cross: EmployeeDBToolDBCross)

    @Delete
    suspend fun removeCross(cross: EmployeeDBToolDBCross)
}