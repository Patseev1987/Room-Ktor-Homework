package ru.bogdan.application.navigate

sealed class Screen(val route: String) {
    object Employees : Screen(EMPLOYEES)
    object Favorite : Screen(FAVORITE)
    object EmployeeDetail : Screen(DETAILS) {
        private const val DETAILS_FOR_ARGS = "details"
        fun getRouteWithArgs(id: String) = "$DETAILS_FOR_ARGS/$id"
    }

    companion object {
        private const val EMPLOYEES = "employees"
        private const val FAVORITE = "favorite"
        const val KEY_EMPLOYEE_ID = "employeeId"
        private const val DETAILS = "details/{$KEY_EMPLOYEE_ID}"
    }
}