package ru.bogdan.application.data.db

import kotlinx.coroutines.flow.Flow
import ru.bogdan.application.domain.Employee

interface DBRepository {
    suspend fun addFavoriteEmployee(employee: Employee): Boolean

    suspend fun removeFavoriteEmployee(employee: Employee): Boolean

    suspend fun getFavoriteEmployees(): Flow<List<Employee>>

    suspend fun getEmployeeById(employeeId: String): Employee?
}