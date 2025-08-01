package ru.bogdan.application.presentor.ui

import androidx.compose.ui.graphics.vector.ImageVector
import ru.bogdan.application.navigate.Screen


sealed class NavItem(
    val screen: Screen,
    val title: String,
    val imageVector: ImageVector
) {
    class EmployeesItemNavigate(title: String, imageVector: ImageVector) : NavItem(
        screen = Screen.Employees,
        title = title,
        imageVector = imageVector
    )

    class FavoriteNavigate(title: String, imageVector: ImageVector) : NavItem(
        screen = Screen.Favorite,
        title = title,
        imageVector = imageVector
    )
}