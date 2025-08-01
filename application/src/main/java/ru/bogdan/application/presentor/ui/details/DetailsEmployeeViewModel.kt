package ru.bogdan.application.presentor.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.bogdan.application.domain.useCases.DeleteEmployeeUseCase
import ru.bogdan.application.domain.useCases.GetEmployeeByIdUseCase
import ru.bogdan.application.domain.useCases.GetFavoriteByIdUseCase
import ru.bogdan.application.domain.useCases.RemoveFavoriteUseCase
import javax.inject.Inject
import javax.inject.Named

class DetailsEmployeeViewModel @Inject constructor(
    @Named("employeeId") private val employeeId: String,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase,
    private val deleteFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.update { it.copy(error = throwable, isLoading = false) }
    }

    init {
        getEmployeeById(employeeId)
    }

    private fun getEmployeeById(employeeId: String) {
        viewModelScope.launch(
            coroutineExceptionHandler
        ) {
            val tempFavorite = getFavoriteByIdUseCase(employeeId)
            if (tempFavorite != null) {
                _state.value = state.value.copy(employee = tempFavorite, isLoading = false, error = null)
            } else {
                _state.value = state.value.copy(
                    employee = getEmployeeByIdUseCase.invoke(employeeId).getOrThrow(),
                    isLoading = false
                )
            }
        }
    }

    fun deleteEmployee() {
        val employee = _state.value.employee
        viewModelScope.launch(
            coroutineExceptionHandler
        ) {
            if (employee.isFavorite) {
                deleteFavoriteUseCase(employee)
                deleteEmployeeUseCase(employee)
            } else {
                deleteEmployeeUseCase(employee)
            }
        }
    }
}