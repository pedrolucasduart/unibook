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


import com.projeto.unibook1.admin.AdminMainScreen
import com.projeto.unibook1.admin.ProfileScreen
import com.projeto.unibook1.admin.RecuperarSenhaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            Unibook1Theme {


                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = "forgot_password") {

                    // Tela de Login
                    composable("login_admin") {
                        AdminLoginScreen(
                            onNavigateToRegister = {
                                navController.navigate("admin_register")
                            },
                            onNavigateToForgotPassword = {
                                navController.navigate("forgot_password")
                            },
                            onLoginSuccess = {
                                navController.navigate("admin_home") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            }
                        )
                    }

                    // Tela de Cadastro
                    composable("admin_register") {
                        AdminRegisterScreen(
                            onBackToLogin = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // Tela de Esqueci a Senha
                    composable("forgot_password") {
                        RecuperarSenhaScreen(
                            onNavigateToLogin = {
                                navController.navigate("login_admin") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = {
                                navController.navigate("admin_register") {
                                    popUpTo("login_admin")
                                }
                            }
                        )
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



                    // Tela Principal do Admin (Home)
                    composable("admin_home") {
                        AdminMainScreen(
                            onProfileClick = {

                                navController.navigate("admin_profile")
                            },
                            onOpenScannerClick = {
                                // Lógica futura do seu scanner
                            },
                            onStudentClick = { _ ->
                                // Lógica futura ao clicar em um aluno na lista
                            }
                        )
                    }

                    // Tela de Perfil do Admin
                    composable("admin_profile") {
                        ProfileScreen(
                            onBackClick = {

                                navController.popBackStack()
                            },
                            onChangeProfilePictureClick = {
                                // Lógica futura para abrir a galeria e mudar a foto
                            }
                        )
                    }

                }
            }
        }
    }
}

