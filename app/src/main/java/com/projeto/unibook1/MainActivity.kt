package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Temas
import com.projeto.unibook1.ui.theme.Unibook1Theme

// Telas Gerais
import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario

// Telas Admin
import com.projeto.unibook1.ui.admin.AdminLoginScreen
import com.projeto.unibook1.ui.admin.AdminRegisterScreen
import com.projeto.unibook1.admin.AdminMainScreen
import com.projeto.unibook1.admin.ProfileScreen
import com.projeto.unibook1.admin.RecuperarSenhaScreen
import com.projeto.unibook1.admin.DefinirSenhaScreen
import com.projeto.unibook1.admin.AdminAdicionarLivroScreen
import com.projeto.unibook1.admin.AdminEditarLivroScreen
import com.projeto.unibook1.admin.AdminLivros
import com.projeto.unibook1.admin.AdminEmprestimos
import com.projeto.unibook1.admin.AdminDetalhesSolicitacaoScreen
import com.projeto.unibook1.admin.AdminScannerScreen
import com.projeto.unibook1.admin.AdminConcluirScreen
import com.projeto.unibook1.admin.AdminGestaoEmprestimos
import com.projeto.unibook1.admin.AdminPerfilSolicitacao

// Telas Aluno
import com.projeto.unibook1.usuario.cadastro.CadastroScreen
import com.projeto.unibook1.usuario.cadastro.DefinirNovaSenhaScreen
import com.projeto.unibook1.usuario.cadastro.LoginAlunoScreen
import com.projeto.unibook1.usuario.cadastro.RecuperarSenhaScreen as RecuperarSenhaAlunoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Unibook1Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login_admin"
                ) {

                    // --- TELAS DE LOGIN E CADASTRO ADMIN ---

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

                    composable("admin_register") {
                        AdminRegisterScreen(
                            onBackToLogin = {
                                navController.navigate("login_admin") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            }
                        )
                    }

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
                            },
                            onNavigateToDefinirSenha = {
                                navController.navigate("definir_senha_admin") {
                                    popUpTo("forgot_password")
                                }
                            }
                        )
                    }

                    composable("definir_senha_admin") {
                        DefinirSenhaScreen(
                            onNavigateToLogin = {
                                navController.navigate("login_admin") {
                                    popUpTo("login_admin") { inclusive = true }
                                }
                            },
                            onNavigateToRegister = {
                                navController.navigate("admin_register")
                            }
                        )
                    }

                    // --- TELAS PRINCIPAIS DO ADMIN ---

                    // 1. Tela Principal do Admin (Home)
                    composable("admin_home") {
                        AdminMainScreen(
                            onProfileClick = { navController.navigate("admin_profile") },
                            // 👇 A MÁGICA ACONTECE AQUI!
                            onOpenScannerClick = {
                                navController.navigate("admin_scanner")
                            },
                            onStudentClick = { _ -> /* Lógica futura */ },
                            onNavigateToHome = { }, // Já está nela
                            onNavigateToEmprestimos = {
                                navController.navigate("admin_emprestimos") {
                                    popUpTo("admin_home") { saveState = true }
                                    restoreState = true
                                }
                            },
                            onNavigateToLivros = {
                                navController.navigate("admin_livros") {
                                    popUpTo("admin_home") { saveState = true }
                                    restoreState = true
                                }
                            }
                        )
                    }

                    // 2. Tela de Empréstimos
                    composable("admin_emprestimos") {
                        AdminEmprestimos(
                            onStudentClick = { matricula -> navController.navigate("detalhes_solicitacao") },
                            onNavigateToHome = {
                                navController.navigate("admin_home") {
                                    popUpTo("admin_home") { inclusive = true }
                                }
                            },
                            onNavigateToEmprestimos = { }, // Já está nela
                            onNavigateToLivros = {
                                navController.navigate("admin_livros") {
                                    popUpTo("admin_home") { saveState = true }
                                    restoreState = true
                                }
                            }
                        )
                    }

                    // 3. Tela de Livros
                    composable("admin_livros") {
                        AdminLivros(
                            onNavigateToHome = { navController.navigate("admin_home") },
                            onNavigateToEmprestimos = { navController.navigate("admin_emprestimos") },
                            onNavigateToLivros = { /* Já está nela */ },
                            onNavigateToEditBook = { navController.navigate("admin_editar_livro") },
                            onNavigateToAddBook = { navController.navigate("admin_add_book") }
                        )
                    }

                    // --- FUNCIONALIDADES DO ADMIN ---

                    composable("admin_editar_livro") {
                        AdminEditarLivroScreen(
                            onBack = { navController.popBackStack() },
                            onNavigateToHome = { navController.navigate("admin_home") },
                            onNavigateToEmprestimos = { navController.navigate("admin_emprestimos") },
                            onNavigateToLivros = { navController.navigate("admin_livros") }
                        )
                    }

                    composable("admin_add_book") {
                        AdminAdicionarLivroScreen(
                            onBack = { navController.popBackStack() },
                            onNavigateToHome = { navController.navigate("admin_home") },
                            onNavigateToEmprestimos = { navController.navigate("admin_emprestimos") },
                            onNavigateToLivros = { navController.navigate("admin_livros") }
                        )
                    }

                    composable("detalhes_solicitacao") {
                        AdminDetalhesSolicitacaoScreen(
                            onCloseClick = { navController.popBackStack() },
                            onNavigateToHome = {
                                navController.navigate("admin_home") { popUpTo("admin_home") { inclusive = true } }
                            },
                            onNavigateToEmprestimos = {
                                navController.navigate("admin_emprestimos") {
                                    popUpTo("admin_home") { saveState = true }
                                    restoreState = true
                                }
                            },
                            onNavigateToLivros = {
                                navController.navigate("admin_livros") {
                                    popUpTo("admin_home") { saveState = true }
                                    restoreState = true
                                }
                            }
                        )
                    }

                    composable("admin_scanner") {
                        AdminScannerScreen(
                            onBackClick = { navController.popBackStack() },
                            onScanSuccess = {
                                // Ao clicar na seta, vai para a tela de conclusão do aluno
                                navController.navigate("admin_scan_aluno") {
                                    // Remove o scanner da pilha pra pessoa não voltar pra câmera se apertar em "voltar"
                                    popUpTo("admin_scanner") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("admin_scan_aluno") {
                        AdminConcluirScreen(
                            onClose = {
                                navController.popBackStack("admin_home", inclusive = false)
                            },
                            onConcluir = {
                                navController.popBackStack("admin_home", inclusive = false)
                            }
                        )
                    }

                    composable("admin_profile") {
                        ProfileScreen(
                            onBackClick = { navController.popBackStack() },
                            onChangeProfilePictureClick = {
                                // Lógica futura para abrir a galeria e mudar a foto
                            }
                        )
                    }

                    // ROTAS LEGADAS/SECUNDÁRIAS DO ADMIN (Preservadas da versão anterior)
                    composable("admin_gestao_emprestimos") {
                        AdminGestaoEmprestimos(
                            onNavigateToSolicitacoes = {
                                navController.navigate("admin_emprestimos")
                            }
                        )
                    }

                    composable("admin_perfil_solicitacao") {
                        AdminPerfilSolicitacao(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // --- TELAS GERAIS E MAPA ---

                    composable("mapa") {
                        MapScreen(
                            onReservaClick = {
                                navController.navigate("reserva")
                            }
                        )
                    }

                    composable("reserva") {
                        TelaReservaArmario()
                    }

                    // --- TELAS DE USUÁRIO (ALUNO) ---

                    composable(route = "login_aluno") {
                        LoginAlunoScreen(
                            onNavigateToCadastro = { navController.navigate(route = "cadastro") },
                            onNavigateToSuporte = { },
                            onEsqueceuSenha = { navController.navigate(route = "recuperar_senha_aluno") },
                            onLoginSucesso = {
                                navController.navigate("mapa") {
                                    popUpTo("login_aluno") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(route = "cadastro") {
                        CadastroScreen(
                            onNavigateToLogin = { navController.navigate(route = "login_aluno") },
                            onNavigateToSuporte = { }
                        )
                    }

                    composable(route = "recuperar_senha_aluno") {
                        RecuperarSenhaAlunoScreen(
                            onVoltarLogin = { navController.navigate(route = "login_aluno") },
                            onContinuar = { navController.navigate("definir_nova_senha_aluno") }
                        )
                    }

                    composable("definir_nova_senha_aluno") {
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