package com.projeto.unibook1.usuario.livro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// ── Color palette ──────────────────────────────────────────────────────────────
private val AzureBlue        = Color(0xFF1A73E8)
private val TextPrimary      = Color(0xFF1A1A1A)
private val TextSecondary    = Color(0xFF666666)
private val TextBlue         = Color(0xFF1A73E8)
private val CardBg           = Color.White
private val CardBorder       = Color(0xFFE0E0E0)
private val BadgeBlueBg      = Color(0xFFE8F0FE)
private val BadgeBlueText    = Color(0xFF1558B0)
private val DividerColor     = Color(0xFFEEEEEE)
private val AvatarBg         = Color(0xFF8D8D8D)
private val NavSelected      = Color(0xFF1A73E8)
private val NavUnselected    = Color(0xFF9E9E9E)
private val ChipBg           = Color(0xFFF5F5F5)
private val ChipBorder       = Color(0xFFE0E0E0)
private val StarYellow       = Color(0xFFFFC107)

@Composable
fun ProfessorPerfilScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = { ProfessorPerfilTopBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                ProfessorHeader()
            }

            item {
                BookSection(
                    sectionTitle = "Plataformas Móveis",
                    bookCount = "3 LIVROS",
                    books = mobileBooks
                )
            }

            item {
                BookSection(
                    sectionTitle = "Inteligência Artificial",
                    bookCount = "3 LIVROS",
                    books = aiBooks
                )
            }

            item {
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ProfessorPerfilTopBar(navController: NavController) {
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
            text = "Perfil do Professor",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AzureBlue,
            modifier = Modifier.weight(1f)
        )
        
    }
    HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
}

@Composable
fun ProfessorHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(AvatarBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(44.dp)
            )
        }
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Prof. Dr. Narak",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Ciência da Computação",
                fontSize = 14.sp,
                color = TextSecondary
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                BadgeItem(text = "Sênior")
                BadgeItem(text = "PhD")
            }
        }
    }
}

@Composable
fun BadgeItem(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(BadgeBlueBg)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = BadgeBlueText
        )
    }
}

data class ProfessorBook(
    val title: String,
    val author: String,
    val isRecommended: Boolean = true
)

private val mobileBooks = listOf(
    ProfessorBook("Flutter in Action", "Eric Windmill"),
    ProfessorBook("Android Design Principles", "Google Devs"),
    ProfessorBook("SWIFT", "Apple")
)

private val aiBooks = listOf(
    ProfessorBook("AI: A Modern Approach", "Stuart Russell"),
    ProfessorBook("Deep Learning with Python", "François Chollet"),
    ProfessorBook("SWIFT", "The N Pedro")
)

@Composable
fun BookSection(sectionTitle: String, bookCount: String, books: List<ProfessorBook>) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = sectionTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(BadgeBlueBg)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = bookCount,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzureBlue
                )
            }
        }
        Spacer(Modifier.height(12.dp))

        books.forEachIndexed { index, book ->
            RecommendedBookItem(book)
            if (index < books.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = DividerColor,
                    thickness = 0.5.dp
                )
            }
        }
    }
}

@Composable
fun RecommendedBookItem(book: ProfessorBook) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp, 64.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFE8E8E8)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.MenuBook,
                contentDescription = null,
                tint = Color(0xFFAAAAAA),
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(ChipBg)
                    .border(1.dp, ChipBorder, RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "RECOMENDADO",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = book.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = book.author,
                fontSize = 13.sp,
                color = TextSecondary
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessorPerfilPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        ProfessorPerfilScreen(navController)
    }
}
