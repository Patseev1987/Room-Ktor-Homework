package ru.bogdan.application.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class EmployeeDB(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,
    val photoUrl: String? = null,
    val isFavorite: Boolean
)
