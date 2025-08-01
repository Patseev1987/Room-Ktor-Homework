package ru.bogdan.application.domain


data class Tool(
    val code: String,
    val name: String,
    val description: String?,
    val type: ToolType,
    val photoUrl: String?,
)