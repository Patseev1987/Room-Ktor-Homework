package ru.bogdan.application.data.web.model

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeWeb(
    val id: String,
    val name: String,
    val surname: String,
    val photoUrl: String?,
    val tools: List<ToolWeb>
)
