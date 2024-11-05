package com.alexc.ph.loginmynt.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.ph.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    private var _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(username: String, password: String) {
        if(username.isEmpty() || password.isEmpty()) {
            _loginUiState.value = LoginUiState.LoginError("No username or password")
        } else {
            viewModelScope.launch {
                _loginUiState.value = LoginUiState.Loading
                val success = loginRepository.login(username, password)
                if(success) {
                    _loginUiState.value = LoginUiState.LoginSuccessful(username)
                } else {
                    _loginUiState.value = LoginUiState.LoginError("Login Failed")
                }
            }
        }
    }

    fun setUiStateToIdle() {
        _loginUiState.value = LoginUiState.Idle
    }

    fun logout() {
        _loginUiState.value = LoginUiState.Idle
    }

}

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class LoginSuccessful(val user: String): LoginUiState()
    data class LoginError(val error: String): LoginUiState()
}