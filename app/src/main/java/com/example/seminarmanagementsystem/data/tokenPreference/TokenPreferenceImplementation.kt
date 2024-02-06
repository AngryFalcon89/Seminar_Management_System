package com.example.seminarmanagementsystem.data.tokenPreference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.seminarmanagementsystem.domain.tokenPreference.TokenPreference
import com.example.seminarmanagementsystem.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class TokenPreferenceImplementation(private val dataStore: DataStore<Preferences>) :
    TokenPreference {
    override suspend fun saveToken(authToken: String) {
        dataStore.edit {
            it[PreferencesKeys.AUTH_TOKEN] = authToken
        }
    }

    override fun readAuthToken(): Flow<String?> = dataStore.data.map {
        it[PreferencesKeys.AUTH_TOKEN]
    }

    override fun readToken(): String? {
        return runBlocking {
            dataStore.data.map { preferences ->
                preferences[PreferencesKeys.AUTH_TOKEN]
            }.firstOrNull()
        }
    }

    override suspend fun saveAppEntry() {
        dataStore.edit {
            it[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            val appEntry = preferences[PreferencesKeys.APP_ENTRY] ?: false
            val authToken = preferences[PreferencesKeys.AUTH_TOKEN].isNullOrBlank().not()
            appEntry || authToken
        }
    }

}


private object PreferencesKeys {
    val AUTH_TOKEN = stringPreferencesKey(name = Constants.AUTH_TOKEN)
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)

}