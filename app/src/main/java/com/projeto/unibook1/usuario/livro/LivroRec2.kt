package com.projeto.unibook1.usuario.livro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
private val CardBg           = Color.White
private val CardBorder       = Color(0xFFE0E0E0)
private val BadgeBlueBg      = Color(0xFFE8F0FE)
private val BadgeBlueText    = Color(0xFF1558B0)
private val DividerColor     = Color(0xFFEEEEEE)
private val ButtonBlue       = Color(0xFF1A73E8)
private val NavSelected      = Color(0xFF1A73E8)
private val NavUnselected    = Color(0xFF9E9E9E)
private val StarYellow       = Color(0xFFFFC107)

// ══════════════════════════════════════════════════════════════════════════════
// Tela de recomendações do curso (sem a parte da IA)
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun LivroRec2Screen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = { Rec2TopBar(navController) },
        bottomBar = { Rec2BottomBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cabeçalho
            item {
                HeaderSection()
            }

            // Lista de livros recomendados
            items(recommendedBooks) { book ->
                RecommendedBookCard(book)
            }

            // Espaço final sem o card de IA
            item {
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 1 · Top App Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun Rec2TopBar(navController: NavController) {
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
            text = "Recomendações do Curso",
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
    }
    HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
}

// ══════════════════════════════════════════════════════════════════════════════
// 2 · Cabeçalho com curso e semestre
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun HeaderSection() {
    Column {
        Text(
            text = "Psicologia — 4° Semestre",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Com base no seu progresso académico, selecionamos materiais essenciais para suas próximas avaliações.",
            fontSize = 14.sp,
            color = TextSecondary,
            lineHeight = 20.sp
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 3 · Dados dos livros (mock)
// ══════════════════════════════════════════════════════════════════════════════
data class CursoBook(
    val title: String,
    val author: String,
    val highlight: String,
    val isbn: String,
    val coverColor: Color
)

private val recommendedBooks = listOf(
    CursoBook(
        title = "Psicologia Cognitiva",
        author = "Robert J. Sternberg",
        highlight = "NOTA 4.9/5 POR ESTUDANTES DE PSICOLOGIA",
        isbn = "978-85",
        coverColor = Color(0xFF4A7E8A)
    ),
    CursoBook(
        title = "Neuropsicologia Aplicada",
        author = "Leandro F. Malloy-Diniz",
        highlight = "85 ALUNOS DO SEU CURSO AVALIARAM BEM",
        isbn = "978-22",
        coverColor = Color(0xFF8B7355)
    ),
    CursoBook(
        title = "Ética Profissional",
        author = "Maria Lúcia de Arruda",
        highlight = "MAIS REQUISITADO ESTE MÊS",
        isbn = "978-41",
        coverColor = Color(0xFF7BA492)
    )
)

// ══════════════════════════════════════════════════════════════════════════════
// 4 · Card de livro recomendado
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun RecommendedBookCard(book: CursoBook) {
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
            // Linha com capa simulada e título/autor
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Capa simulada
                Box(
                    modifier = Modifier
                        .size(60.dp, 80.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(book.coverColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Outlined.MenuBook,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = book.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = book.author,
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Badge de destaque
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(BadgeBlueBg)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = book.highlight,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = BadgeBlueText
                )
            }

            Spacer(Modifier.height(10.dp))

            // Linha ISBN e botão "Ver Detalhes"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ISBN: ${book.isbn}",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                TextButton(onClick = { }) {
                    Text(
                        text = "Ver Detalhes",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextBlue
                    )
                }
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 5 · Bottom Navigation Bar (igual às outras telas)
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun Rec2BottomBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier.border(
            width = 0.5.dp,
            color = DividerColor,
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
        )
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Início", modifier = Modifier.size(22.dp)) },
            label = { Text("Início", fontSize = 11.sp) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = true,  // guia "Livros" ativa
            onClick = { },
            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Livros", modifier = Modifier.size(22.dp)) },
            label = { Text("Livros", fontSize = 11.sp) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Outlined.Bookmark, contentDescription = "Reservas", modifier = Modifier.size(22.dp)) },
            label = { Text("Reservas", fontSize = 11.sp) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Perfil", modifier = Modifier.size(22.dp)) },
            label = { Text("Perfil", fontSize = 11.sp) },
            colors = navBarColors()
        )
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

// ══════════════════════════════════════════════════════════════════════════════
// Preview
// ══════════════════════════════════════════════════════════════════════════════
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LivroRec2Preview() {
    val navController = rememberNavController()
    MaterialTheme {
        LivroRec2Screen(navController)
    }
}