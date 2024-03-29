package com.example.examenmovilesrecuperacionv5_soniasanchez.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.examenmovilesrecuperacionv5_soniasanchez.ui.nav.navigationAct
import com.example.examenmovilesrecuperacionv5_soniasanchez.ui.theme.ApolloProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApolloProjectTheme {
                navigationAct()
            }

        }
    }
}
