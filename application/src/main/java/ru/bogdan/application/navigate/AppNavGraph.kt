package ru.bogdan.application.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.bogdan.application.navigate.Screen.Companion.KEY_EMPLOYEE_ID

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    mainContent: @Composable () -> Unit,
    favoriteContent: @Composable () -> Unit,
    employeeContent: @Composable (String) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Employees.route
    ) {
        composable(Screen.Employees.route) {
            mainContent()
        }

        composable(Screen.Favorite.route) {
            favoriteContent()
        }

        composable(Screen.EmployeeDetail.route) {
            val id = it.arguments?.getString(KEY_EMPLOYEE_ID) ?: error("Employee ID is missing")
            employeeContent(id)
        }
    }
}