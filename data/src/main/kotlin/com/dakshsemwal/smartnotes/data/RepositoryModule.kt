package com.dakshsemwal.smartnotes.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindGenerativeAiRepository(
        impl: GenerativeAiRepositoryImpl
    ): GenerativeAiRepository
}