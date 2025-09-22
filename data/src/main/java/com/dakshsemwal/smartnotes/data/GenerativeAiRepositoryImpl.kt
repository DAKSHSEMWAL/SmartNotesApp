package com.dakshsemwal.smartnotes.data

import jakarta.inject.Inject

class GenerativeAiRepositoryImpl @Inject constructor(
    private val summarizationClient: SummarizationClient
) : GenerativeAiRepository {
    override suspend fun summarise(text: String): Result<String> =
        summarizationClient.summarise(text)
}