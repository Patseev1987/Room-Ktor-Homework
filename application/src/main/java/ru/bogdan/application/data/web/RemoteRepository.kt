package ru.bogdan.application.data.web

import ru.bogdan.application.data.web.model.EmployeeWeb

interface RemoteRepository {
    suspend fun getEmployees(): Result<List<EmployeeWeb>>
    suspend fun getEmployee(id: String): Result<EmployeeWeb>
    suspend fun deleteEmployee(employee: EmployeeWeb): Result<Boolean>
}