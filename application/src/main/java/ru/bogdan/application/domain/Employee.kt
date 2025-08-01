package ru.bogdan.application.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Employee(
    val id: String,
    val name: String,
    val surname: String,
    val photoUrl: String?,
    val tools: List<Tool>,
    var isFavorite: Boolean
) {
    companion object {
        val NONE = Employee(
            id = "111",
            name = "Ivan",
            surname = "Gross",
            photoUrl = "https://profile-images.xing.com/images/69fb2614a4e1a4bec2002102c3da4c8c-4/ivan-gross.1024x1024.jpg",
            tools = emptyList(),
            isFavorite = false
        )
    }
}
