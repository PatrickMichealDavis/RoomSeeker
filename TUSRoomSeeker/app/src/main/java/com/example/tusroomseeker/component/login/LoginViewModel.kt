package com.example.tusroomseeker.component.login

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tusroomseeker.component.profile.Profile
import com.example.tusroomseeker.database.RoomSeekerRepository
import com.example.tusroomseeker.database.RoomSeekerRoomDatabase
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class LoginViewModel(application: Application) : AndroidViewModel(application) {

  private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val repository = RoomSeekerRepository(application)



    private val _authState = MutableLiveData<AuthState>()
    val authState: MutableLiveData<AuthState> = _authState

    var loggedInEmail = mutableStateOf("")


    
    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
            loggedInEmail.value = auth.currentUser?.email ?: ""
        }
    }

    init{
        checkAuthStatus()
        //RoomSeekerRoomDatabase.getDatabase(application, clearDatabase = true)

    }

    fun getLoggedInUser(): LiveData<Profile> {
        val email = loggedInEmail.value
        return if (email.isNotEmpty()) {
            repository.fetchProfileByEmail(email)
        } else {
            MutableLiveData(
                Profile(
                    id = 0,

                    name = "",
                    gender = "",
                    email = "",
                    knum = "",
                    userType = 0
                ))
        }
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
        loggedInEmail.value = ""

    }

}

sealed class AuthState{
    object Unauthenticated: AuthState()
    object Authenticated: AuthState()
    object Loading: AuthState()
    data class Error(val message: String): AuthState()
}
