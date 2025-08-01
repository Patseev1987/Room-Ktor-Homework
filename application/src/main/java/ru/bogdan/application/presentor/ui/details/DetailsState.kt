package ru.bogdan.application.presentor.ui.details

import ru.bogdan.application.domain.Employee

data class DetailsState(
    val employee: Employee = Employee.NONE,
    val isLoading: Boolean = true,
    val error: Throwable? = null
)
