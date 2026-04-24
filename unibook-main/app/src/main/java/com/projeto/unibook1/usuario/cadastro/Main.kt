package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.unibook1.ui.theme.Unibook1Theme
import com.projeto.unibook1.usuario.cadastro.CadastroScreen
import com.projeto.unibook1.usuario.cadastro.DefinirNovaSenhaScreen
import com.projeto.unibook1.usuario.cadastro.LoginAlunoScreen
import com.projeto.unibook1.usuario.cadastro.RecuperarSenhaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unibook1Theme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login_aluno") {

                    // Tela de Login do Aluno - Pedro
                    composable(route = "login_aluno") {
                        LoginAlunoScreen(
                            onNavigateToCadastro = { navController.navigate(route = "cadastro") },
                            onNavigateToSuporte = { },
                            onEsqueceuSenha = { navController.navigate(route = "recuperar_senha") }, // <- aqui
                            onLoginSucesso = { navController.navigate(route = "login_aluno") }
                        )
                    }

                    // Tela de Cadastro - Pedro
                    composable(route = "cadastro") {
                        CadastroScreen(
                            onNavigateToLogin = { navController.navigate(route = "login_aluno") },
                            onNavigateToSuporte = { }
                        )
                    }

                    // Tela de Recuperar Senha - adicionar isso
                    composable(route = "recuperar_senha") {
                        RecuperarSenhaScreen(
                            onVoltarLogin = { navController.navigate(route = "login_aluno") },
                            onContinuar = { navController.navigate("definir_nova_senha") } // 👈 essa linha
                        )
                    }

                    composable("definir_nova_senha") {
                        DefinirNovaSenhaScreen(
                            onVoltarLogin = { navController.navigate("login_aluno") },
                            onSenhaAtualizada = {
                                navController.navigate("login_aluno") {
                                    popUpTo("login_aluno") { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
