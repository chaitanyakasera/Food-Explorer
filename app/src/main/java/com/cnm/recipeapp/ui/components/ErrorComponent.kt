package com.cnm.recipeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorComponent(message: String, click: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = message)
        Button(onClick = { click() }) {
            Text(text = "Refresh")
        }
    }
}