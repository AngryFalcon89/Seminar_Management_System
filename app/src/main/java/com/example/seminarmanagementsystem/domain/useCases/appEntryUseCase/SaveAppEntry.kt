package com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase

import com.example.seminarmanagementsystem.domain.tokenPreference.TokenPreference

class SaveAppEntry(
    private val tokenPreference: TokenPreference
) {

    suspend operator fun invoke() {
        tokenPreference.saveAppEntry()
    }
}