package com.example.todoapp.presentation.screen.reminders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.entity.Reminder
import com.example.todoapp.domain.usecase.ReminderUseCases
import com.example.todoapp.worker.WorkerManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCases: ReminderUseCases,
    private val workerManager: WorkerManager
) : ViewModel() {
    
    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders: StateFlow<List<Reminder>> = _reminders
    
    init {
        loadReminders()
    }
    
    fun loadReminders() {
        viewModelScope.launch {
            reminderUseCases.getAllReminders().collectLatest { reminderList ->
                _reminders.value = reminderList
            }
        }
    }
    
    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderUseCases.deleteReminder(reminder)
            workerManager.cancelReminderNotification(reminder.id)
        }
    }
    
    fun createReminder(title: String, date: Long) {
        viewModelScope.launch {
            val reminderId = reminderUseCases.createReminder(title, date)
            workerManager.scheduleReminderNotification(reminderId, date)
        }
    }
    
    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderUseCases.updateReminder(reminder)
            workerManager.cancelReminderNotification(reminder.id)
            workerManager.scheduleReminderNotification(reminder.id, reminder.date)
        }
    }
}