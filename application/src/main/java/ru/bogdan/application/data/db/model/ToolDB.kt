package ru.bogdan.application.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.bogdan.application.domain.ToolType

@Entity(tableName = "tools")
data class ToolDB(
    @PrimaryKey
    val code: String,
    val name: String,
    val description: String? = null,
    val type: ToolType,
    val photoUrl: String? = null,
)