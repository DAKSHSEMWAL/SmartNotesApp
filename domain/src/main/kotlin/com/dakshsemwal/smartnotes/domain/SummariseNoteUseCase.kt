package com.dakshsemwal.smartnotes.domain

import com.dakshsemwal.smartnotes.data.GenerativeAiRepository
import javax.inject.Inject

class SummariseNoteUseCase @Inject constructor(
    private val repo: GenerativeAiRepository
) {
    suspend operator fun invoke(input: String) = repo.summarise(input)
}