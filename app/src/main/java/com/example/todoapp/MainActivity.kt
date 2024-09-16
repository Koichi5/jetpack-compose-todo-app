package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.todoapp.data.TodosRepository
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Tag", "Main Activity Fired")
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TodoApp()
                }
            }
        }
    }
}
