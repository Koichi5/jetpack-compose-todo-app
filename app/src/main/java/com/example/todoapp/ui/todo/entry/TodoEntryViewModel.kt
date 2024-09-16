package com.example.todoapp.ui.todo.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Todo
import com.example.todoapp.data.TodosRepository

class TodoEntryViewModel(private val todosRepository: TodosRepository) : ViewModel() {
    var todoUiState by mutableStateOf(TodoUiState())

    fun updateUiState(todoDetails: TodoDetails) {
        todoUiState = TodoUiState(
            todoDetails = todoDetails,
            isEntryValid = validateInput(todoDetails)
        )
    }

    suspend fun saveTodo() {
        if(validateInput()) {
            todosRepository.insertTodo(todoUiState.todoDetails.toTodo())
        }
    }

    private fun validateInput(uiState: TodoDetails = todoUiState.todoDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank()
        }
    }
}

data class TodoUiState(
    val todoDetails: TodoDetails = TodoDetails(),
    val isEntryValid: Boolean = false
)

data class TodoDetails(
    val id: Int = 0,
    val title: String = "",
    val description: String = ""
)

fun TodoDetails.toTodo(): Todo = Todo(
    id = id,
    title = title,
    description = description
)

fun Todo.toTodoUiState(isEntryValid: Boolean = false): TodoUiState = TodoUiState(
    todoDetails = this.toTodoDetails(),
    isEntryValid = isEntryValid
)

fun Todo.toTodoDetails(): TodoDetails = TodoDetails(
    id = id,
    title = title,
    description = description
)
