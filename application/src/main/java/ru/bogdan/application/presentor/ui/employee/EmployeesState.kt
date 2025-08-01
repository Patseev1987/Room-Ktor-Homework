package ru.bogdan.application.presentor.ui.employee

import ru.bogdan.application.domain.Employee

data class EmployeesState(
    val isLoading: Boolean = true,
    val employees: List<Employee> = emptyList(),
    val error: Throwable? = null,
)
