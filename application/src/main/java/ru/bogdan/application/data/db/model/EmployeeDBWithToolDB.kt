package ru.bogdan.application.data.db.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EmployeeDBWithToolDB(
    @Embedded val employeeDB: EmployeeDB,
    @Relation(
        parentColumn = "id",
        entityColumn = "code",
        associateBy = Junction(EmployeeDBToolDBCross::class)
    )
    val tools: List<ToolDB>
)