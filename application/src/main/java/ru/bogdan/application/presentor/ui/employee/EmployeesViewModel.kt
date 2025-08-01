package ru.bogdan.application.presentor.ui.employee

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.bogdan.application.domain.Employee
import ru.bogdan.application.domain.useCases.AddFavoriteUseCase
import ru.bogdan.application.domain.useCases.GetEmployeesUseCase
import ru.bogdan.application.domain.useCases.GetFavoriteUseCase
import ru.bogdan.application.domain.useCases.RemoveFavoriteUseCase
import javax.inject.Inject

class EmployeesViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val getEmployeesUseCase: GetEmployeesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(EmployeesState())
    val state = _state.asStateFlow()
    private val employees = mutableMapOf<String, Employee>()

    private fun getEmployees() {
        employees.clear()
        viewModelScope.launch {
            getFavoriteUseCase()
                .onStart {
//                    _state.update {
//                        it.copy(
//                            isLoading = true,
//                            error = null
//                        )
//                    }
                }
                .onEach {
                    getEmployeesUseCase().onSuccess { empls ->
                        empls.forEach {
                            employees[it.id] = it
                        }
                    }
                }
                .collect { favorites ->
                    favorites.forEach { employee ->
                        employees[employee.id] = employee
                    }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            employees = employees.values.toList(),
                            error = getEmployeesUseCase().exceptionOrNull()
                        )
                    }
                    Log.i("EmployeesViewModel", "$favorites")
                }
        }
    }

    fun addFavorite(employee: Employee) {
        viewModelScope.launch {
            addFavoriteUseCase(employee.copy(isFavorite = true))
        }
    }

    fun removeFavorite(employee: Employee) {
        viewModelScope.launch {
            removeFavoriteUseCase(employee)
            employees[employee.id] = employee.copy(isFavorite = false)
        }
    }

    fun refreshData() {
        getEmployees()
    }
}