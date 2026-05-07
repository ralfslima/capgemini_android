package com.example.api2.ui.components;

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api2.data.model.Student


@SuppressLint("DefaultLocale") // <- Remover o warning da formatação da média
@Composable
fun StudentItem(student: Student, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("Aluno(a): ${student.name}")
                Text("Média: ${String.format("%.2f", student.mean ?: 0.0)}")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Remover")
            }
        }
    }
}