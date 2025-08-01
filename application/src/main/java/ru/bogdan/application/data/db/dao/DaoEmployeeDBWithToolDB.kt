package ru.bogdan.application.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.bogdan.application.data.db.model.EmployeeDBWithToolDB

@Dao
interface DaoEmployeeDBWithToolDB {

    @Transaction
    @Query("SELECT * FROM employees")
    fun getFavorites(): Flow<List<EmployeeDBWithToolDB>>

    @Transaction
    @Query("SELECT * FROM employees WHERE id = :employeeId")
    fun getEmployeeById(employeeId: String): EmployeeDBWithToolDB?
}