package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.usuarios

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.examenmovilesrecuperacionv5_soniasanchez.R
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Empleado
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil

@Composable
fun EmpleadosActivity(
    viewModel: EmpleadosViewModel = hiltViewModel(),
    bottomNavigationBar: @Composable () -> Unit = {},
    onFailedToken: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.value.listaType) {
        when (state.value.listaType) {
            ListaType.EMPLEADOS -> viewModel.event(EmpleadosEvent.getEmpleados)
            ListaType.MOVILES -> viewModel.event(EmpleadosEvent.getMoviles)
        }
    }

    ListaEmpleados(
        state = state.value,
        bottomNavigationBar = bottomNavigationBar,
        onFailedToken = onFailedToken
    )
}

@Composable
fun ListaEmpleados(
    state: EmpleadosState,
    bottomNavigationBar: @Composable () -> Unit = {},
    onFailedToken: () -> Unit,
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

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            when (state.listaType) {
                ListaType.EMPLEADOS -> {
                    items(items = state.empleados) { empleado ->
                        EmpleadoItem(
                            empleado = empleado,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(dimensionResource(id = R.dimen.dimen_6dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                ListaType.MOVILES -> {
                    items(items = state.moviles) { movil ->
                        MovilItem(
                            movil = movil,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(dimensionResource(id = R.dimen.dimen_6dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EmpleadoItem(
    empleado: Empleado,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_16dp))
                .fillMaxWidth()
        ) {
            Text(
                text = "Nombre: ${empleado.username}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rol: ${empleado.rol}",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun MovilItem(
    movil: Movil,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_16dp))
                .fillMaxWidth()
        ) {
            Text(
                text = "ID: ${movil.id}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Modelo: ${movil.modelo}",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Año: ${movil.year}",
                fontSize = 14.sp
            )
        }
    }
}


