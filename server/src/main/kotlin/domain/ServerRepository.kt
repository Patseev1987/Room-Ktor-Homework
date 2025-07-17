package domain

interface ServerRepository {
    suspend fun findEmployees(): List<Employee>

    suspend fun findEmployeeById(id: String): Employee?

    suspend fun updateEmployee(employee: Employee): Boolean

    suspend fun addEmployee(employee: Employee): Employee

    suspend fun deleteEmployee(employeeId: String): Boolean
}