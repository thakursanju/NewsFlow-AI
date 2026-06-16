package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private var auth: FirebaseAuth? = null

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    init {
        try {
            auth = FirebaseAuth.getInstance()
        } catch (e: Exception) {
            // Firebase may not be initialized if google-services.json is missing
        }
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        val currentUser = auth?.currentUser
        if (currentUser != null) {
            _authState.value = AuthState.Authenticated(currentUser.uid)
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                if (auth != null) {
                    val result = auth!!.signInWithEmailAndPassword(email, password).await()
                    _authState.value = AuthState.Authenticated(result.user?.uid ?: "")
                } else {
                    // Simulate login if Firebase isn't configured
                    kotlinx.coroutines.delay(1000)
                    _authState.value = AuthState.Authenticated("simulated_user_id")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Authentication failed")
            }
        }
    }

    fun register(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                if (auth != null) {
                    val result = auth!!.createUserWithEmailAndPassword(email, password).await()
                    _authState.value = AuthState.Authenticated(result.user?.uid ?: "")
                } else {
                    // Simulate registration if Firebase isn't configured
                    kotlinx.coroutines.delay(1000)
                    _authState.value = AuthState.Authenticated("simulated_user_id")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun signInWithGoogle() {
        // In a real app, this would use Credential Manager to get the Google ID Token
        // then pass that token to FirebaseAuth.signInWithCredential()
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            kotlinx.coroutines.delay(1500)
            _authState.value = AuthState.Authenticated("simulated_google_user_id")
        }
    }

    fun logout() {
        auth?.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Authenticated(val userId: String) : AuthState()
        object Unauthenticated : AuthState()
        data class Error(val message: String) : AuthState()
    }
}
