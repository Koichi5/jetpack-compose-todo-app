package com.example.todoapp.ui.todo.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodosRepository
import com.example.todoapp.ui.todo.entry.TodoDetails
import com.example.todoapp.ui.todo.entry.toTodo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TodoDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val todosRepository: TodosRepository
) : ViewModel() {
    private val todoId: Int = checkNotNull(savedStateHandle[TodoDetailsDestination.todoIdArg])

    val uiState: StateFlow<TodoDetailsUiState> = todosRepository.getTodoStream(todoId)
        .map { todo ->
            todo?.let {
                TodoDetailsUiState(
                    todoDetails = TodoDetails(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                    )
                )
            } ?: TodoDetailsUiState()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = TodoDetailsUiState()
        )

    suspend fun deleteTodo() {
        uiState.value.todoDetails.toTodo().let { todo ->
            todosRepository.deleteTodo(todo)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class TodoDetailsUiState(
    val isDone: Boolean = false,
    val todoDetails: TodoDetails = TodoDetails()
)
