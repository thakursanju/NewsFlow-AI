package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferencesRepository(private val context: Context) {
    private val dataStore = context.dataStore

    val isDarkMode: Flow<Boolean> = dataStore.data.map { it[DARK_MODE] ?: true }
    val country: Flow<String> = dataStore.data.map { it[COUNTRY] ?: "us" }

    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { it[DARK_MODE] = enabled }
    }

    suspend fun setCountry(country: String) {
        dataStore.edit { it[COUNTRY] = country }
    }

    companion object {
        private val DARK_MODE = booleanPreferencesKey("dark_mode")
        private val COUNTRY = stringPreferencesKey("country")
    }
}
