package com.example.seminarmanagementsystem.domain.tokenPreference

import kotlinx.coroutines.flow.Flow

interface TokenPreference {
    suspend fun saveToken(authToken: String)
    fun readAuthToken(): Flow<String?>
    fun readToken(): String?
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}