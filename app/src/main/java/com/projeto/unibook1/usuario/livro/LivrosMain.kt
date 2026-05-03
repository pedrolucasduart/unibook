package com.projeto.unibook1.usuario.livro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.projeto.unibook1.usuario.Inicio.TelaInicial
import com.projeto.unibook1.usuario.Inicio.TelaReservas
import com.projeto.unibook1.usuario.mapa.MapScreen
import com.projeto.unibook1.telasgerais.TelaReservaArmario

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
private val BookCardBg1    = Color(0xFFF5E6D3)
private val BookCardBg2    = Color(0xFFF7EDE2)
private val BookCardBg3    = Color(0xFF4E8B7A)
private val BookCardBg4    = Color(0xFFF5D6C0)
private val NavSelected    = Color(0xFF1A73E8)
private val NavUnselected  = Color(0xFF9E9E9E)

class LivrosMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            MainScreen(navController)
                        }
                        composable("professores") {
                            LivroProfessoresScreen(navController)
                        }
                        composable("insight") {
                            LivroInsightScreen(navController)
                        }
                        // Novas rotas adicionadas
                        composable("pesquisa") {
                            LivroPesquisaScreen(navController)
                        }
                        composable("detalhes") {
                            LivroInsightScreen(navController)
                        }
                        composable("recomendacoes_curso") {
                            LivroRec2Screen(navController)
                        }
                        composable("avaliacao") {
                            LivroReviewScreen(navController)
                        }
                        composable("professor_perfil") {
                            ProfessorPerfilScreen(navController)
                        }
                        composable("inicio") {
                            TelaInicial(onReservaClick = { navController.navigate("perfil") })
                        }
                        composable("mapa") {
                            MapScreen(onReservaClick = { navController.navigate("reserva_armario") })
                        }
                        composable("perfil") {
                            TelaReservas()
                        }
                        composable("reserva_armario") {
                            TelaReservaArmario()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,

        bottomBar = { LivroBottomNavBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBarSection()
            Spacer(Modifier.height(12.dp))
            // Barra de pesquisa agora recebe navController
            LivroSearchBarSection(navController)
            Spacer(Modifier.height(10.dp))
            LivroFilterChipsRow()
            Spacer(Modifier.height(16.dp))
            LivroDestaqueSection()
            Spacer(Modifier.height(16.dp))
            // Notas da comunidade recebe navController
            NotasComunidadeSection(navController)
            Spacer(Modifier.height(20.dp))
            LivroRecomendadosSection()
            Spacer(Modifier.height(20.dp))
            LivroExplorarCatalogoSection()
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
fun TopAppBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = AzureBlue,
            modifier = Modifier.size(26.dp)
        )
        Spacer(Modifier.width(12.dp))
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



    }
}

@Composable
fun LivroSearchBarSection(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, ChipBorder, RoundedCornerShape(24.dp))
            .background(Color(0xFFF9F9F9))
            .clickable { navController.navigate("pesquisa") }   // Navega para pesquisa
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

@Composable
fun LivroFilterChipsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LivroFilterChipItem(label = "Curso",     icon = Icons.Default.School)
        Spacer(Modifier.width(8.dp))
        LivroFilterChipItem(label = "Professor", icon = Icons.Default.Person)
        Spacer(Modifier.width(8.dp))


    }
}

@Composable
fun LivroFilterChipItem(label: String, icon: ImageVector) {
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

@Composable
fun LivroDestaqueSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Destaque da Semana",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = AzureBlue
        )
        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight(0.85f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF7BA492))
            )
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NotasComunidadeSection(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, ChipBorder, RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
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

        CommunityNoteItem(
            author = "Prof. Ricardo Rocha (Arquitetura)",
            quote = "\"Livro base para a disciplina de Teoria da Forma II. Recomendo a leitura dos capítulos 3 e 5.\""
        )

        Spacer(Modifier.height(10.dp))
        HorizontalDivider(color = Divider, thickness = 0.5.dp)
        Spacer(Modifier.height(10.dp))

        CommunityNoteItem(
            author = "Ana Clara (Monitora)",
            quote = "\"O exemplar possui anotações úteis sobre a Gestalt na página 112.\""
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("insight") },   // Navega para insights
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ver todas as 12\nnotas",
                fontSize = 13.sp,
                color = TextBlue,
                textDecoration = TextDecoration.None,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
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
fun LivroRecomendadosSection() {
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
        Box(
            modifier = Modifier
                .width(110.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(book.bgColor),
            contentAlignment = Alignment.Center
        ) {
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
fun LivroExplorarCatalogoSection() {
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
        Icon(
            imageVector = if (book.bookmarkFilled) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = if (book.bookmarkFilled) ButtonBlue else Color(0xFFBDBDBD),
            modifier = Modifier.size(22.dp)
        )
    }
}



@Composable
fun LivroBottomNavBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

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
            selected = currentRoute == "inicio",
            onClick = { navController.navigate("inicio") },
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
            selected = currentRoute == "mapa",
            onClick = { navController.navigate("mapa") },
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
            selected = currentRoute == "main",
            onClick = { 
                if (currentRoute != "main") {
                    navController.navigate("main") {
                        popUpTo("main") { inclusive = true }
                    }
                }
            },
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
            selected = currentRoute == "perfil",
            onClick = { navController.navigate("perfil") },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AzureScholarPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        MainScreen(navController)
    }
}
