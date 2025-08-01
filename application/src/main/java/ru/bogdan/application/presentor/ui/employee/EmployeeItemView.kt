package ru.bogdan.application.presentor.ui.employee

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import ru.bogdan.application.R
import ru.bogdan.application.ViewModelFactory
import ru.bogdan.application.domain.Employee
import ru.bogdan.application.navigate.Screen


@Composable
fun EmployeesList(
    modifier: Modifier = Modifier,
    factory: ViewModelFactory,
    onClick: (screen: Screen, id: String) -> Unit
) {
    val viewModel: EmployeesViewModel = viewModel(factory = factory)
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refreshData()
        Log.i("EmployeesViewModel", "EmployeesViewModel refreshData")
    }
    EmployeeListContent(
        modifier = modifier,
        state = state,
        viewModel = viewModel,
        onClick = onClick
    )
}

@Composable
fun EmployeeListContent(
    state: State<EmployeesState>,
    viewModel: EmployeesViewModel,
    modifier: Modifier = Modifier,
    onClick: (screen: Screen, id: String) -> Unit,
) {
    Log.i("EmployeesViewModel", "EmployeeListContent")
    state.value.error?.let {
        ShowToast(it)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (state.value.employees.isEmpty() && !state.value.isLoading) {
            Text(
                modifier = Modifier.align(Alignment.Center),
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
                            if (employee.isFavorite) {
                                viewModel.removeFavorite(employee)
                            } else {
                                viewModel.addFavorite(employee)
                            }
                        }
                    )
                }
            }
        }
        if (state.value.isLoading)
            CircularProgressIndicator()
    }
}

@Composable
fun EmployeeItemView(
    employee: Employee,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onCardClick: () -> Unit,
) {
    Card(
        modifier = modifier.padding(16.dp),
        border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(8.dp),
        onClick = onCardClick
    ) {
        Box {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.CenterVertically),
                    model = employee.photoUrl,
                    contentDescription = employee.name,
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = employee.name,
                        style = TextStyle(color = Color.Black, fontSize = 30.sp, fontWeight = Bold),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Text(
                        text = employee.surname,
                        style = TextStyle(color = Color.Black, fontSize = 30.sp, fontWeight = Bold),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
            IconButton(
                onClick = onFavoriteClick, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = if (employee.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "",
                    tint = Color.Red
                )
            }

        }
    }
}

@Composable
fun ShowToast(throwable: Throwable) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        }
    }
}