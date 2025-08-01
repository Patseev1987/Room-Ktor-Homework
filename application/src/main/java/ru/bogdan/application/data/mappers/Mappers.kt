package ru.bogdan.application.data.mappers

import ru.bogdan.application.data.db.model.EmployeeDB
import ru.bogdan.application.data.db.model.EmployeeDBWithToolDB
import ru.bogdan.application.data.db.model.ToolDB
import ru.bogdan.application.data.web.model.EmployeeWeb
import ru.bogdan.application.data.web.model.ToolWeb
import ru.bogdan.application.domain.Employee
import ru.bogdan.application.domain.Tool

fun EmployeeDB.toEmployee(tools: List<Tool>): Employee = Employee(
    id = this.id,
    name = this.name,
    surname = this.surname,
    photoUrl = this.photoUrl,
    tools = tools,
    isFavorite = this.isFavorite
)

fun Employee.toEmployeeDB(): EmployeeDB = EmployeeDB(
    id = this.id,
    name = this.name,
    surname = this.surname,
    photoUrl = this.photoUrl,
    isFavorite = this.isFavorite
)

fun ToolDB.toTool(): Tool = Tool(
    code = this.code,
    name = this.name,
    description = this.description,
    photoUrl = this.photoUrl,
    type = this.type,
)

fun List<ToolDB>.toTool(): List<Tool> = this.map { it.toTool() }

fun Tool.toToolDB(): ToolDB = ToolDB(
    code = this.code,
    name = this.name,
    description = this.description,
    photoUrl = this.photoUrl,
    type = this.type,
)

fun List<Tool>.toToolDB(): List<ToolDB> = this.map { it.toToolDB() }

fun ToolWeb.toTool(): Tool = Tool(
    code = this.code,
    name = this.name,
    description = this.description,
    photoUrl = this.photoUrl,
    type = this.type,
)

fun List<ToolWeb>.toTools(): List<Tool> = this.map { it.toTool() }

fun Tool.toToolWeb(): ToolWeb = ToolWeb(
    code = this.code,
    name = this.name,
    description = this.description,
    photoUrl = this.photoUrl,
    type = this.type,
)

fun List<Tool>.toToolsWeb() = this.map { it.toToolWeb() }

fun EmployeeWeb.toEmployee(): Employee = Employee(
    id = this.id,
    name = this.name,
    surname = this.surname,
    photoUrl = this.photoUrl,
    tools = this.tools.toTools(),
    isFavorite = false
)

fun Employee.toEmployeeWeb() = EmployeeWeb(
    id = this.id,
    name = this.name,
    surname = this.surname,
    photoUrl = this.photoUrl,
    tools = this.tools.toToolsWeb(),
)

fun Result<List<EmployeeWeb>>.toResultEmployees(): Result<List<Employee>> {
    return runCatching {
        this.getOrThrow().map { it.toEmployee() }
    }
}

fun EmployeeDBWithToolDB.toEmployee(): Employee = Employee(
    id = this.employeeDB.id,
    name = this.employeeDB.name,
    surname = this.employeeDB.surname,
    photoUrl = this.employeeDB.photoUrl,
    isFavorite = this.employeeDB.isFavorite,
    tools = this.tools.map { it.toTool() },
)

fun List<EmployeeDBWithToolDB>.toEmployees(): List<Employee> = this.map { it.toEmployee() }

