package com.example.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.ui.home.HomeDestination
import com.example.todoapp.ui.home.HomeScreen
import com.example.todoapp.ui.todo.details.TodoDetailsDestination
import com.example.todoapp.ui.todo.details.TodoDetailsScreen
import com.example.todoapp.ui.todo.edit.TodoEditDestination
import com.example.todoapp.ui.todo.edit.TodoEditScreen
import com.example.todoapp.ui.todo.entry.TodoEntryDestination
import com.example.todoapp.ui.todo.entry.TodoEntryScreen

@Composable
fun TodoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToTodoEntry = { navController.navigate(TodoEntryDestination.route) },
                navigateToTodoUpdate = {
                    navController.navigate("${TodoDetailsDestination.route}/${it}")
                }
            )
        }

        composable(route = TodoEntryDestination.route) {
            TodoEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = TodoDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(TodoDetailsDestination.todoIdArg) {
                type = NavType.IntType
            })
        ) {
            TodoDetailsScreen(
                navigateToEditTodo = { navController.navigate("${TodoEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = TodoEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TodoEditDestination.todoIdArg) {
                type = NavType.IntType
            })
        ) {
            TodoEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}