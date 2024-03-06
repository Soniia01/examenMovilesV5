package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.examenmovilesrecuperacionv5_soniasanchez.R

@Composable
fun LoginActivity(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginDone: () -> Unit,
    bottomNavigationBar: @Composable () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()


    Content(
        state,
        bottomNavigationBar,
        onLoginDone,
        { viewModel.event(LoginEvents.login()) },
        { viewModel.event(LoginEvents.setPasswordState(it)) },
        { viewModel.event(LoginEvents.setUserState(it)) },
        { viewModel.event(LoginEvents.Error) }
    )
}


@Composable
fun Content(
    state: LoginState,
    bottomNavigationBar: @Composable () -> Unit = {},
    onLoginDone: () -> Unit,
    clickLogin: () -> Unit,
    cambiarPassw: (String) -> Unit,
    cambiarUser: (String) -> Unit,
    errorVisto: () -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.login) {
        if (state.login) {
            onLoginDone()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomNavigationBar,
    ) { innerPadding ->
        LaunchedEffect(state.error) {
            state.error?.let {
                snackbarHostState.showSnackbar(
                    message = state.error,
                    actionLabel = "Dismiss",
                    duration = SnackbarDuration.Short
                )
                errorVisto()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Customized UI components
            UserField(state.username, cambiarUser)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_16dp)))
            PasswordField(state.password, cambiarPassw)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_16dp)))
            LoginButton(clickLogin)
        }
    }
}

@Composable
fun UserField(username: String, onUserNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = username,
        onValueChange = onUserNameChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text("Usuario") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun LoginButton(clickLogin: () -> Unit) {
    Button(
        onClick = clickLogin,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Iniciar Sesión")
    }
}

@Composable
fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text("Contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        maxLines = 1,
    )
}