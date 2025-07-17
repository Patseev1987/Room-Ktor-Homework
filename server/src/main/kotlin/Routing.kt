import data.ServerRepositoryImpl
import domain.Employee
import domain.ServerRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureRouting(repository: ServerRepository = ServerRepositoryImpl) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
            }
        )
    }

    routing {
        get("/employees") {
            call.respond(repository.findEmployees())
        }

        get("/employees/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val temp = repository.findEmployeeById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(temp)
        }

        post("/employees") {
            var employee = call.receive<Employee>()
            employee = repository.addEmployee(employee)
            call.respond(employee)
        }

        delete("/employees/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (repository.deleteEmployee(id)) call.respond(HttpStatusCode.OK) else call.respond(HttpStatusCode.NotFound)
        }

        put("/employees/update") {
            val employee = call.receive<Employee>()
            val temp =repository.updateEmployee(employee)
            if (temp) call.respond(HttpStatusCode.OK) else call.respond(HttpStatusCode.NotFound)
        }
    }
}
