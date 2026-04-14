package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Imports do seu amigo
import com.projeto.unibook1.ui.admin.AdminLoginScreen
import com.projeto.unibook1.ui.theme.Unibook1Theme
import com.projeto.unibook1.usuario.cadastro.CadastroScreen

// Seus imports
import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario

// 👇 NOVOS IMPORTS (Verifique se os pacotes batem com as pastas do seu projeto!)
import com.projeto.unibook1.admin.AdminMainScreen
import com.projeto.unibook1.admin.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unibook1Theme {

                val navController = rememberNavController()

                // 👇 Coloquei "admin_home" aqui para você testar suas telas logo de cara!
                // Depois você pode voltar para "mapa" ou "login_admin".
                NavHost(navController = navController, startDestination = "admin_home") {

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

                    // 👇 --- NOVAS TELAS DO ADMIN AQUI --- 👇

                    // Tela Principal do Admin (Home)
                    composable("admin_home") {
                        AdminMainScreen(
                            onProfileClick = {
                                // MÁGICA 1: Vai para a tela de perfil ao clicar na bolinha
                                navController.navigate("admin_profile")
                            },
                            onOpenScannerClick = {
                                // Lógica futura do seu scanner
                            },
                            onStudentClick = { nomeEstudante ->
                                // Lógica futura ao clicar em um aluno na lista
                            }
                        )
                    }

                    // Tela de Perfil do Admin
                    composable("admin_profile") {
                        ProfileScreen(
                            onBackClick = {
                                // MÁGICA 2: Volta para a tela anterior (Home)
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