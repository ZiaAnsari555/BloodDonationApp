package com.villarica.villarica.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

// Create DataStore extension property
val Context.dataStore by preferencesDataStore(name = "wearhouse_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val DEVICE_REGISTERED = booleanPreferencesKey("device_registered")
        private val DEVICE_CODE = stringPreferencesKey("device_code")
        private val INDEX_FILE_PATH = stringPreferencesKey("index_file_path")
    }

    // Save DeviceCode
    suspend fun saveDeviceCode(deviceCode: String) {
        context.dataStore.edit { preferences ->
            preferences[DEVICE_CODE] = deviceCode
        }
    }

    // Retrieve DeviceCode as Flow
    val deviceCodeUpdate: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[DEVICE_CODE] // Returns stored username
        }

    // Retrieve DeviceCode as Data
    suspend fun getDeviceCode(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[DEVICE_CODE]
        }.firstOrNull()
    }


    // Save IndexFilePath
    suspend fun saveIndexFilePath(indexFilePath: String) {
        context.dataStore.edit { preferences ->
            preferences[INDEX_FILE_PATH] = indexFilePath
        }
    }

    // Retrieve IndexFilePath as Data
    suspend fun getIndexFilePath(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[INDEX_FILE_PATH]
        }.firstOrNull()
    }

    // Save DeviceCode
    suspend fun setIsRegistered(isRegistered: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DEVICE_REGISTERED] = isRegistered
        }
    }

    // Retrieve DeviceCode as Data
    suspend fun getIsRegistered(): Boolean? {
        return context.dataStore.data.map { preferences ->
            preferences[DEVICE_REGISTERED]
        }.firstOrNull()
    }

}