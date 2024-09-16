package com.example.todoapp.ui.todo.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodosRepository
import com.example.todoapp.ui.todo.entry.TodoDetails
import com.example.todoapp.ui.todo.entry.TodoUiState
import com.example.todoapp.ui.todo.entry.toTodo
import com.example.todoapp.ui.todo.entry.toTodoUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TodoEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val todosRepository: TodosRepository
) : ViewModel() {

    var todoUiState by mutableStateOf(TodoUiState())

    private val todoId: Int = checkNotNull(savedStateHandle[TodoEditDestination.todoIdArg])

    init {
        viewModelScope.launch {
            todoUiState = todosRepository.getTodoStream(todoId)
                .filterNotNull()
                .first()
                .toTodoUiState(isEntryValid = true)
        }
    }

    suspend fun updateTodo() {
        if(validateInput(todoUiState.todoDetails)) {
            todosRepository.updateTodo(todoUiState.todoDetails.toTodo())
        }
    }

    fun updateUiState(todoDetails: TodoDetails) {
        todoUiState = TodoUiState(
            todoDetails = todoDetails, isEntryValid = validateInput(todoDetails)
        )
    }

    private fun validateInput(uiState: TodoDetails = todoUiState.todoDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank()
        }
    }
}
