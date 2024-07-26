package com.sendiko.vmse

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 *
 * Sebuah class yang digunakan untuk mengelola State atau Kondisi dan Event dari Screen.
 *
 * */
class RegisterScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.OnUsernameChanged ->
                onUsernameChanged(event.username)

            is RegisterScreenEvent.OnPhoneNumberChanged ->
                onPhoneNumberChanged(event.phoneNumber)

            is RegisterScreenEvent.OnPasswordChanged ->
                onPasswordChanged(event.password)

            is RegisterScreenEvent.OnPasswordVisibilityChanged ->
                onPasswordVisibilityChanged(event.isVisible)

            is RegisterScreenEvent.OnAgreeTermsAndServicesToggle ->
                onAgreeTermsAndServicesToggle(event.isAgree)

            RegisterScreenEvent.OnClearNotification ->
                onClearNotification()

            RegisterScreenEvent.OnRegister ->
                onRegister()
        }
    }

    private fun onRegister() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            delay(2000)
            when {
                state.value.username.isBlank() -> {
                    Log.i("FIELD_LOG", "onRegister: true")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            notificationMessage = "Username can't be empty"
                        )
                    }
                }

                state.value.phoneNumber.isBlank() -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            notificationMessage = "Phone number can't be empty"
                        )
                    }
                }

                state.value.password.isBlank() -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            notificationMessage = "Password can't be empty"
                        )
                    }
                }

                !state.value.agreeToTermsAndServices -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            notificationMessage = "You need to agree with our Terms and Services."
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            notificationMessage = "Registrasi berhasil!"
                        )
                    }
                }
            }
        }
    }

    private fun onClearNotification() {
        _state.update {
            it.copy(
                isLoading = false,
                notificationMessage = "",
                errorMessage = "",
                isError = false
            )
        }
    }

    private fun onAgreeTermsAndServicesToggle(isAgree: Boolean) {
        _state.update { it.copy(agreeToTermsAndServices = isAgree) }
    }

    private fun onPasswordVisibilityChanged(isVisible: Boolean) {
        _state.update { it.copy(isPasswordVisible = isVisible) }
    }

    private fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    private fun onPhoneNumberChanged(phoneNumber: String) {
        _state.update { it.copy(phoneNumber = phoneNumber) }
    }

    private fun onUsernameChanged(username: String) {
        _state.update { it.copy(username = username) }
    }

}

/**
 *
 * Flow:
 * 1. State -> Screen
 * 2. Event -> ViewModel
 * 3. ViewModel -> State
 * 4. State -> Screen
 *
 */
