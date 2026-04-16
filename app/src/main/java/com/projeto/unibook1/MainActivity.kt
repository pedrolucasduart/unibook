package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.unibook1.admin.AdminMainScreen
import com.projeto.unibook1.admin.ProfileScreen

// Imports do seu amigo
import com.projeto.unibook1.ui.admin.AdminLoginScreen
import com.projeto.unibook1.ui.theme.Unibook1Theme
import com.projeto.unibook1.usuario.cadastro.CadastroScreen

// Seus imports
import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario
import com.projeto.unibook1.usuario.cadastro.LoginAlunoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unibook1Theme {


                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "admin_main") {

                    // Tela de Login do Aluno - Pedro
                    composable(route = "login_aluno") {
                        LoginAlunoScreen(
                            onNavigateToCadastro = { navController.navigate(route = "cadastro") },
                            onNavigateToSuporte = { },
                            onEsqueceuSenha = { },
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