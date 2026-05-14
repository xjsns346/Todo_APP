package com.example.todoapp.util

object Constants {
    const val SHORT_TERM_TASK_RESET_HOUR = 0
    const val SHORT_TERM_TASK_RESET_MINUTE = 5
    const val REMINDER_ADVANCE_DAYS = 3
    const val ONE_DAY_MILLIS = 24L * 60 * 60 * 1000
    const val DATABASE_NAME = "todo_database"

    object Navigation {
        const val HOME_ROUTE = "home"
        const val TASKS_ROUTE = "tasks"
        const val ANNIVERSARIES_ROUTE = "anniversaries"
        const val PLANS_ROUTE = "plans"
        const val ADD_TASK_ROUTE = "add_task"
        const val ADD_ANNIVERSARY_ROUTE = "add_anniversary"
        const val ADD_PLAN_ROUTE = "add_plan"
    }

    object PreferenceKeys {
        const val FIRST_LAUNCH = "first_launch"
        const val NOTIFICATION_ENABLED = "notification_enabled"
    }
}
