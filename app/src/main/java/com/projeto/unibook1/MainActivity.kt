package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.projeto.unibook1.ui.admin.AdminLoginScreen // Importa a tela que criamos
import com.projeto.unibook1.ui.theme.Unibook1Theme
import com.projeto.unibook1.usuario.mapa.CadastroScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unibook1Theme {
                AdminLoginScreen()
                CadastroScreen(
                    onNavigateToLogin = {},
                    onNavigateToSuporte = {}
                )
            }
        }
    }
}