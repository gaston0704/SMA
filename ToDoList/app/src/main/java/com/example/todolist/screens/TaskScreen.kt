package com.example.todolist.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.TaskViewModel
import com.example.todolist.model.Task
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()){
    val tasks = viewModel.tasks

    var showAddDialog by remember { mutableStateOf(false) }
    var editingTask by remember { mutableStateOf<Task?>(null) }

    Column(Modifier.fillMaxSize().padding(16.dp)){

        Button(onClick = { showAddDialog = true }) {
            Text("Add task")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn{
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onToggle = { viewModel.toggleCompleted(task.id) },
                    onEdit = { editingTask = task },
                    onDelete = { viewModel.deleteTask(task.id) }
                )
            }
        }
    }

    if(showAddDialog){
        TaskDialog(
            title = "Add Task",
            initialTitle = "",
            initialDesc = "",
            onDismiss = { showAddDialog = false },
            onConfirm = { t, d ->
                viewModel.addTask(t, d)
                showAddDialog = false
            }
        )
    }

    editingTask?.let { task ->
        TaskDialog(
            title = "Edit Task",
            initialTitle = task.title,
            initialDesc = task.description ?: "",
            onDismiss = { editingTask = null },
            onConfirm = { newT, newD ->
                viewModel.updateTask(task.id, newT, newD)
                editingTask = null
            }
        )
    }
}