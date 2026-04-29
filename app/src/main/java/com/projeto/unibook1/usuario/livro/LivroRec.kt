package com.example.azurescholar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// ── Color palette (padrão das outras telas) ──────────────────────────────────
private val AzureBlue        = Color(0xFF1A73E8)
private val TextPrimary      = Color(0xFF1A1A1A)
private val TextSecondary    = Color(0xFF666666)
private val TextBlue         = Color(0xFF1A73E8)
private val AvailableGreen   = Color(0xFF4CAF50)
private val AvailableGreenBg = Color(0xFFE8F5E9)
private val BorrowedRed      = Color(0xFFE53935)
private val BorrowedRedBg    = Color(0xFFFFEBEE)
private val ReservedOrange   = Color(0xFFFF9800)
private val ReservedOrangeBg = Color(0xFFFFF3E0)
private val DividerColor     = Color(0xFFEEEEEE)
private val CardBg           = Color.White
private val CardBorder       = Color(0xFFE0E0E0)
private val BadgeBlueBg      = Color(0xFFE8F0FE)
private val BadgeBlueText    = Color(0xFF1558B0)
private val StarYellow       = Color(0xFFFFC107)
private val NoteBg           = Color(0xFFF5F7FA)
private val ButtonBlue       = Color(0xFF1A73E8)

// ══════════════════════════════════════════════════════════════════════════════
// Tela de detalhes do livro
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun LivroDetalhesScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = { DetalhesTopBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cabeçalho com título, autor, rating, tags
            item {
                BookHeader()
            }

            // Informações técnicas
            item {
                TechnicalInfoCard()
            }

            // Localização física
            item {
                LocationCard()
            }

            // Status dos exemplares
            item {
                ExemplaresCard()
            }

            // Comunidade & Notas
            item {
                CommunityNoteCard()
            }

            // Nota pessoal
            item {
                PersonalNoteCard()
            }

            // Botão reservar (sticky visual dentro da lista, mas pode ser fixo)
            item {
                Spacer(Modifier.height(8.dp))
                ReserveButton()
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 1 · Top App Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun DetalhesTopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Voltar",
            tint = AzureBlue,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Book Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AzureBlue,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = "Compartilhar",
            tint = AzureBlue,
            modifier = Modifier.size(22.dp)
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Outlined.BookmarkBorder,
            contentDescription = "Salvar",
            tint = AzureBlue,
            modifier = Modifier.size(22.dp)
        )
    }
    HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
}

// ══════════════════════════════════════════════════════════════════════════════
// 2 · Cabeçalho do livro
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun BookHeader() {
    Column {
        Text(
            text = "Psicologia Social",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Leon Festinger",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary
        )
        Spacer(Modifier.height(8.dp))
        // Rating
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                repeat(4) {
                    Icon(Icons.Filled.Star, null, tint = StarYellow, modifier = Modifier.size(18.dp))
                }
                Icon(Icons.Filled.StarHalf, null, tint = StarYellow, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.width(6.dp))
            Text(
                text = "4.8 / 5 (120 reviews)",
                fontSize = 14.sp,
                color = TextSecondary
            )
        }
        Spacer(Modifier.height(12.dp))
        // Tags
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterTag(label = "Psicologia")
            FilterTag(label = "Acadêmico")
        }
    }
}

@Composable
fun FilterTag(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(BadgeBlueBg)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = BadgeBlueText
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 3 · Informações Técnicas
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun TechnicalInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Informações Técnicas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Obra fundamental que explora os processos de influência social, dissonância cognitiva e comportamento de indivíduos em grupos.",
                fontSize = 14.sp,
                color = TextSecondary,
                lineHeight = 20.sp
            )
            Spacer(Modifier.height(12.dp))
            InfoRow(label = "ISBN", value = "978-0131496712")
            InfoRow(label = "Edição", value = "8ª Edição")
            InfoRow(label = "Editora", value = "Pearson")
            InfoRow(label = "Ano", value = "2014")
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextSecondary)
        Text(text = value, fontSize = 14.sp, color = TextPrimary)
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 4 · Localização Física
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun LocationCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Localização Física",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, null, tint = TextBlue, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("1º Andar", fontSize = 14.sp, color = TextPrimary)
                    Text("Corredor B, Prateleira 4", fontSize = 14.sp, color = TextSecondary)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Ver no Mapa",
                fontSize = 14.sp,
                color = TextBlue,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 5 · Status dos Exemplares
// ══════════════════════════════════════════════════════════════════════════════
data class Exemplar(
    val id: String,
    val status: String,
    val extraInfo: String? = null,
    val statusColor: Color,
    val statusBg: Color
)

private val exemplaresList = listOf(
    Exemplar("101", "DISPONÍVEL", null, AvailableGreen, AvailableGreenBg),
    Exemplar("102", "EMPRESTADO", "Dev.: 25/04", BorrowedRed, BorrowedRedBg),
    Exemplar("103", "RESERVADO", null, ReservedOrange, ReservedOrangeBg)
)

@Composable
fun ExemplaresCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Status dos Exemplares",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(12.dp))
            exemplaresList.forEach { exemplar ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Exemplar ${exemplar.id}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextPrimary
                        )
                        Spacer(Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(exemplar.statusBg)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = exemplar.status,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = exemplar.statusColor
                            )
                        }
                        if (exemplar.extraInfo != null) {
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = exemplar.extraInfo,
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                        }
                    }
                    // Botão Reservar apenas para disponíveis
                    if (exemplar.status == "DISPONÍVEL") {
                        TextButton(onClick = { }) {
                            Text("Reservar", fontSize = 12.sp, color = AzureBlue)
                        }
                    } else {
                        Spacer(Modifier.width(60.dp))
                    }
                }
                if (exemplar != exemplaresList.last()) {
                    HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
                }
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 6 · Comunidade & Notas
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun CommunityNoteCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            // Ícone e número
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.People, null, tint = AzureBlue, modifier = Modifier.size(24.dp))
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "99",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzureBlue
                )
            }
            Spacer(Modifier.width(16.dp))
            // Mensagem
            Text(
                text = "Indicado pelo professor Anderson para Processos Psicológicos Básicos.",
                fontSize = 14.sp,
                color = TextSecondary,
                lineHeight = 20.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 7 · Nota Pessoal
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun PersonalNoteCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = NoteBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(Icons.Default.Edit, null, tint = TextBlue, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = "NOTA PESSOAL",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextBlue
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Ler para o seminário da próxima semana.",
                    fontSize = 14.sp,
                    color = TextPrimary
                )
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 8 · Botão Reservar Livro
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ReserveButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
    ) {
        Icon(Icons.Default.Bookmark, null, tint = Color.White, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            text = "RESERVAR LIVRO",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// Preview
// ══════════════════════════════════════════════════════════════════════════════
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LivroDetalhesPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        LivroDetalhesScreen(navController)
    }
}