package com.sendiko.vmse

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sendiko.content_box_with_notification.ContentBoxWithNotification
import com.sendiko.vmse.ui.theme.ViewModelEventAndStateTheme

/**
 *
 * UI Screen composable kalian
 *
 * @param state untuk memberikan kondisi-kondisi yang ada di screen ini
 * @param onEvent untuk berkomunikasi dengan ViewModel tentang aksi yang dilakukan user.
 *
 * */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    state: RegisterScreenState,
    onEvent: (RegisterScreenEvent) -> Unit,
) {
    LaunchedEffect(key1 = state.notificationMessage, key2 = state.isError) {
        if (state.notificationMessage.isNotBlank() || state.isError) {
            onEvent(RegisterScreenEvent.OnClearNotification)
        }
    }
    ContentBoxWithNotification(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isError
    ) {
        Scaffold {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Register",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Please fill out to create your new account",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Username",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.username,
                    onValueChange = { newText ->
                        onEvent(RegisterScreenEvent.OnUsernameChanged(newText))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Phone Number",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.phoneNumber,
                    onValueChange = {
                        onEvent(RegisterScreenEvent.OnPhoneNumberChanged(it))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Phone,
                            contentDescription = null
                        )
                    },
                    prefix = {
                        Text(
                            text = "+62",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Password",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.password,
                    onValueChange = {
                        onEvent(RegisterScreenEvent.OnPasswordChanged(it))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (state.isPasswordVisible) {
                            IconButton(onClick = {
                                onEvent(
                                    RegisterScreenEvent.OnPasswordVisibilityChanged(
                                        false
                                    )
                                )
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Visibility,
                                    contentDescription = null
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                onEvent(
                                    RegisterScreenEvent.OnPasswordVisibilityChanged(
                                        true
                                    )
                                )
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.agreeToTermsAndServices,
                        onCheckedChange = {
                            onEvent(RegisterScreenEvent.OnAgreeTermsAndServicesToggle(it))
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "I Agree to the Terms and Services that this system provide.",
                        textDecoration = TextDecoration.Underline,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onEvent(RegisterScreenEvent.OnRegister) },
                    enabled = !state.isLoading
                ) {
                    Text(text = if (state.isLoading) "Loading.." else "Register")
                }
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPrev() {
    ViewModelEventAndStateTheme(
        darkTheme = true
    ) {
        RegisterScreen(state = RegisterScreenState()) {

        }
    }
}