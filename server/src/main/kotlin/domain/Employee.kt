package domain

import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: String,
    val name: String,
    val surname: String,
    val photoUrl: String?,
    val tools: List<Tool>
)
