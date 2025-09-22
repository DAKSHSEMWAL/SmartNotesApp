package com.dakshsemwal.smartnotes.data

interface GenerativeAiRepository {
    suspend fun summarise(text: String): Result<String>
}