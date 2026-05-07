package com.projeto.unibook1.usuario.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Colors ────────────────────────────────────────────────────────────────────
private val BluePrimary    = Color(0xFF2196F3)
private val BlueLight      = Color(0xFFE8F0FE)
private val BlueChip       = Color(0xFFE3EEFF)
private val BackgroundGray = Color(0xFFF5F7FA)
private val CardWhite      = Color(0xFFFFFFFF)
private val TextDark       = Color(0xFF1A1A2E)
private val TextGray       = Color(0xFF6B7280)

// ── Screen ────────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyDataScreen(
    onNavigateBack: () -> Unit = {},
    onReadPrivacyPolicy: () -> Unit = {}
) {
    var personalizedRecommendations by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = BackgroundGray,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Privacidade & Dados",
                            color = BluePrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = BlueChip,
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Shield,
                                    contentDescription = null,
                                    tint = BluePrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "LGPD Protegido",
                                    color = BluePrimary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = BluePrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundGray)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            HeroInfoCard(onReadPrivacyPolicy = onReadPrivacyPolicy)

            ConsentSection(
                personalizedRecommendations = personalizedRecommendations,
                onToggleRecommendations = { personalizedRecommendations = it }
            )

            ComplianceFooter()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun HeroInfoCard(onReadPrivacyPolicy: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Seus dados, sua\njornada acadêmica.",
                color = TextDark,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                lineHeight = 30.sp
            )
            Text(
                text = "Na Unifriends, utilizamos suas informações para criar uma experiência de aprendizado personalizada. Seus dados alimentam a gestão inteligente da biblioteca e geram recomendações de leitura precisas.",
                color = TextGray,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            // 3. Botão de política de privacidade ajeitado
            Button(
                onClick = onReadPrivacyPolicy,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.OpenInNew,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Ler Política de Privacidade",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

// ── Consent section ───────────────────────────────────────────────────────────
@Composable
private fun ConsentSection(
    personalizedRecommendations: Boolean,
    onToggleRecommendations: (Boolean) -> Unit
    // 2. aiMentorProcessing removido
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SectionHeader(
            icon = Icons.Outlined.Settings,
            label = "Gerenciar Consentimento"
        )

        ConsentCard(
            title = "Recomendações Personalizadas",
            description = "Sugestões de livros e cursos com base no seu histórico acadêmico.",
            enabled = personalizedRecommendations,
            onToggle = onToggleRecommendations
        )

    }
}

@Composable
private fun ConsentCard(
    title: String,
    description: String,
    enabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    color = TextDark,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = description,
                    color = TextGray,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
            Switch(
                checked = enabled,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = BluePrimary,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFD1D5DB),
                    uncheckedBorderColor = Color(0xFFD1D5DB)
                )
            )
        }
    }
}

// ── Compliance footer ─────────────────────────────────────────────────────────
@Composable
private fun ComplianceFooter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.VerifiedUser,
            contentDescription = null,
            tint = TextGray.copy(alpha = 0.5f),
            modifier = Modifier.size(32.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "CONFORMIDADE",
                color = TextGray,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.5.sp
            )
            Text(
                text = "Lei 13.709/2018 (LGPD)",
                color = TextDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "Unifriends utiliza práticas de segurança de padrão acadêmico para garantir a integridade dos seus dados educacionais.",
            color = TextGray,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

// ── Shared section header ─────────────────────────────────────────────────────
@Composable
private fun SectionHeader(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BluePrimary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            color = TextDark,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrivacyDataScreenPreview() {
    MaterialTheme {
        PrivacyDataScreen()
    }
}