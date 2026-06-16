package com.example.domain.repository

import com.example.domain.model.Article
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

// Simulated Spring Boot Backend REST API Service
// This interface defines the endpoints for a Spring Boot backend
// used to store user preferences and favorite articles in PostgreSQL.
interface BackendApiService {
    
    // Syncs the user's preferred news region/country
    @POST("api/v1/users/preferences")
    suspend fun syncPreferences(@Body request: SyncPreferencesRequest)
    
    // Retrieves the user's preferred news region
    @GET("api/v1/users/preferences")
    suspend fun getPreferences(): SyncPreferencesResponse

    // Syncs a saved favorite article cross-platform
    @POST("api/v1/users/favorites")
    suspend fun addFavorite(@Body request: SyncFavoriteRequest)
    
    // Removes a favorite article
    @POST("api/v1/users/favorites/remove")
    suspend fun removeFavorite(@Body request: SyncFavoriteRequest)
}

data class SyncPreferencesRequest(
    val userId: String,
    val country: String,
    val isDarkMode: Boolean
)

data class SyncPreferencesResponse(
    val country: String,
    val isDarkMode: Boolean
)

data class SyncFavoriteRequest(
    val userId: String,
    val article: Article
)
