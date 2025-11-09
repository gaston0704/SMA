package com.example.todolist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskDialog(
    title: String,
    initialTitle: String,
    initialDesc: String,
    onDismiss: () -> Unit,
    onConfirm: (String, String?) -> Unit
) {
    var t by remember { mutableStateOf(initialTitle) }
    var d by remember { mutableStateOf(initialDesc) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                OutlinedTextField(value = t, onValueChange = { t = it }, label = { Text("Title") })
                OutlinedTextField(value = d, onValueChange = { d = it }, label = { Text("Description") })
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(t, d.takeIf { it.isNotBlank() } )
                }
            ) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
