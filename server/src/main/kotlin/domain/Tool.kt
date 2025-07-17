package domain

import kotlinx.serialization.Serializable

@Serializable
data class Tool(
    val code: String,
    val name: String,
    val description: String?,
    val type:ToolType,
    val photoUrl: String?,
)
