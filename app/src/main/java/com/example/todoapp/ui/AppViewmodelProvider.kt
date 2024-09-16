package com.example.todoapp.ui

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoapp.TodoApplication
import com.example.todoapp.ui.home.HomeViewModel
import com.example.todoapp.ui.todo.details.TodoDetailsViewModel
import com.example.todoapp.ui.todo.edit.TodoEditViewModel
import com.example.todoapp.ui.todo.entry.TodoEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            TodoEditViewModel(
                this.createSavedStateHandle(),
                todoApplication().container.todosRepository
            )
        }

        initializer {
            TodoEntryViewModel(todoApplication().container.todosRepository)
        }

        initializer {
            TodoDetailsViewModel(
                this.createSavedStateHandle(),
                todoApplication().container.todosRepository
            )
        }

        initializer {
            HomeViewModel(todoApplication().container.todosRepository)
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)