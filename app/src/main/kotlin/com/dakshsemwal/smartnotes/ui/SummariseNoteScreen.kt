package com.dakshsemwal.smartnotes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SummariseNoteScreen(viewModel: SummariseNoteViewModel = viewModel()) {
    val state = viewModel.uiState
    Column(Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        OutlinedTextField(
            value = state.noteInput,
            onValueChange = viewModel::onNoteInputChanged,
            label = { Text("Paste or type your text") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            enabled = !state.isLoading && state.noteInput.isNotBlank(),
            onClick = viewModel::onSummariseClick
        ) { Text(if (state.isLoading) "Summarising…" else "Summarise") }

        state.summary?.let {
            Spacer(Modifier.height(16.dp))
            Text(it)
        }
        state.error?.let {
            Spacer(Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
