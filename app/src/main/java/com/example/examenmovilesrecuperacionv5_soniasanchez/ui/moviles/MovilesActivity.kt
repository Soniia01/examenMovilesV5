package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.moviles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.examenmovilesrecuperacionv5_soniasanchez.R
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil

@Composable
fun MovilesActivity(
    viewModel: MovilesViewModel = hiltViewModel(),
    bottomNavigationBar: @Composable () -> Unit = {},
    onFailedToken: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event(MovilesEvents.GetMoviles)
    }

    UpdateMovil(
        state.value,
        bottomNavigationBar,
        onFailedToken,
        { viewModel.event(MovilesEvents.UpdateMovil(it)) },
        { viewModel.event(MovilesEvents.CambiarId(it)) },
        { viewModel.event(MovilesEvents.CambiarModelo(it)) },
        { viewModel.event(MovilesEvents.CambiarAnio(it)) },
        { viewModel.event(MovilesEvents.CambiarCapacidad(it)) },
        { viewModel.event(MovilesEvents.CambiarEmpleado(it)) },
    )
}

@Composable
fun UpdateMovil(
    state: MovilesState,
    bottomNavigationBar: @Composable () -> Unit = {},
    onFailedToken: () -> Unit,
    update: (Movil) -> Unit,
    cambiarId: (Int) -> Unit,
    cambiarModelo: (String) -> Unit,
    cambiarAnio: (Int) -> Unit,
    cambiarCapacidad: (Int) -> Unit,
    cambiarEmpleado: (Int) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val label = stringResource(id = R.string.ok)

    LaunchedEffect(state.failedToken) {
        if (state.failedToken) {
            onFailedToken()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomNavigationBar,
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->

        LaunchedEffect(state.message) {
            state.message?.let {
                snackbarHostState.showSnackbar(
                    message = it,
                    actionLabel = label
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(innerPadding)) {
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_16dp)))
                Text(text = "Id:", fontSize = 14.sp)
                id(state, cambiarId)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_6dp)))
                Text(text = "Modelo:", fontSize = 14.sp)
                modelo(state, cambiarModelo)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_6dp)))
                Text(text = "Año:", fontSize = 14.sp)
                anio(state, cambiarAnio)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_6dp)))
                Text(text = "Capacidad:", fontSize = 14.sp)
                capacidad(state, cambiarCapacidad)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_6dp)))
                Text(text = "Empleado:", fontSize = 14.sp)
                empleado(state, cambiarEmpleado)
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_6dp)))
                updateMovil(state, update)
            }
        }
    }
}

@Composable
fun MovilesList(
    moviles: List<Movil>,
    onItemClick: (Movil) -> Unit
) {
    LazyColumn {
        items(moviles) { movil ->
            MovilListItem(
                movil = movil,
                onItemClick = { onItemClick(it) }
            )
        }
    }
}

@Composable
fun MovilListItem(
    movil: Movil,
    onItemClick: (Movil) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_6dp))
            .clickable { onItemClick(movil) }
    ) {
    }
}
@Composable
fun updateMovil(
    state: MovilesState,
    update: (Movil) -> Unit
) {
    Button(onClick = { state.movil?.let { update(it) } },
        content = { Text(text = stringResource(id = R.string.update)) })
}

@Composable
fun id(state: MovilesState, cambiarId: (Int) -> Unit) {
    TextField(
        value = state.movil?.id.toString(),
        placeholder = { Text(text = stringResource(id = R.string.space)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { cambiarId(it.toIntOrNull() ?: 0) },
        singleLine = true,
        enabled = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun modelo(state: MovilesState, cambiarModelo: (String) -> Unit) {
    TextField(
        value = state.movil?.modelo?:"",
        placeholder = { Text(text = stringResource(id = R.string.space)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { cambiarModelo(it) },
        singleLine = true,
        enabled = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun anio(state: MovilesState, cambiarAnio: (Int) -> Unit) {
    TextField(
        value = state.movil?.year.toString(),
        placeholder = { Text(text = stringResource(id = R.string.space)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { cambiarAnio(it.toIntOrNull() ?: 0) },
        singleLine = true,
        enabled = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun capacidad(state: MovilesState, cambiarCapacidad: (Int) -> Unit) {
    TextField(
        value = state.movil?.capacidad.toString(),
        placeholder = { Text(text = stringResource(id = R.string.space)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { cambiarCapacidad(it.toIntOrNull() ?: 0) },
        singleLine = true,
        enabled = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun empleado(state: MovilesState, cambiarEmpleado: (Int) -> Unit) {
    TextField(
        value = state.movil?.empleado.toString(),
        placeholder = { Text(text = stringResource(id = R.string.space)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { cambiarEmpleado(it.toIntOrNull() ?: 0) },
        singleLine = true,
        enabled = true,
        modifier = Modifier.fillMaxWidth()
    )
}