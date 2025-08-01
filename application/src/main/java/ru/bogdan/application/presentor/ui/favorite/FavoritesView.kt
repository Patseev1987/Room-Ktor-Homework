package ru.bogdan.application.presentor.ui.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.bogdan.application.R
import ru.bogdan.application.ViewModelFactory
import ru.bogdan.application.domain.Employee
import ru.bogdan.application.navigate.Screen
import ru.bogdan.application.presentor.ui.employee.EmployeeItemView
import ru.bogdan.application.presentor.ui.employee.EmployeesState
import ru.bogdan.application.presentor.ui.employee.ShowToast


@Composable
fun FavoritesList(
    modifier: Modifier = Modifier,
    factory: ViewModelFactory,
    onClick: (screen: Screen, id: String) -> Unit,
) {
    val viewModel: FavoritesViewModel = viewModel(factory = factory)
    val state = viewModel.state.collectAsState()
    FavoriteListContent(
        modifier = modifier,
        state = state,
        onClick = onClick,
        onDeleteClick = {
            viewModel.removeFavorite(it)
        }
    )
}

@Composable
fun FavoriteListContent(
    state: State<EmployeesState>,
    modifier: Modifier = Modifier,
    onClick: (screen: Screen, id: String) -> Unit,
    onDeleteClick: (Employee) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        state.value.error?.let {
            ShowToast(it)
        }
        if (state.value.employees.isEmpty()) {
            Text(
                text = stringResource(R.string.employee_not_found),
                fontSize = 30.sp,
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(items = state.value.employees, key = { it.id }) { employee ->
                    EmployeeItemView(
                        modifier = Modifier.fillMaxWidth(),
                        employee = employee,
                        onCardClick = {
                            onClick(
                                Screen.EmployeeDetail, employee.id
                            )
                        },
                        onFavoriteClick = {
                            onDeleteClick(employee)
                        }
                    )
                }
            }
        }
        if (state.value.isLoading)
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
            )
    }
}
