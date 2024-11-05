package com.alexc.ph.loginmynt.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexc.ph.loginmynt.R

val SmallDp = 8.dp
val MediumDp = 16.dp

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    if(loginUiState is LoginUiState.Loading) {
        LoadingScreen()
    } else {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            loginError = loginUiState is LoginUiState.LoginError,
            onLoginErrorDismiss = {
                viewModel.setUiStateToIdle()
            },
            loginClick = viewModel::login
        )
    }

    LaunchedEffect(loginUiState) {
        if(loginUiState is LoginUiState.LoginSuccessful) {
            navigateToHome()
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginError: Boolean = false,
    onLoginErrorDismiss:() -> Unit = {},
    loginClick: (username: String, password: String) -> Unit = { _, _ -> }
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(MediumDp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = {
                username = it
                if(loginError) onLoginErrorDismiss()
            },
            isError = loginError,
            label = { Text(text = stringResource(R.string.username), style = MaterialTheme.typography.bodyMedium) },
            minLines = 1,
            shape = RoundedCornerShape(SmallDp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(SmallDp))
        TextField(
            value = password,
            onValueChange = {
                password = it
                if(loginError) onLoginErrorDismiss()
            },
            isError = loginError,
            label = { Text(text = stringResource(R.string.password), style = MaterialTheme.typography.bodyMedium) },
            minLines = 1,
            shape = RoundedCornerShape(SmallDp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if(isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible },
                ) {
                    Icon(
                        imageVector = icon,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(MediumDp))

        if(loginError) {
            Text(
                text = stringResource(R.string.login_error),
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = MediumDp)
            )
        }

        Button(
            onClick = { loginClick(username, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        Spacer(modifier = Modifier.height(MediumDp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
