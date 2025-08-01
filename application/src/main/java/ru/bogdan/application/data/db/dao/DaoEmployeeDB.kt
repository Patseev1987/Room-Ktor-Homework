package ru.bogdan.application.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.bogdan.application.data.db.model.EmployeeDB

@Dao
interface DaoEmployeeDB {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmployeeDB(employeeDB: EmployeeDB)

    @Delete
    suspend fun removeEmployeeDB(employeeDB: EmployeeDB)
}