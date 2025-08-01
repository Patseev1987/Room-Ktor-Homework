package ru.bogdan.application.data.db.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "EmployeeDBToolDBCross",
    primaryKeys = ["id", "code"],
    foreignKeys = [
        ForeignKey(
            entity = EmployeeDB::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ToolDB::class,
            parentColumns = ["code"],
            childColumns = ["code"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EmployeeDBToolDBCross(
    val id: String,
    val code: String,
)