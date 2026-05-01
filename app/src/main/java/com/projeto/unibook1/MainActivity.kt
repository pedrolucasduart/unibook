package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.projeto.unibook1.ui.admin.AdminLoginScreen
import com.projeto.unibook1.ui.admin.AdminRegisterScreen
import com.projeto.unibook1.ui.theme.Unibook1Theme

import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario

import com.projeto.unibook1.admin.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Unibook1Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "admin_home"
                ) {

                    composable("login_admin") {
                        AdminLoginScreen(
                            onNavigateToRegister = { navController.navigate("admin_register") },
                            onNavigateToForgotPassword = { navController.navigate("forgot_password") },
                            onLoginSuccess = {
                                navController.navigate("admin_home") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("admin_register") {
                        AdminRegisterScreen(
                            onBackToLogin = { navController.popBackStack() }
                        )
                    }

                    composable("mapa") {
                        MapScreen(
                            onReservaClick = { navController.navigate("reserva") }
                        )
                    }

                    composable("reserva") {
                        TelaReservaArmario()
                    }

                    composable("admin_home") {
                        AdminMainScreen(
                            onProfileClick = { navController.navigate("admin_profile") },
                            onOpenScannerClick = {},
                            onStudentClick = {},
                            onEmprestimosClick = {
                                navController.navigate("admin_emprestimos")
                            }
                        )
                    }

                    composable("admin_emprestimos") {
                        AdminGestaoEmprestimos(
                            onNavigateToSolicitacoes = {
                                navController.navigate("admin_solicitacoes")
                            }
                        )
                    }

                    composable("admin_solicitacoes") {
                        AdminEmprestimos(
                            BackClick = { navController.popBackStack() },
                            onStudentClick = {
                                navController.navigate("admin_perfil_solicitacao")
                            }
                        )
                    }

                    composable("admin_perfil_solicitacao") {
                        AdminPerfilSolicitacao(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    composable("admin_profile") {
                        ProfileScreen(
                            onBackClick = { navController.popBackStack() },
                            onChangeProfilePictureClick = {}
                        )
                    }
                }
            }
        }
    }
}
