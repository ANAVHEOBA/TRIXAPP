package com.example.trixapp.model

data class Task(
    val title: String,
    val description: String,
    val progress: String,
    val points: Int,
    val isCompleted: Boolean
)

enum class TaskType {
    ONE_TIME, DAILY, COMPLETED
}