@file:OptIn(ExperimentalUuidApi::class)

package data

import domain.Employee
import domain.ServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

object ServerRepositoryImpl : ServerRepository {
    private val data = mutableListOf(employee1, employee2, employee3)
    private val mutex = Mutex()


    override suspend fun findEmployees(): List<Employee> {
        return data
    }

    override suspend fun findEmployeeById(id: String): Employee? {
        return data.firstOrNull { it.id == id }
    }

    override suspend fun updateEmployee(employee: Employee): Boolean {
        try {
            protectData {
                val temp = data.find { it.id == employee.id }
                data.remove(temp)
                data.add(employee)
            }
            return true
        } catch (e: Throwable) {
            return false
        }
    }

    override suspend fun addEmployee(employee: Employee): Employee {
        var emp = employee
        try {
            protectData {
                emp = emp.copy(id = Uuid.random().toString())
                data.add(emp)
            }
            return emp
        } catch (e: Throwable) {
            throw e
        }
    }

    override suspend fun deleteEmployee(employeeId: String): Boolean {
        try {
            val temp = data.find { it.id == employeeId }
            data.remove(temp)
            return true
        } catch (e: Throwable) {
            return false
        }
    }


    private suspend fun ServerRepositoryImpl.protectData(block: () -> Unit) {
        withContext(Dispatchers.IO) {
            mutex.withLock {
                block()
            }
        }
    }
}