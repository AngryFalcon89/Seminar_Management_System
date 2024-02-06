package com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase

import com.example.seminarmanagementsystem.domain.tokenPreference.TokenPreference
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val tokenPreference: TokenPreference
) {
    operator fun invoke(): Flow<Boolean> {
        return tokenPreference.readAppEntry()
    }
}