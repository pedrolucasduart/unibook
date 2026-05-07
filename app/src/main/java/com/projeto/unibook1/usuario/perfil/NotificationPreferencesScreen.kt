package com.projeto.unibook1.usuario.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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

// ── Modelo de dados ───────────────────────────────────────────────

data class NotificationItem(
    val title: String,
    val description: String,
    val defaultEnabled: Boolean = true
)

data class NotificationCategory(
    val label: String,
    val icon: ImageVector,
    val items: List<NotificationItem>
)

// ── Tela principal ────────────────────────────────────────────────

@Composable
fun NotificationPreferencesScreen(
    onBack: () -> Unit = {}
) {
    val categories = listOf(
        NotificationCategory(
            label = "Empréstimos",
            icon = Icons.Outlined.MenuBook,
            items = listOf(
                NotificationItem(
                    title = "Lembrete de Devolução",
                    description = "Avisos 2 dias antes e no dia do vencimento do prazo.",
                    defaultEnabled = true
                ),
                NotificationItem(
                    title = "Confirmação de Renovação",
                    description = "Confirmação imediata quando você estende um prazo.",
                    defaultEnabled = true
                )
            )
        ),
        NotificationCategory(
            label = "Reservas",
            icon = Icons.Outlined.Bookmark,
            items = listOf(
                NotificationItem(
                    title = "Livro Disponível para Retirada",
                    description = "Saiba no instante em que seu livro reservado chegar.",
                    defaultEnabled = true
                ),
                NotificationItem(
                    title = "Mudança na Fila de Espera",
                    description = "Alertas quando sua posição na fila de reservas mudar.",
                    defaultEnabled = false
                )
            )
        ),
        NotificationCategory(
            label = "Recomendações",
            icon = Icons.Outlined.AutoAwesome,
            items = listOf(
                NotificationItem(
                    title = "Novos Livros do seu Curso",
                    description = "Sugestões baseadas na sua grade curricular.",
                    defaultEnabled = true
                ),
                NotificationItem(
                    title = "Dicas de Professores",
                    description = "Indicações semanais dos docentes da faculdade.",
                    defaultEnabled = false
                )
            )
        ),
        NotificationCategory(
            label = "Sistema",
            icon = Icons.Outlined.Settings,
            items = listOf(
                NotificationItem(
                    title = "Mensagens da Biblioteca",
                    description = "Avisos sobre horários de funcionamento e eventos.",
                    defaultEnabled = true
                ),
                NotificationItem(
                    title = "Alertas de Segurança",
                    description = "Novos acessos à sua conta e alterações de senha.",
                    defaultEnabled = true
                )
            )
        )
    )

    val toggleStates = remember {
        mutableStateMapOf<String, Boolean>().apply {
            categories.forEach { cat ->
                cat.items.forEach { item ->
                    put("${cat.label}|${item.title}", item.defaultEnabled)
                }
            }
        }
    }

    Scaffold(
        bottomBar = { BottomNavBar() },
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
                    .padding(start = 4.dp, end = 8.dp, top = 16.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Blue)
                }
                Text(
                    "Preferências de\nNotificação",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue,
                    lineHeight = 26.sp
                )
            }

            Spacer(Modifier.height(8.dp))

            // ── Card Hero ─────────────────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = LightBlue),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Mantenha-se Atualizado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Blue,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Personalize como e quando você quer receber avisos sobre seus livros e atividades na biblioteca.",
                        fontSize = 13.sp,
                        color = TextSecond,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    Spacer(Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(CardBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.NotificationsActive,
                            contentDescription = null,
                            tint = Blue,
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // ── Categorias de Notificação ─────────────────────────
            categories.forEach { category ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        category.icon,
                        contentDescription = null,
                        tint = Blue,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        category.label,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TextPrimary
                    )
                }

                Spacer(Modifier.height(10.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        category.items.forEachIndexed { index, item ->
                            val key = "${category.label}|${item.title}"
                            val enabled = toggleStates[key] ?: item.defaultEnabled

                            if (index > 0) {
                                HorizontalDivider(
                                    color = Color(0xFFE2E8F0),
                                    thickness = 0.5.dp
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(end = 12.dp)
                                ) {
                                    Text(
                                        item.title,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        color = TextPrimary
                                    )
                                    Spacer(Modifier.height(3.dp))
                                    Text(
                                        item.description,
                                        fontSize = 12.sp,
                                        color = TextSecond,
                                        lineHeight = 18.sp
                                    )
                                }
                                Switch(
                                    checked = enabled,
                                    onCheckedChange = { toggleStates[key] = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = Blue,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedTrackColor = Color(0xFFCBD5E1)
                                    )
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}

// ── Bottom Navigation Bar ─────────────────────────────────────────

@Composable
private fun BottomNavBar() {
    val Blue      = Color(0xFF2563EB)
    val LightBlue = Color(0xFFEFF6FF)
    val CardBg    = Color(0xFFFFFFFF)

    NavigationBar(containerColor = CardBg, tonalElevation = 0.dp) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
            label = { Text("Home", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.MenuBook, contentDescription = "Library") },
            label = { Text("Library", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Notifications, contentDescription = "Alerts") },
            label = { Text("Alerts", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile", fontSize = 11.sp) },
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
fun NotificationPreferencesScreenPreview() {
    MaterialTheme {
        NotificationPreferencesScreen()
    }
}