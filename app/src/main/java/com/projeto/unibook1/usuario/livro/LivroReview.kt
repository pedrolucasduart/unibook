package com.projeto.unibook1.usuario.livro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
private val StarYellow       = Color(0xFFFFC107)
private val CardBg           = Color.White
private val CardBorder       = Color(0xFFE0E0E0)
private val DividerColor     = Color(0xFFEEEEEE)
private val ButtonBlue       = Color(0xFF1A73E8)

// ══════════════════════════════════════════════════════════════════════════════
// Tela de avaliação do livro
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun LivroReviewScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = { ReviewTopBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            // Informações do livro
            BookInfoCard()

            Spacer(Modifier.height(24.dp))

            // Seção da nota
            RatingSection()

            Spacer(Modifier.height(24.dp))

            // Campo de avaliação
            ReviewTextField()

            Spacer(Modifier.height(16.dp))

            // Checkbox spoilers e contador
            SpoilerCheckbox()

            Spacer(Modifier.height(24.dp))



            Spacer(Modifier.height(40.dp))
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 1 · Top App Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ReviewTopBar(navController: NavController) {
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
            text = "Avaliar Livro",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AzureBlue,
            modifier = Modifier.weight(1f)
        )
        // Botão Publicar (visual)
        TextButton(onClick = { }) {
            Text(
                text = "Publicar",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = AzureBlue
            )
        }
    }
    HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
}

// ══════════════════════════════════════════════════════════════════════════════
// 2 · Card com informações do livro
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun BookInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Capa simulada
            Box(
                modifier = Modifier
                    .size(56.dp, 76.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF4A7E8A)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Outlined.MenuBook,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(Modifier.width(14.dp))
            Column {
                Text(
                    text = "Psicologia Social",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "por Elliot Aronson",
                    fontSize = 14.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 3 · Seção de avaliação com estrelas
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun RatingSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "SUA NOTA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextSecondary,
            letterSpacing = 1.sp
        )
        Spacer(Modifier.height(12.dp))
        // Estrelas (apenas visual, não interativas)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(4) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    tint = StarYellow,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.width(4.dp))
            }
            Icon(
                Icons.Filled.StarHalf,
                contentDescription = null,
                tint = StarYellow,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = "4.5 de 5 estrelas",
            fontSize = 14.sp,
            color = TextSecondary
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 4 · Campo de texto da avaliação
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ReviewTextField() {
    var reviewText by remember { mutableStateOf("") }
    val maxLength = 2000

    OutlinedTextField(
        value = reviewText,
        onValueChange = { if (it.length <= maxLength) reviewText = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Escreva sua avaliação...", color = TextSecondary) },
        textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzureBlue,
            unfocusedBorderColor = CardBorder,
            cursorColor = AzureBlue
        ),
        minLines = 4,
        maxLines = 8
    )
    // Contador de caracteres
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "${reviewText.length} / $maxLength",
            fontSize = 12.sp,
            color = TextSecondary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 5 · Checkbox de spoilers
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun SpoilerCheckbox() {
    var checked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(checkedColor = AzureBlue)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Contém spoilers",
            fontSize = 14.sp,
            color = TextPrimary
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LivroReviewPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        LivroReviewScreen(navController)
    }
}