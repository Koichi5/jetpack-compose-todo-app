package com.example.todoapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.TodoNavHost

@Composable
fun TodoApp(navController: NavHostController = rememberNavController()) {
    TodoNavHost(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopAppBar(
    title: String,
    canNavigationBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
    ) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigationBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}