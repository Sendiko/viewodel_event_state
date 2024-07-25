package com.sendiko.vmse

/**
 *
 * Sebuah class untuk menampung kondisi kondisi yang akan ada di Screen.
 *
 * seperti: text untuk TextField maupun Text biasa, List content dari screen,
 * kondisi Switch, kondisi Checkbox, dan lainnya.
 *
 * */
data class RegisterScreenState(
    val username: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val agreeToTermsAndServices: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val notificationMessage: String = "",
)
