package com.example.tusroomseeker.component.login

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import java.util.regex.Pattern


class LoginViewModel {
    var uiState = mutableStateOf(LoginDetails())
        private set

    private val email: String
        get() = uiState.value.email
    private val password: String
        get() = uiState.value.password

    private val MIN_PASS_LENGTH = 6
    private val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{$MIN_PASS_LENGTH,}$"

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            showSnackbar("Invalid email address")
            return
        }

        if (!password.isValidPassword()) {
            showSnackbar("Password must contain at least $MIN_PASS_LENGTH characters, including uppercase, lowercase, and digits")
            return
        }

        openAndPopUp("HomeScreen", "LoginScreen")
    }



    private fun String.isValidEmail(): Boolean {
        return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.isValidPassword(): Boolean {
        return this.isNotBlank() &&
                this.length >= MIN_PASS_LENGTH &&
                Pattern.compile(PASS_PATTERN).matcher(this).matches()
    }

    private fun showSnackbar(message: String) {
        // Implement a Snackbar mechanism, or connect with a Composable
        println(message) // Placeholder for actual Snackbar
    }
}
