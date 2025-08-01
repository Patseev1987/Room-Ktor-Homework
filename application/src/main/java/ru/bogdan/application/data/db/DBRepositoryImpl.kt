package ru.bogdan.application.data.db

import androidx.room.withTransaction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.bogdan.application.data.db.model.EmployeeDBToolDBCross
import ru.bogdan.application.data.mappers.toEmployee
import ru.bogdan.application.data.mappers.toEmployeeDB
import ru.bogdan.application.data.mappers.toEmployees
import ru.bogdan.application.data.mappers.toToolDB
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DBRepository {

    private val daoEmployee by lazy { db.daoEmployeeDBWithToolDB }
    private val daoTool by lazy { db.daoToolDB }
    private val daoEmployeeDB by lazy { db.daoEmployeeDB }
    private val daoDBCross by lazy { db.daoEmployeesDBToolDBCross }


    override suspend fun addFavoriteEmployee(employee: Employee): Boolean =
        withContext(dispatcher) {
            try {
                db.withTransaction {
                    daoEmployeeDB.addEmployeeDB(
                        employeeDB = employee.toEmployeeDB()
                    )
                    employee.tools.forEach { tool ->
                        daoTool.addToolDB(toolDB = tool.toToolDB())
                        daoDBCross.addCross(
                            EmployeeDBToolDBCross(
                                employee.id,
                                code = tool.code,
                            )
                        )
                    }

                }
            } catch (_: Throwable) {
                false
            }
            true
        }

    override suspend fun removeFavoriteEmployee(employee: Employee): Boolean =
        withContext(dispatcher) {
            try {
                db.withTransaction {
                    daoEmployeeDB.removeEmployeeDB(employee.toEmployeeDB())
                }
            } catch (_: Throwable) {
                false
            }
            true
        }

    override suspend fun getFavoriteEmployees(): Flow<List<Employee>> =
        withContext(dispatcher) {
            daoEmployee.getFavorites().map { it.toEmployees() }
        }

    override suspend fun getEmployeeById(employeeId: String): Employee? =
        withContext(dispatcher) {
            daoEmployee.getEmployeeById(employeeId)?.toEmployee()
        }
}