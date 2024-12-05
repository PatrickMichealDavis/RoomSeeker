package com.example.tusroomseeker.component.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class LoginViewModel(application: Application) : AndroidViewModel(application) {

  private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: MutableLiveData<AuthState> = _authState

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    init{
        checkAuthStatus()
    }

    fun login(email:String,password:String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password must not be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "An error occurred")
                }
            }
    }

    fun signup(email:String,password:String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password must not be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "An error occurred")
                }
            }
    }

    fun signout(){
        auth.signOut()
       _authState.value = AuthState.Unauthenticated

    }

}

sealed class AuthState{
    object Unauthenticated: AuthState()
    object Authenticated: AuthState()
    object Loading: AuthState()
    data class Error(val message: String): AuthState()
}
