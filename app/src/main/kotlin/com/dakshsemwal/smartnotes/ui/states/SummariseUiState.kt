package com.dakshsemwal.smartnotes.ui.states

data class SummariseUiState(
    val noteInput: String = "",
    val summary: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)