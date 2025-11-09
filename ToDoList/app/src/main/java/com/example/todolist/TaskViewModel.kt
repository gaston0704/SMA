package com.example.todolist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todolist.model.Task

class TaskViewModel : ViewModel(){
    private var nextId = 0
    val tasks  = mutableStateListOf<Task>()

    fun addTask(title: String, description: String?){
        tasks.add(
            Task(
                id = nextId++,
                title = title,
                description = description
            )
        )
    }

    fun updateTask(id: Int, newTitle: String, newDescription: String?){
        val task = tasks.find{ it.id == id } ?: return
        task.title = newTitle
        task.description = newDescription
    }

    fun deleteTask(id: Int){
        tasks.removeAll{ it.id == id }
    }

    fun toggleCompleted(id: Int){
        val index = tasks.indexOfFirst { it.id == id }
        if (index != -1) {
            val task = tasks[index]
            tasks[index] = task.copy(isCompleted = !task.isCompleted)
        }
    }
}