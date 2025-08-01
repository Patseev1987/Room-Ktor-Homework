package ru.bogdan.application.data

import kotlinx.coroutines.flow.Flow
import ru.bogdan.application.data.db.DBRepository
import ru.bogdan.application.data.mappers.toEmployee
import ru.bogdan.application.data.mappers.toEmployeeWeb
import ru.bogdan.application.data.mappers.toResultEmployees
import ru.bogdan.application.data.web.RemoteRepository
import ru.bogdan.application.domain.AppRepository
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val dbRepository: DBRepository,
) : AppRepository {
    override suspend fun getEmployees(): Result<List<Employee>> {
        return remoteRepository.getEmployees().toResultEmployees()
    }

    override suspend fun getEmployeeById(id: String): Result<Employee> {
        return runCatching {
            remoteRepository.getEmployee(id).getOrThrow().toEmployee()
        }
    }

    override suspend fun deleteEmployee(employee: Employee): Result<Boolean> {
        return runCatching {
            remoteRepository.deleteEmployee(employee.toEmployeeWeb()).getOrThrow()
        }

    }

    override suspend fun addFavoriteEmployee(employee: Employee): Boolean {
        return dbRepository.addFavoriteEmployee(employee)
    }

    override suspend fun removeFavoriteEmployee(employee: Employee): Boolean {
        return dbRepository.removeFavoriteEmployee(employee)
    }

    override suspend fun getFavoriteById(id: String): Employee? {
        return dbRepository.getEmployeeById(id)
    }

    override suspend fun getFavoriteEmployees(): Flow<List<Employee>> {
        return dbRepository.getFavoriteEmployees()
    }
}