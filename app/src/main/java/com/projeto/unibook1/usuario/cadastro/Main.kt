package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.unibook1.ui.theme.Unibook1Theme
import com.projeto.unibook1.usuario.Inicio.TelaInicial
import com.projeto.unibook1.usuario.cadastro.CadastroScreen
import com.projeto.unibook1.usuario.cadastro.ChatScreen
import com.projeto.unibook1.usuario.cadastro.DefinirNovaSenhaScreen
import com.projeto.unibook1.usuario.cadastro.LoginAlunoScreen
import com.projeto.unibook1.usuario.cadastro.OnboardingScreen
import com.projeto.unibook1.usuario.cadastro.RecuperarSenhaScreen
import com.projeto.unibook1.usuario.cadastro.SuporteAlunoScreen
import com.projeto.unibook1.usuario.suporte.FAQScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unibook1Theme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login_aluno") {

                    composable(route = "login_aluno") {
                        LoginAlunoScreen(
                            onNavigateToCadastro = { navController.navigate(route = "cadastro") },
                            onNavigateToSuporte = { navController.navigate(route = "suporte") }, // <- preencher
                            onEsqueceuSenha = { navController.navigate(route = "recuperar_senha") },
                            onLoginSucesso = { navController.navigate(route = "onboarding") }
                        )
                    }

                    composable(route = "cadastro") {
                        CadastroScreen(
                            onNavigateToLogin = { navController.navigate(route = "login_aluno") },
                            onNavigateToSuporte = { navController.navigate(route = "suporte") } // <- preencher
                        )
                    }

                    composable(route = "recuperar_senha") {
                        RecuperarSenhaScreen(
                            onVoltarLogin = { navController.navigate(route = "login_aluno") },
                            onContinuar = { navController.navigate("definir_nova_senha") }
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

                    // Tela de Suporte - adicionar
                    composable(route = "suporte") {
                        SuporteAlunoScreen(
                            onVoltar = { navController.popBackStack() },
                            onIniciarConversa = { categoria ->
                                navController.navigate("chat/$categoria")
                            },
                            onAbrirFaq = { navController.navigate("faq") }
                        )
                    }

                    composable(route = "chat/{categoria}") { backStackEntry ->
                        val categoria = backStackEntry.arguments?.getString("categoria") ?: "geral"
                        ChatScreen(
                            categoria = categoria,
                            onVoltar = { navController.popBackStack() }
                        )
                    }

                    composable(route = "faq") {
                        FAQScreen(
                            onVoltar = { navController.popBackStack() }
                        )
                    }

                    // Adicione a rota do onboarding
                    composable(route = "onboarding") {
                        OnboardingScreen(
                            onPular = {
                                navController.navigate("tela_inicial") {
                                    popUpTo("login_aluno") { inclusive = true }
                                }
                            },
                            onConcluir = {
                                navController.navigate("tela_inicial") {
                                    popUpTo("login_aluno") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(route = "tela_inicial") {
                        TelaInicial(
                            onReservaClick = { }
                        )
                    }
                }
            }
        }
    }
}