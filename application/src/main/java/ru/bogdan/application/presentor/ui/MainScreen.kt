package ru.bogdan.application.presentor.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.bogdan.application.R
import ru.bogdan.application.ViewModelFactory
import ru.bogdan.application.navigate.AppNavGraph
import ru.bogdan.application.navigate.Screen
import ru.bogdan.application.presentor.ui.details.DetailsEmployee
import ru.bogdan.application.presentor.ui.employee.EmployeesList
import ru.bogdan.application.presentor.ui.favorite.FavoritesList

@Composable
fun MainApplicationScreen(
    factory: ViewModelFactory,
    modifier: Modifier = Modifier,
) {
    val navHostController = rememberNavController()
    val currentScreen = navHostController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentScreen == Screen.Favorite.route || currentScreen == Screen.Employees.route) {
                BottomBar(
                    modifier = Modifier.fillMaxWidth(),
                    currentScreen = currentScreen,
                    onClick = { screen ->
                        navHostController.navigate(screen.route) {
                            launchSingleTop = true
                            popUpTo(screen.route) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        },
        modifier = modifier,
        content = { paddingValues ->
            AppNavGraph(
                navHostController = navHostController,
                mainContent = {
                    EmployeesList(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        factory = factory,
                        onClick = { screen, id ->
                            navHostController.navigate((screen as Screen.EmployeeDetail).getRouteWithArgs(id)) {
                                launchSingleTop = true

                            }
                        }
                    )
                },
                favoriteContent = {
                    FavoritesList(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        factory = factory,
                        onClick = { screen, id ->
                            navHostController.navigate((screen as Screen.EmployeeDetail).getRouteWithArgs(id)) {
                                launchSingleTop = true
                            }
                        }
                    )
                },
                employeeContent = {
                    DetailsEmployee(
                        modifier = Modifier.fillMaxWidth(),
                        employeeId = it,
                        onPressBack = {
                            navHostController.popBackStack()
                        }
                    )
                }
            )
        }
    )
}


@Composable
fun BottomBar(
    currentScreen: String?,
    modifier: Modifier = Modifier,
    onClick: (Screen) -> Unit
) {
    val items = listOf(

        NavItem.EmployeesItemNavigate(
            title = stringResource(R.string.employees),
            imageVector = Icons.Default.Face,
        ),
        NavItem.FavoriteNavigate(
            title = stringResource(R.string.favorite),
            imageVector = Icons.Default.Favorite,
        )
    )
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = item.imageVector,
                            contentDescription = item.title
                        )
                        Text(
                            text = item.title,
                        )
                    }
                },
                selected = currentScreen == item.screen.route,
                onClick = {
                    onClick(item.screen)
                }
            )
        }
    }
}