package ru.bogdan.application.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getEmployees(): Result<List<Employee>>

    suspend fun getEmployeeById(id: String): Result<Employee>

    suspend fun deleteEmployee(employee: Employee): Result<Boolean>

    suspend fun addFavoriteEmployee(employee: Employee): Boolean

    suspend fun removeFavoriteEmployee(employee: Employee): Boolean

    suspend fun getFavoriteById(id: String): Employee?

    suspend fun getFavoriteEmployees(): Flow<List<Employee>>
}