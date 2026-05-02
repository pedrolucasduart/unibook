package com.projeto.unibook1.usuario.livro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

// ── Color palette ─────────────────────────────────────────────────────────────
private val AzureBlue        = Color(0xFF1A73E8)
private val TextPrimary      = Color(0xFF1A1A1A)
private val TextSecondary    = Color(0xFF666666)
private val AvailableGreen   = Color(0xFF4CAF50)
private val AvailableGreenBg = Color(0xFFE8F5E9)
private val BorrowedRed      = Color(0xFFE53935)
private val BorrowedRedBg    = Color(0xFFFFEBEE)
private val DividerColor     = Color(0xFFEEEEEE)
private val CardBg           = Color.White
private val CardBorder       = Color(0xFFE0E0E0)
private val ChipBg           = Color(0xFFF5F5F5)
private val ChipBorder       = Color(0xFFE0E0E0)
private val NavSelected      = Color(0xFF1A73E8)
private val NavUnselected    = Color(0xFF9E9E9E)
private val HighlightBg      = Color(0xFFF0F4FB)
private val HighlightBorder  = Color(0xFF1A73E8)

@Composable
fun LivroPesquisaScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = { PsicologiaTopBar(navController) },

    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Encontramos 12 resultados para \"Psicologia\"",
                    fontSize = 14.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            item {
                PesquisaFilterChipsRow(navController)
            }

            items(bookResults) { book ->
                BookResultItem(book, onClick = {
                    navController.navigate("detalhes")
                })
            }

            item {
                HighlightCourseCard()
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun PsicologiaTopBar(navController: NavController) {
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
            modifier = Modifier
                .size(24.dp)
                .clickable { navController.popBackStack() }   // Voltar funcional
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Psicologia",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AzureBlue,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Buscar",
            tint = AzureBlue,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(8.dp))

    }
    HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
}

@Composable
fun PesquisaFilterChipsRow(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PesquisaFilterChipItem(label = "Disponíveis")
        PesquisaFilterChipItem(
            label = "Curso",
            onClick = { navController.navigate("recomendacoes_curso") }
        )

    }
}

@Composable
fun PesquisaFilterChipItem(label: String, onClick: () -> Unit = {}) {
    AssistChip(
        onClick = onClick,
        label = { Text(label, fontSize = 13.sp) },
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, ChipBorder, RoundedCornerShape(20.dp))
            .background(ChipBg),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = ChipBg,
            labelColor = TextPrimary
        )
    )
}

data class BookResult(
    val title: String,
    val author: String,
    val status: String,
    val isAvailable: Boolean,
    val coverColor: Color
)

private val bookResults = listOf(
    BookResult("Psicologia Social", "Leon Festinger", "DISPONÍVEL", true, Color(0xFF4A7E8A)),
    BookResult("Fundamentos da Mente", "Lev Vygotsky", "EMPRESTADO", false, Color(0xFF8B7355)),
    BookResult("Introdução à Psicologia", "E. R. Hilgard", "DISPONÍVEL", true, Color(0xFF7BA492))
)

@Composable
fun BookResultItem(book: BookResult, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
            .background(CardBg)
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp, 70.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(book.coverColor)
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = book.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = book.author,
                fontSize = 13.sp,
                color = TextSecondary
            )
            Spacer(Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (book.isAvailable) AvailableGreenBg else BorrowedRedBg)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = book.status,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (book.isAvailable) AvailableGreen else BorrowedRed
                )
            }
        }

    }
}

@Composable
fun HighlightCourseCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(HighlightBg)
            .border(1.dp, HighlightBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(AzureBlue.copy(alpha = 0.1f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "DESTAQUE DO CURSO",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzureBlue
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Processos Psicológicos Básicos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Anderson R. Oliveira",
                fontSize = 14.sp,
                color = TextSecondary
            )
            Spacer(Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(AvailableGreenBg)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "DISPONÍVEL AGORA",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AvailableGreen
                    )
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = AzureBlue),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("Reservar", fontSize = 12.sp, color = Color.White)
                }
            }
        }
    }
}



@Composable
private fun navBarColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = NavSelected,
    selectedTextColor = NavSelected,
    unselectedIconColor = NavUnselected,
    unselectedTextColor = NavUnselected,
    indicatorColor = Color.Transparent
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LivroPesquisaPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        LivroPesquisaScreen(navController)
    }
}
