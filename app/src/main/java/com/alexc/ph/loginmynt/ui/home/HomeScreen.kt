package com.alexc.ph.loginmynt.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexc.ph.loginmynt.ui.login.LoginViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    logoutClick: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold (
        modifier = modifier,
        topBar = {
            GenericTopAppBar(
                navigateBack = {
                    loginViewModel.logout()
                    logoutClick()
                },
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun GenericTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigateBack: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = navigateBack,
            modifier = Modifier.padding(end = 4.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Logout,
                contentDescription = "logout"
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(logoutClick = {})
}