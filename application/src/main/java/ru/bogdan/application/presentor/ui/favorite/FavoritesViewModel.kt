package ru.bogdan.application.presentor.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.bogdan.application.domain.Employee
import ru.bogdan.application.domain.useCases.GetFavoriteUseCase
import ru.bogdan.application.domain.useCases.RemoveFavoriteUseCase
import ru.bogdan.application.presentor.ui.employee.EmployeesState
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(EmployeesState())
    val state = _state.asStateFlow()

    init {
        getEmployees()
    }

    private fun getEmployees() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = throwable,
                    )
                }
            }
        ) {
            getFavoriteUseCase()
                .collect { favorites ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            employees = favorites
                        )
                    }
                }
        }
    }

    fun removeFavorite(employee: Employee) {
        viewModelScope.launch {
            removeFavoriteUseCase(employee)
        }
    }

}