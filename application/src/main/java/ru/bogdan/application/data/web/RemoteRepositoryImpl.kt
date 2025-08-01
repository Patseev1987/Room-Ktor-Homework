package ru.bogdan.application.data.web

import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.bogdan.application.data.web.model.EmployeeWeb
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val client: HttpClientApp,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteRepository {
    override suspend fun getEmployees(): Result<List<EmployeeWeb>> =
        withContext(dispatcher) {
            runCatching {
                client.client.get("${BASE_URL}/employees").body()
            }
        }

    override suspend fun getEmployee(id: String): Result<EmployeeWeb> =
        withContext(dispatcher) {
            runCatching {
                client.client.get("${BASE_URL}/employees/$id").body()
            }
        }

    override suspend fun deleteEmployee(employee: EmployeeWeb): Result<Boolean> =
        withContext(dispatcher) {
            runCatching {
                client.client.delete("${BASE_URL}/employees/${employee.id}").body()
            }
        }
}