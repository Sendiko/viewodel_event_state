package com.sendiko.vmse

/**
 *
 * Sebuah sealed class untuk menentukan Event/Action yang bisa terjadi dalam sebuah Screen.
 *
 * `data class` digunakan jika sebuah aksi perlu memberikan data dari Screen ke ViewModel.
 *
 * `data object` digunakan jika sebuah aksi tidak perlu memberikan data dari Screen ke ViewModel
 *
 * */
sealed class RegisterScreenEvent {
    data class OnUsernameChanged(val username: String): RegisterScreenEvent()
    data class OnPhoneNumberChanged(val phoneNumber: String): RegisterScreenEvent()
    data class OnPasswordChanged(val password: String): RegisterScreenEvent()
    data class OnPasswordVisibilityChanged(val isVisible: Boolean): RegisterScreenEvent()
    data class OnAgreeTermsAndServicesToggle(val isAgree: Boolean): RegisterScreenEvent()
    data object OnRegister: RegisterScreenEvent()
    data object OnClearNotification: RegisterScreenEvent()
}
