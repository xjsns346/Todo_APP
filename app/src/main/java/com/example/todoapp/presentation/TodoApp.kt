package com.example.todoapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.presentation.screen.home.HomeScreen
import com.example.todoapp.presentation.screen.plans.ExtraPlanScreen
import com.example.todoapp.presentation.screen.reminders.ReminderScreen
import com.example.todoapp.presentation.screen.tasks.TaskListScreen
import com.example.todoapp.util.Constants.Navigation

@Composable
fun TodoApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        BottomNavItem(
            route = Navigation.HOME_ROUTE,
            label = "首页",
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            route = Navigation.TASKS_ROUTE,
            label = "每日任务",
            icon = Icons.Default.CheckCircle
        ),
        BottomNavItem(
            route = Navigation.ANNIVERSARIES_ROUTE,
            label = "纪念日",
            icon = Icons.Default.DateRange
        ),
        BottomNavItem(
            route = Navigation.PLANS_ROUTE,
            label = "拓展事项",
            icon = Icons.Default.List
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Navigation.HOME_ROUTE,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Navigation.HOME_ROUTE) {
                HomeScreen(
                    onNavigateToTasks = { navController.navigate(Navigation.TASKS_ROUTE) },
                    onNavigateToAnniversaries = { navController.navigate(Navigation.ANNIVERSARIES_ROUTE) },
                    onNavigateToPlans = { navController.navigate(Navigation.PLANS_ROUTE) }
                )
            }
            composable(Navigation.TASKS_ROUTE) {
                TaskListScreen()
            }
            composable(Navigation.ANNIVERSARIES_ROUTE) {
                ReminderScreen()
            }
            composable(Navigation.PLANS_ROUTE) {
                ExtraPlanScreen()
            }
        }
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)
