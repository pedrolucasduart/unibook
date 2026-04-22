package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.projeto.unibook1.ui.admin.AdminLoginScreen
import com.projeto.unibook1.ui.admin.AdminRegisterScreen
import com.projeto.unibook1.ui.theme.Unibook1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ativa o modo de tela cheia (opcional, padrão do Android Studio atual)
        enableEdgeToEdge()

        setContent {
            Unibook1Theme {
                // Variável de estado que controla qual tela está visível
                // Começa como "login"
                var currentScreen by remember { mutableStateOf("login") }

                // Lógica de Troca de Telas (Navegação Manual)
                when (currentScreen) {
                    "login" -> {
                        AdminLoginScreen(
                            onNavigateToRegister = {
                                // Quando clicar em "Cadastre-se", muda o estado para "cadastro"
                                currentScreen = "cadastro"
                            }
                        )
                    }
                    "cadastro" -> {
                        AdminRegisterScreen(
                            onBackToLogin = {
                                // Quando clicar em "Voltar", muda o estado para "login"
                                currentScreen = "login"
                            }
                        )
                    }
                }
            }
        }
    }
}