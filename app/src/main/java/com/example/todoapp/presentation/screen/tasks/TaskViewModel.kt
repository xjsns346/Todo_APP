package com.example.todoapp.presentation.screen.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.entity.Task
import com.example.todoapp.domain.usecase.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {
    
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks
    
    init {
        loadTasks()
    }
    
    fun loadTasks() {
        viewModelScope.launch {
            taskUseCases.getAllTasks().collectLatest { taskList ->
                _tasks.value = taskList
            }
        }
    }
    
    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            taskUseCases.toggleTaskCompletion(
                taskId = task.id,
                completed = !task.isCompleted
            )
        }
    }
    
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskUseCases.deleteTask(task)
        }
    }
    
    fun createTask(
        title: String,
        description: String? = null,
        isShortTerm: Boolean = false,
        durationDays: Int? = null,
        keepHistory: Boolean = true,
        dueDate: Long? = null
    ) {
        viewModelScope.launch {
            taskUseCases.createTask(
                title = title,
                description = description,
                isShortTerm = isShortTerm,
                durationDays = durationDays,
                keepHistory = keepHistory,
                dueDate = dueDate
            )
        }
    }
    
    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskUseCases.updateTask(task)
        }
    }
}