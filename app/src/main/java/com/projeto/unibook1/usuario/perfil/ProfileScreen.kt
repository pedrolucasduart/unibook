package com.projeto.unibook1.usuario.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Cores ─────────────────────────────────────────────────────────
private val Blue        = Color(0xFF2196F3)
private val LightBlue   = Color(0xFFEFF6FF)
private val Background  = Color(0xFFF1F5F9)
private val CardBg      = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF1E293B)
private val TextSecond  = Color(0xFF64748B)
private val GreenReady  = Color(0xFF16A34A)
private val OrangeWarn  = Color(0xFFFFF7ED)
private val OrangeText  = Color(0xFFEA580C)
private val RedLogout   = Color(0xFFDC2626)

// ── Modelos de dados ──────────────────────────────────────────────

data class UserProfile(
    val name: String,
    val registration: String,
    val semester: String,
    val course: String
)

data class LoanItem(
    val title: String,
    val returnDate: String,
    val progressFraction: Float = 0.6f
)

data class ReserveItem(
    val title: String,
    val isReady: Boolean,
    val queuePosition: Int? = null
)

data class LockerInfo(
    val lockerCode: String,
    val location: String,
    val sharedWith: String? = null
)

// ── Tela principal ────────────────────────────────────────────────

@Composable
fun ProfileScreen(
    user: UserProfile = UserProfile(
        name = "Lucas Silva",
        registration = "2124567",
        semester = "4° Semestre",
        course = "Ciência da Computação"
    ),
    loans: List<LoanItem> = listOf(
        LoanItem("Psicologia Experimental", "15 Out", 0.6f)
    ),
    reserves: List<ReserveItem> = listOf(
        ReserveItem("Ética e Moralidade", isReady = true),
        ReserveItem("Análise do Comportamento", isReady = false, queuePosition = 2)
    ),
    locker: LockerInfo = LockerInfo(
        lockerCode = "C-04",
        location = "Bloco B, 2° andar",
        sharedWith = "Ziltom"
    ),
    onViewSharedLocker: () -> Unit = {},
    onHelpAndSupport: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onHistoricoClick: () -> Unit = {},
    onPrivacidadeClick: () -> Unit = {},
    onNotificacoesClick: () -> Unit = {},
    onMapaClick: () -> Unit = {},       // ← adicionado
    onLivrosClick: () -> Unit = {},     // ← adicionado
    onLogout: () -> Unit = {}
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutConfirmDialog(
            onConfirm = {
                showLogoutDialog = false
                onLogout()
            },
            onDismiss = { showLogoutDialog = false }
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                onInicioClick = onBackClick,
                onMapaClick   = onMapaClick,    // ← adicionado
                onLivrosClick = onLivrosClick,  // ← adicionado
                onPerfilClick = {}
            )
        },
        containerColor = Background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            // ── Top Bar ──────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = TextPrimary)
                }
                IconButton(onClick = onSettingsClick) {
                    Icon(Icons.Outlined.Settings, contentDescription = "Configurações", tint = TextPrimary)
                }
            }

            // ── Informações do usuário ────────────────────────────
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFCBD5E1)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        tint = Color.White,
                        modifier = Modifier.size(56.dp)
                    )
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = user.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoChip("Matrícula: ${user.registration}")
                    InfoChip(user.semester)
                }

                Spacer(Modifier.height(6.dp))
                Text(user.course, fontSize = 14.sp, color = TextSecond)
                Spacer(Modifier.height(20.dp))
            }

            // ── Meus Empréstimos ──────────────────────────────────
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Outlined.MenuBook, contentDescription = null, tint = Blue, modifier = Modifier.size(20.dp))
                        Text("Meus Empréstimos", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
                    }
                    Surface(shape = RoundedCornerShape(20.dp), color = LightBlue) {
                        Text(
                            "${loans.size} ativo${if (loans.size != 1) "s" else ""}",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            color = Blue,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                loans.forEachIndexed { index, loan ->
                    if (index > 0) {
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(color = Color(0xFFE2E8F0))
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(loan.title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = TextPrimary)
                    Text("Devolução: ${loan.returnDate}", fontSize = 12.sp, color = TextSecond)
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFFE2E8F0))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(loan.progressFraction)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Blue)
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // ── Minhas Reservas ───────────────────────────────────
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Outlined.Bookmark, contentDescription = null, tint = Blue, modifier = Modifier.size(20.dp))
                    Text("Minhas Reservas", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
                }

                reserves.forEachIndexed { index, reserve ->
                    if (index > 0) {
                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider(color = Color(0xFFE2E8F0))
                    }
                    Spacer(Modifier.height(12.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(40.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(if (reserve.isReady) GreenReady else Blue)
                        )
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(reserve.title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = TextPrimary)
                            Spacer(Modifier.height(2.dp))
                            if (reserve.isReady) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = GreenReady, modifier = Modifier.size(14.dp))
                                    Text("Pronto para retirada", fontSize = 12.sp, color = GreenReady, fontWeight = FontWeight.Medium)
                                }
                            } else {
                                Text("Posição na fila: ${reserve.queuePosition}°", fontSize = 12.sp, color = TextSecond)
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // ── Armários e Cestas ─────────────────────────────────
            SectionCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                background = LightBlue
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Outlined.Lock, contentDescription = null, tint = Blue, modifier = Modifier.size(20.dp))
                    Text("Armários e Cestas", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                }
                Text("Facilite seu dia no campus.", fontSize = 13.sp, color = TextSecond)

                Spacer(Modifier.height(14.dp))

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(LightBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Outlined.Lock, contentDescription = null, tint = Blue, modifier = Modifier.size(22.dp))
                        }
                        Column {
                            Text("Armário ${locker.lockerCode}", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextPrimary)
                            Text(locker.location, fontSize = 12.sp, color = TextSecond)
                        }
                    }
                }

                locker.sharedWith?.let { sharedName ->
                    Spacer(Modifier.height(10.dp))
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = OrangeWarn),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onViewSharedLocker() }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.Group, contentDescription = null, tint = OrangeText, modifier = Modifier.size(20.dp))
                                Column {
                                    Text(
                                        "Você está em um armário\ncompartilhado",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = OrangeText
                                    )
                                    Text(
                                        "$sharedName está com você no Armário ${locker.lockerCode}",
                                        fontSize = 11.sp,
                                        color = OrangeText
                                    )
                                }
                            }
                            Text("Ver", color = OrangeText, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // ── Geral ─────────────────────────────────────────────
            Text(
                "Geral",
                modifier = Modifier.padding(horizontal = 20.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = TextSecond,
                letterSpacing = 0.5.sp
            )

            Spacer(Modifier.height(8.dp))

            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                GeneralMenuItem(
                    icon = Icons.Outlined.History,
                    title = "Histórico de Empréstimos",
                    subtitle = "12 livros lidos",
                    onClick = onHistoricoClick
                )
                MenuDivider()
                GeneralMenuItem(
                    icon = Icons.Outlined.Shield,
                    title = "Privacidade (LGPD)",
                    onClick = onPrivacidadeClick
                )
                MenuDivider()
                GeneralMenuItem(
                    icon = Icons.Outlined.Notifications,
                    title = "Preferências de Notificação",
                    onClick = onNotificacoesClick
                )
            }

            Spacer(Modifier.height(12.dp))

            // ── Ajuda e Suporte ───────────────────────────────────
            SectionCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                clickable = true,
                onClick = onHelpAndSupport
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Blue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Outlined.Chat, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
                        }
                        Column {
                            Text("AJUDA E SUPORTE", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Blue)
                            Text("Falar com a bibliotecária", fontSize = 12.sp, color = TextSecond)
                        }
                    }
                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Blue)
                }
            }

            Spacer(Modifier.height(12.dp))

            // ── Sair da Conta ─────────────────────────────────────
            SectionCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                clickable = true,
                onClick = { showLogoutDialog = true }
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFEE2E2)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = "Sair da conta",
                            tint = RedLogout,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Text("Sair da conta", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = RedLogout)
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

// ── Dialog de confirmação de logout ──────────────────────────────

@Composable
private fun LogoutConfirmDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        containerColor = CardBg,
        title = { Text("Sair da conta", fontWeight = FontWeight.Bold, color = TextPrimary) },
        text = { Text("Tem certeza que deseja encerrar sua sessão?", color = TextSecond) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Sair", color = RedLogout, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = TextSecond)
            }
        }
    )
}

// ── Componentes auxiliares ────────────────────────────────────────

@Composable
private fun InfoChip(text: String) {
    Surface(shape = RoundedCornerShape(20.dp), color = LightBlue) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            fontSize = 11.sp,
            color = Blue
        )
    }
}

@Composable
private fun SectionCard(
    modifier: Modifier = Modifier,
    background: Color = CardBg,
    clickable: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(if (clickable) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = background),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), content = content)
    }
}

@Composable
private fun GeneralMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(icon, contentDescription = null, tint = TextSecond, modifier = Modifier.size(20.dp))
            Column {
                Text(title, fontSize = 14.sp, color = TextPrimary)
                if (subtitle != null) Text(subtitle, fontSize = 12.sp, color = TextSecond)
            }
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecond, modifier = Modifier.size(18.dp))
    }
}

@Composable
private fun MenuDivider() {
    HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 0.5.dp)
}

// ── Bottom Navigation Bar ─────────────────────────────────────────

@Composable
private fun BottomNavBar(
    onInicioClick: () -> Unit = {},
    onMapaClick: () -> Unit = {},
    onLivrosClick: () -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    NavigationBar(containerColor = CardBg, tonalElevation = 0.dp) {
        NavigationBarItem(
            selected = false,
            onClick = onInicioClick,
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Início") },
            label = { Text("Início", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onMapaClick,
            icon = { Icon(Icons.Outlined.Map, contentDescription = "Mapa") },
            label = { Text("Mapa", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = onLivrosClick,
            icon = { Icon(Icons.Outlined.MenuBook, contentDescription = "Livros") },
            label = { Text("Livros", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = true,
            onClick = onPerfilClick,
            icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
            label = { Text("Perfil", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Blue,
                selectedTextColor = Blue,
                indicatorColor = LightBlue
            )
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}