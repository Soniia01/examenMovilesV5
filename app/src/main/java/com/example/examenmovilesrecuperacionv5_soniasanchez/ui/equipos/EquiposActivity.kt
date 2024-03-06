package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.equipos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.examenmovilesrecuperacionv5_soniasanchez.R
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Equipo
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Jugador

@Composable
fun EquiposActivity(
    viewModel: EquiposViewModel = hiltViewModel(),
    bottomNavigationBar: @Composable () -> Unit = {}
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event(EquiposEvent.getEquipos)
    }

    EquiposView(
        state = state.value,
        bottomNavigationBar = bottomNavigationBar,
        { viewModel.event(EquiposEvent.GetEquipoById(it)) },
        { viewModel.event(EquiposEvent.CambiarId(it)) }
    )
}

@Composable
fun EquiposView(
    state: EquiposState,
    bottomNavigationBar: @Composable () -> Unit = {},
    getEquipoById: (String) -> Unit,
    cambiarId: (String) -> Unit,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val label = stringResource(id = R.string.ok)

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
                Spacer(modifier = Modifier.height(16.dp))
                id(state, cambiarId)
                Spacer(modifier = Modifier.height(8.dp))
                getEquipoById(state, getEquipoById)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(Color.Gray)
                ) {
                    items(items = state.equipos) { equipo ->
                        EquipoItemRow(
                            equipo = equipo,
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EquipoItemRow(
    equipo: Equipo,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (equipo.id) {
        1 -> Color.Green
        2 -> Color.Blue
        else -> Color.Gray
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(6.dp)
            ) {
                Text(text = "ID: ${equipo.id}")
                Text(text = "Nombre: ${equipo.nombre}")
            }

            if (equipo.jugadores.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp)
                ) {
                    Text("Jugadores:")
                    equipo.jugadores.forEach { jugador ->
                        JugadorItemRow(jugador = jugador)
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp)
                ) {
                    Text(text = "Equipo vacío")
                }
            }
        }
    }
}

@Composable
fun JugadorItemRow(jugador: Jugador) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(0.08F)
                    .padding(horizontal = 6.dp),
                text = "Id: ${jugador.id}"
            )
            Text(
                modifier = Modifier
                    .weight(0.20F)
                    .padding(horizontal = 6.dp),
                text = "Nombre: ${jugador.nombre}"
            )
        }
    }
}

@Composable
fun id(state: EquiposState, cambiarId: (String) -> Unit) {
    TextField(
        value = state.id?:"",
        placeholder = { Text(text = stringResource(id = R.string.id)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { cambiarId(it) },
        singleLine = true,
        enabled = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun getEquipoById(
    state: EquiposState,
    getEquipoById: (String) -> Unit
) {
    Button(onClick = { state.id?.let { getEquipoById(it) } },
        content = { Text(text = stringResource(id = R.string.getEquipo)) })
}

