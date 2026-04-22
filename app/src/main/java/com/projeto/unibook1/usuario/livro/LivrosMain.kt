package com.projeto.unibook1.usuario.livro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import trabalho.em.unibooktelas23.LivroProfessoresScreen


// ── Color palette ──────────────────────────────────────────────────────────────
private val AzureBlue      = Color(0xFF1565C0)
private val AzureBlueDark  = Color(0xFF0D47A1)
private val ButtonBlue     = Color(0xFF1A73E8)
private val AvailableGreen = Color(0xFF4CAF50)
private val AvailableGreenBg = Color(0xFFE8F5E9)
private val BorrowedRed    = Color(0xFFE53935)
private val BorrowedRedBg  = Color(0xFFFFEBEE)
private val StarYellow     = Color(0xFFFFC107)
private val BlueBorder     = Color(0xFF1A73E8)
private val LightBlueBg    = Color(0xFFEEF4FC)
private val ChipBg         = Color(0xFFF5F5F5)
private val ChipBorder     = Color(0xFFE0E0E0)
private val TextPrimary    = Color(0xFF1A1A1A)
private val TextSecondary  = Color(0xFF666666)
private val TextBlue       = Color(0xFF1A73E8)
private val Divider        = Color(0xFFEEEEEE)
private val BookCardBg1    = Color(0xFFF5E6D3)  // warm orange/terracotta
private val BookCardBg2    = Color(0xFFF7EDE2)  // beige/cream
private val BookCardBg3    = Color(0xFF4E8B7A)  // teal green
private val BookCardBg4    = Color(0xFFF5D6C0)  // light peach
private val NavSelected    = Color(0xFF1A73E8)
private val NavUnselected  = Color(0xFF9E9E9E)

class LivrosMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {

                // Controlador de navegação
                val navController = rememberNavController()

                Surface {

                    // Define as rotas do app
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {

                        // Tela principal
                        composable("main") {
                            MainScreen(navController)
                        }

                        // Tela professores
                        composable("professores") {
                            LivroProfessoresScreen(navController)
                        }

                        // Tela insight
                        composable("insight") {
                            LivroInsightScreen(navController)
                        }
                    }
                }


            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// Root screen
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun MainScreen(navController: NavController) {


    // CHIP "Professor"
    AssistChip(
        onClick = {
            navController.navigate("professores")
        },
        label = { Text("Professor") }
    )

    // CHIP "Princípios de Design"
    AssistChip(
        onClick = {
            navController.navigate("insight")
        },
        label = { Text("Princípios de Design") }
    )

    Scaffold(
        containerColor = Color.White,
        floatingActionButton = { MicFab() },
        bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBarSection()
            Spacer(Modifier.height(12.dp))
            SearchBarSection()
            Spacer(Modifier.height(10.dp))
            FilterChipsRow()
            Spacer(Modifier.height(16.dp))
            DestaqueSection()
            Spacer(Modifier.height(16.dp))
            NotasComunidadeSection()
            Spacer(Modifier.height(20.dp))
            RecomendadosSection()
            Spacer(Modifier.height(20.dp))
            ExplorarCatalogoSection()
            Spacer(Modifier.height(80.dp))
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 1 · Top App Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun TopAppBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Hamburger
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = AzureBlue,
            modifier = Modifier.size(26.dp)
        )
        Spacer(Modifier.width(12.dp))
        // Brand name – two-line
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Uni",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AzureBlue,
                lineHeight = 22.sp
            )
            Text(
                text = "Book",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AzureBlue,
                lineHeight = 22.sp
            )
        }
        // Avatar
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFBDBDBD)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 2 · Search Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun SearchBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, ChipBorder, RoundedCornerShape(24.dp))
            .background(Color(0xFFF9F9F9))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = Color(0xFFAAAAAA),
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Buscar por título, autor ou curso...",
            fontSize = 14.sp,
            color = Color(0xFFAAAAAA)
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 3 · Filter Chips
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun FilterChipsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChipItem(label = "Curso",     icon = Icons.Default.School)
        Spacer(Modifier.width(8.dp))
        FilterChipItem(label = "Professor", icon = Icons.Default.Person)
        Spacer(Modifier.width(8.dp))
        FilterChipItem(label = "Edição",    icon = Icons.Default.MenuBook)
        Spacer(Modifier.weight(1f))
        // Sliders / filter icon
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(6.dp))
                .border(1.dp, ChipBorder, RoundedCornerShape(6.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Filtros",
                tint = TextSecondary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun FilterChipItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, ChipBorder, RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(14.dp)
        )
        Spacer(Modifier.width(5.dp))
        Text(text = label, fontSize = 13.sp, color = TextPrimary)
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 4 · Destaque da Semana
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun DestaqueSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Destaque da Semana",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = AzureBlue
        )
        Spacer(Modifier.height(12.dp))

        // Book hero image placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {
            // Simulated green book cover
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight(0.85f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF7BA492))
            )
            // Subtle decorative circle top-right
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 20.dp, y = (-10).dp)
                    .clip(CircleShape)
                    .background(Color(0xFFDCEBF5))
            )
        }

        Spacer(Modifier.height(12.dp))

        // Badge + stars row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // DISPONÍVEL badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(AvailableGreenBg)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "DISPONÍVEL",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = AvailableGreen
                )
            }
            Spacer(Modifier.weight(1f))
            // Stars
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(4) {
                    Icon(Icons.Default.Star, null, tint = StarYellow, modifier = Modifier.size(16.dp))
                }
                Icon(Icons.Default.StarHalf, null, tint = StarYellow, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("(4.8)", fontSize = 13.sp, color = TextSecondary)
            }
        }

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Princípios de Design",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "Por Alex Miller", fontSize = 14.sp, color = TextSecondary)
        Spacer(Modifier.height(6.dp))

        // Location
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = TextBlue,
                modifier = Modifier.size(15.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Estante C, Prateleira 4 – Setor Artes",
                fontSize = 13.sp,
                color = TextBlue
            )
        }

        Spacer(Modifier.height(14.dp))

        // Reservar Agora button
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue)
        ) {
            Text(
                text = "Reservar\nAgora",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 5 · Notas da Comunidade
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun NotasComunidadeSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, ChipBorder, RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Row(verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = Icons.Default.StickyNote2,
                contentDescription = null,
                tint = TextBlue,
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Notas da\nComunidade",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextBlue,
                lineHeight = 20.sp
            )
        }

        Spacer(Modifier.height(12.dp))

        // Note 1
        CommunityNoteItem(
            author = "Prof. Ricardo Rocha (Arquitetura)",
            quote = "\"Livro base para a disciplina de Teoria da Forma II. Recomendo a leitura dos capítulos 3 e 5.\""
        )

        Spacer(Modifier.height(10.dp))
        HorizontalDivider(color = Divider, thickness = 0.5.dp)
        Spacer(Modifier.height(10.dp))

        // Note 2
        CommunityNoteItem(
            author = "Ana Clara (Monitora)",
            quote = "\"O exemplar possui anotações úteis sobre a Gestalt na página 112.\""
        )

        Spacer(Modifier.height(12.dp))

        // Ver todas link
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ver todas as 12\nnotas",
                fontSize = 13.sp,
                color = TextBlue,
                textDecoration = TextDecoration.None,
                fontWeight = FontWeight.Medium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = TextBlue,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun CommunityNoteItem(author: String, quote: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Blue left border accent
        Box(
            modifier = Modifier
                .width(3.dp)
                .height(60.dp)
                .background(BlueBorder)
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(text = author, fontSize = 12.sp, color = TextBlue, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(3.dp))
            Text(text = quote, fontSize = 13.sp, color = TextPrimary, lineHeight = 18.sp)
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 6 · Recomendados para Você
// ══════════════════════════════════════════════════════════════════════════════
data class BookCard(
    val title: String,
    val author: String,
    val rating: String,
    val bgColor: Color,
    val textColor: Color = Color.White
)

private val recommendedBooks = listOf(
    BookCard("Anatomia\nHumana", "Gray's", "4.9", BookCardBg1, Color(0xFF5D2E0C)),
    BookCard("Cálculo\nDiferencial", "Stewart", "4.5", BookCardBg2, Color(0xFF5D3A1A)),
    BookCard("Marketing\nDigital", "Ler", "4.7", BookCardBg3, Color.White),
    BookCard("UX I", "Erika", "4.9", BookCardBg4, Color(0xFF5D2E0C)),
)

@Composable
fun RecomendadosSection() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Recomendados para",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Você",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "avaliados por colegas do seu curso",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
            Text(
                text = "Ver\ntudo",
                fontSize = 13.sp,
                color = TextBlue,
                fontWeight = FontWeight.Medium,
                textAlign = androidx.compose.ui.text.style.TextAlign.End
            )
        }

        Spacer(Modifier.height(12.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recommendedBooks) { book ->
                RecommendedBookCard(book)
            }
        }
    }
}

@Composable
fun RecommendedBookCard(book: BookCard) {
    Column(modifier = Modifier.width(110.dp)) {
        // Book cover
        Box(
            modifier = Modifier
                .width(110.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(book.bgColor),
            contentAlignment = Alignment.Center
        ) {
            // Small book title overlay for teal card (Marketing Digital)
            if (book.bgColor == BookCardBg3) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text("CARTA DO", fontSize = 9.sp, color = Color.White.copy(alpha = 0.8f), fontWeight = FontWeight.Light)
                    Text("MARKETING", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    Text("DIGITAL", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = book.title.replace("\n", " "),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = book.author, fontSize = 11.sp, color = TextSecondary)
        Spacer(Modifier.height(2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, null, tint = StarYellow, modifier = Modifier.size(13.dp))
            Spacer(Modifier.width(3.dp))
            Text(text = book.rating, fontSize = 12.sp, color = TextPrimary)
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 7 · Explorar Catálogo
// ══════════════════════════════════════════════════════════════════════════════
data class CatalogBook(
    val title: String,
    val author: String,
    val status: String,
    val isAvailable: Boolean,
    val bookmarkFilled: Boolean,
    val coverColor: Color
)

private val catalogBooks = listOf(
    CatalogBook("Psicologia Social", "Leon Festinger", "EMPRESTADO", false, false, Color(0xFF4A7E8A)),
    CatalogBook("Justiça: O que é...", "Michael Sandel", "DISPONÍVEL", true, true, Color(0xFF8B7355)),
)

@Composable
fun ExplorarCatalogoSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Explorar Catálogo",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(Modifier.height(12.dp))
        catalogBooks.forEachIndexed { index, book ->
            CatalogBookItem(book)
            if (index < catalogBooks.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Divider,
                    thickness = 0.5.dp
                )
            }
        }
    }
}

@Composable
fun CatalogBookItem(book: CatalogBook) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Book thumbnail
        Box(
            modifier = Modifier
                .width(52.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(book.coverColor)
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = book.title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(text = book.author, fontSize = 12.sp, color = TextSecondary)
            Spacer(Modifier.height(5.dp))
            // Status badge
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
        // Bookmark icon
        Icon(
            imageVector = if (book.bookmarkFilled) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = if (book.bookmarkFilled) ButtonBlue else Color(0xFFBDBDBD),
            modifier = Modifier.size(22.dp)
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 8 · Mic FAB
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun MicFab() {
    FloatingActionButton(
        onClick = {},
        containerColor = ButtonBlue,
        contentColor = Color.White,
        shape = CircleShape,
        modifier = Modifier.size(56.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Mic,
            contentDescription = "Pesquisa por voz",
            modifier = Modifier.size(26.dp)
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 9 · Bottom Navigation Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun BottomNavBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier.border(
            width = 0.5.dp,
            color = Divider,
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
        )
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(Icons.Outlined.Home, contentDescription = "Início", modifier = Modifier.size(22.dp))
            },
            label = { Text("Início", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavSelected,
                selectedTextColor = NavSelected,
                unselectedIconColor = NavUnselected,
                unselectedTextColor = NavUnselected,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(Icons.Outlined.Map, contentDescription = "Mapa", modifier = Modifier.size(22.dp))
            },
            label = { Text("Mapa", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavSelected,
                selectedTextColor = NavSelected,
                unselectedIconColor = NavUnselected,
                unselectedTextColor = NavUnselected,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(Icons.Default.MenuBook, contentDescription = "Livros", modifier = Modifier.size(22.dp))
            },
            label = { Text("Livros", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavSelected,
                selectedTextColor = NavSelected,
                unselectedIconColor = NavUnselected,
                unselectedTextColor = NavUnselected,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(Icons.Outlined.Person, contentDescription = "Perfil", modifier = Modifier.size(22.dp))
            },
            label = { Text("Perfil", fontSize = 11.sp) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavSelected,
                selectedTextColor = NavSelected,
                unselectedIconColor = NavUnselected,
                unselectedTextColor = NavUnselected,
                indicatorColor = Color.Transparent
            )
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// Preview
// ══════════════════════════════════════════════════════════════════════════════
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AzureScholarPreview() {
    val navController = rememberNavController()

    MaterialTheme {
        MainScreen(navController)
    }
}
