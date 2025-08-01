package ru.bogdan.application.data.web.model

import kotlinx.serialization.Serializable
import ru.bogdan.application.domain.ToolType

@Serializable
data class ToolWeb(
    val code: String,
    val name: String,
    val description: String?,
    val type: ToolType,
    val photoUrl: String?,
)