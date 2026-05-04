package com.projeto.unibook1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Temas
import com.projeto.unibook1.ui.theme.Unibook1Theme

// Telas Gerais e Início
import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario
import com.projeto.unibook1.usuario.Inicio.TelaInicial
import com.projeto.unibook1.usuario.Inicio.ArmarioScreen // 👈 Import da nova tela de Armário
import com.projeto.unibook1.usuario.livro.LivroPesquisaScreen
import com.projeto.unibook1.usuario.livro.LivroInsightScreen
import com.projeto.unibook1.usuario.livro.LivroRec2Screen
import com.projeto.unibook1.usuario.livro.LivroMainScreen
import com.projeto.unibook1.usuario.livro.LivroProfessoresScreen
import com.projeto.unibook1.usuario.livro.LivroReviewScreen
import com.projeto.unibook1.usuario.livro.ProfessorPerfilScreen

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

// Novas Telas Admin (Gestão de Empréstimos)
import com.projeto.unibook1.admin.AdminAlunosBloqueados
import com.projeto.unibook1.admin.AdminPerfilBloqueado
import com.projeto.unibook1.admin.AdminEmprestimosAtrasados
import com.projeto.unibook1.admin.AdminDetalhesAtraso
import com.projeto.unibook1.admin.AdminEmprestimosRegulares
import com.projeto.unibook1.admin.AdminPerfilEmprestimo

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

                    // ==========================================
                    // TELAS DE LOGIN E CADASTRO ADMIN
                    // ==========================================

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
                                navController.navigate("admin_register") { popUpTo("login_admin") }
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
                            onNavigateToRegister = { navController.navigate("admin_register") }
                        )
                    }

                    // ==========================================
                    // TELAS PRINCIPAIS DO ADMIN
                    // ==========================================

                    composable("admin_home") {
                        AdminMainScreen(
                            onProfileClick = { navController.navigate("admin_profile") },
                            onOpenScannerClick = { navController.navigate("admin_scanner") },
                            onStudentClick = { _ -> /* Lógica futura */ },
                            onNavigateToHome = { },
                            onNavigateToEmprestimos = { navController.navigate("admin_gestao_emprestimos") },
                            onNavigateToLivros = { navController.navigate("admin_livros") }
                        )
                    }

                    composable("admin_livros") {
                        AdminLivros(
                            onNavigateToHome = { navController.navigate("admin_home") },
                            onNavigateToEmprestimos = { navController.navigate("admin_gestao_emprestimos") },
                            onNavigateToLivros = { },
                            onNavigateToEditBook = { navController.navigate("admin_editar_livro") },
                            onNavigateToAddBook = { navController.navigate("admin_add_book") }
                        )
                    }

                    // ==========================================
                    // FUNCIONALIDADES DO ADMIN (LIVROS E SCANNER)
                    // ==========================================

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

                    composable("admin_scanner") {
                        AdminScannerScreen(
                            onBackClick = { navController.popBackStack() },
                            onScanSuccess = {
                                navController.navigate("admin_scan_aluno") {
                                    popUpTo("admin_scanner") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("admin_scan_aluno") {
                        AdminConcluirScreen(
                            onClose = { navController.popBackStack("admin_home", inclusive = false) },
                            onConcluir = { navController.popBackStack("admin_home", inclusive = false) }
                        )
                    }

                    composable("admin_profile") {
                        ProfileScreen(
                            onBackClick = { navController.popBackStack() },
                            onChangeProfilePictureClick = {}
                        )
                    }

                    // ==========================================
                    // FLUXO DE GESTÃO DE EMPRÉSTIMOS
                    // ==========================================

                    composable("admin_gestao_emprestimos") {
                        AdminGestaoEmprestimos(
                            onNavigateToSolicitacoes = { navController.navigate("admin_emprestimos") },
                            onNavigateToRegulares = { navController.navigate("admin_regulares") },
                            onNavigateToIrregulares = { navController.navigate("admin_atrasados") },
                            onNavigateToBloqueados = { navController.navigate("admin_bloqueados") },
                            onNavigateToHome = { navController.navigate("admin_home") },
                            onNavigateToEmprestimos = { /* Já estamos na gestão */ },
                            onNavigateToLivros = { navController.navigate("admin_livros") }
                        )
                    }

                    composable("admin_emprestimos") {
                        AdminEmprestimos(
                            onStudentClick = { navController.navigate("detalhes_solicitacao") },
                            BackClick = { navController.popBackStack() },
                            onNavigateToHome = { navController.navigate("admin_home") },
                            onNavigateToEmprestimos = { },
                            onNavigateToLivros = { navController.navigate("admin_livros") }
                        )
                    }

                    composable("detalhes_solicitacao") {
                        AdminDetalhesSolicitacaoScreen(
                            onCloseClick = { navController.popBackStack() },
                            onNavigateToHome = { navController.navigate("admin_home") { popUpTo("admin_home") { inclusive = true } } },
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

                    composable("admin_perfil_solicitacao") {
                        AdminPerfilSolicitacao(onBack = { navController.popBackStack() })
                    }

                    composable("admin_regulares") {
                        AdminEmprestimosRegulares(
                            onBack = { navController.popBackStack() },
                            onAlunoClick = { navController.navigate("admin_perfil_emprestimo") }
                        )
                    }

                    composable("admin_perfil_emprestimo") {
                        AdminPerfilEmprestimo(onBack = { navController.popBackStack() })
                    }

                    composable("admin_atrasados") {
                        AdminEmprestimosAtrasados(
                            onBack = { navController.popBackStack() },
                            onAlunoClick = { navController.navigate("admin_detalhes_atraso") }
                        )
                    }

                    composable("admin_detalhes_atraso") {
                        AdminDetalhesAtraso(onBack = { navController.popBackStack() })
                    }

                    composable("admin_bloqueados") {
                        AdminAlunosBloqueados(
                            onBack = { navController.popBackStack() },
                            onAlunoClick = { navController.navigate("admin_perfil_bloqueado") }
                        )
                    }

                    composable("admin_perfil_bloqueado") {
                        AdminPerfilBloqueado(onBack = { navController.popBackStack() })
                    }

                    // ==========================================
                    // TELAS GERAIS E MAPA
                    // ==========================================

                    composable("mapa") {
                        // 👇 Alterado para apontar para a nova rota do armário
                        MapScreen(onReservaClick = { navController.navigate("armario_screen") })
                    }

                    // 👇 Nova rota adicionada para a tela de Verificação do Armário
                    composable("armario_screen") {
                        ArmarioScreen(
                            onBackClick = { navController.popBackStack() },
                            onPerdiChaveClick = { /* Lógica futura se houver tela de chave perdida */ }
                        )
                    }

                    composable("reserva") {
                        TelaReservaArmario() // Deixei aqui caso você precise depois
                    }

                    // ==========================================
                    // TELAS DE USUÁRIO (ALUNO)
                    // ==========================================

                    composable(route = "login_aluno") {
                        LoginAlunoScreen(
                            onNavigateToCadastro = { navController.navigate(route = "cadastro") },
                            onNavigateToSuporte = { },
                            onEsqueceuSenha = { navController.navigate(route = "recuperar_senha_aluno") },
                            onLoginSucesso = {
                                navController.navigate("mapa") { popUpTo("login_aluno") { inclusive = true } }
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
                                navController.navigate("login_aluno") { popUpTo("login_aluno") { inclusive = true } }
                            }
                        )
                    }
                }
            }
        }
    }
}
