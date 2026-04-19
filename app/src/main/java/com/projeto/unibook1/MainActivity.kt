package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.unibook1.admin.AdminMainScreen
import com.projeto.unibook1.admin.ProfileScreen
import com.projeto.unibook1.ui.admin.AdminLoginScreen
import com.projeto.unibook1.ui.theme.Unibook1Theme
import com.projeto.unibook1.usuario.cadastro.CadastroScreen
import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario
import com.projeto.unibook1.usuario.Inicio.TelaInicial
import com.projeto.unibook1.usuario.cadastro.LoginAlunoScreen
import com.projeto.unibook1.usuario.cadastro.RecuperarSenhaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unibook1Theme {


                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login_aluno") {

                    // tela de login - Pedro
                    composable(route = "login_aluno") {
                        LoginAlunoScreen(
                            onNavigateToCadastro = { navController.navigate(route = "cadastro") },
                            onNavigateToSuporte = { },
                            onEsqueceuSenha = { navController.navigate(route = "recuperar_senha") },
                            onLoginSucesso = {
                                navController.navigate("inicio") {
                                    popUpTo("login_aluno") { inclusive = true }
                                }
                            }
                        )
                    }

                    // Tela de Cadastro - Pedro
                    composable(route = "cadastro") {
                        CadastroScreen(
                            onNavigateToLogin = { navController.navigate(route = "login_aluno") },
                            onNavigateToSuporte = { }
                        )
                    }

                    // Tela de Recuperar Senha - Pedro
                    composable(route = "recuperar_senha") {
                        RecuperarSenhaScreen(
                            onVoltarLogin = { navController.navigate(route = "login_aluno") }
                        )
                    }

                    composable("inicio") {
                        TelaInicial(onReservaClick = { navController.navigate("reserva") })
                    }

                    // Tela principal do Admin - Ziltom
                    composable("admin_main") {
                        AdminMainScreen(
                            onOpenScannerClick = { },
                            onStudentClick = { },
                            onProfileClick = { navController.navigate("perfil_admin") }
                        )
                    }

                    composable("perfil_admin") {
                        ProfileScreen(
                            onBackClick = { navController.popBackStack() },
                            onChangeProfilePictureClick = { }
                        )
                    }

                    // Tela de Login Zíltom
                    composable("login_admin") {
                        AdminLoginScreen()
                    }

                    // Tela do Mapa Renan
                    composable("mapa") {
                        MapScreen(
                            onReservaClick = {
                                navController.navigate("reserva")
                            }
                        )
                    }

                    // Tela de Reserva, essa é geral, de todo mundo
                    composable("reserva") {
                        TelaReservaArmario()
                    }
                }
            }
        }
    }
}