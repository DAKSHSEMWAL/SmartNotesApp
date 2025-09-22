package com.dakshsemwal.smartnotes.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dakshsemwal.smartnotes.domain.SummariseNoteUseCase
import com.dakshsemwal.smartnotes.ui.states.SummariseUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummariseNoteViewModel @Inject constructor(
    private val summariseNote: SummariseNoteUseCase
) : ViewModel() {
    var uiState by mutableStateOf(SummariseUiState())
        private set

    fun onNoteInputChanged(text: String) {
        uiState = uiState.copy(noteInput = text)
    }

    fun onSummariseClick() {
        if (uiState.noteInput.isBlank()) return
        uiState = uiState.copy(isLoading = true, error = null)
        viewModelScope.launch {
           /* val result = summariseNote(uiState.noteInput)
            uiState = if (result.isSuccess) {
                uiState.copy(summary = result.getOrNull(), isLoading = false)
            } else {
                uiState.copy(error = result.exceptionOrNull()?.message, isLoading = false)
            }*/
        }
    }
}