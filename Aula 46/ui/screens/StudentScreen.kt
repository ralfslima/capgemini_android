package com.example.api2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api2.ui.components.StudentForm
import com.example.api2.ui.components.StudentItem
import com.example.api2.viewModel.StudentViewModel

@Composable
fun StudentScreen(viewModel: StudentViewModel) {
    Column(modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(20.dp)) {
        StudentForm(viewModel) // Chama o formulário

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(viewModel.students) { student ->
                StudentItem(
                    student = student,
                    onDelete = { student.id?.let { viewModel.removeStudent(it) } }
                )
            }
        }
    }
}