package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenmovilesrecuperacionv5_soniasanchez.R
import com.example.examenmovilesrecuperacionv5_soniasanchez.ui.equipos.EquiposActivity
import com.example.examenmovilesrecuperacionv5_soniasanchez.ui.login.LoginActivity
import com.example.examenmovilesrecuperacionv5_soniasanchez.ui.moviles.MovilesActivity
import com.example.examenmovilesrecuperacionv5_soniasanchez.ui.usuarios.EmpleadosActivity

@Composable
fun navigationAct() {
    val navController = rememberNavController()
    val loginActivity = stringResource(id = R.string.pantallaLogin)
    val equiposActivity = stringResource(id = R.string.listaEquipoPath)
    val movilesActivity = stringResource(id = R.string.listaMovilesPath)
    val empleadosActivity = stringResource(id = R.string.listaEmpleadosPath)

    NavHost(
        navController = navController,
        startDestination = loginActivity,

    ){

        composable(loginActivity) {
                LoginActivity(
                    onLoginDone = {
                        navController.navigate(empleadosActivity) {
                            popUpTo(loginActivity) {
                                inclusive = true
                            }
                        }
                    },
                    bottomNavigationBar =  {
                        BottomBar(
                            navController = navController,
                            screens = screensBottomBar
                        )
                    }
                )
        }
        composable(empleadosActivity) {
            EmpleadosActivity(
                onFailedToken = {
                    navController.navigate(loginActivity) {
                        popUpTo(empleadosActivity) {
                            inclusive = true
                        }
                    }
                },
                bottomNavigationBar =  {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar
                    )
                }
            )
        }
        composable(movilesActivity) {
            MovilesActivity(
                onFailedToken = {
                    navController.navigate(loginActivity) {
                        popUpTo(movilesActivity) {
                            inclusive = true
                        }
                    }
                },
                bottomNavigationBar =  {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar
                    )
                }
            )
        }
        composable(equiposActivity) {
            EquiposActivity(
                bottomNavigationBar =  {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar
                    )
                }
            )
        }
    }

}
