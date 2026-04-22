package com.projeto.unibook1.usuario.livro



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// ── Color palette ──────────────────────────────────────────────────────────────
private val AppBackground    = Color(0xFFF8F9FA)
private val CardBg           = Color.White
private val AzureBlue        = Color(0xFF1A73E8)
private val TextPrimary      = Color(0xFF1A1A1A)
private val TextSecondary    = Color(0xFF757575)
private val SearchBarBg      = Color.White
private val SearchBarBorder  = Color(0xFFE0E0E0)
private val BadgeBlueBg      = Color(0xFFE8F0FE)
private val BadgeBlueText    = Color(0xFF1558B0)
private val AvatarBgPlaceholder = Color(0xFFBCAAA4)
private val VerifiedBlue     = Color(0xFF1A73E8)
private val NavSelected      = Color(0xFF1A73E8)
private val NavUnselected    = Color(0xFF9E9E9E)
private val CtaBg            = Color(0xFFF0F4FB)

// ══════════════════════════════════════════════════════════════════════════════
// Entry point
// ══════════════════════════════════════════════════════════════════════════════

// ══════════════════════════════════════════════════════════════════════════════
// Root screen
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun LivroProfessoresScreen(navController: NavController) {

    Column {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar"
            )
        }

        Text("Tela de Professores")
    }
    Scaffold(
        containerColor = AppBackground,
        topBar = { ProfessoresTopBar() },
        bottomBar = { ProfessoresBottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(8.dp))
            SearchBarSection()
            Spacer(Modifier.height(24.dp))
            SectionHeader(title = "CORPO DOCENTE", count = "48 Disponíveis")
            Spacer(Modifier.height(16.dp))

            // Cards
            FeaturedProfessorCard()
            Spacer(Modifier.height(12.dp))
            StandardProfessorCard(
                name = "Profª. Maria Silva",
                department = "Direito",
                works = "8 obras recomendadas"
            )
            Spacer(Modifier.height(12.dp))
            StandardProfessorCard(
                name = "Prof. Ricardo Rocha",
                department = "Arquitetura",
                works = "15 obras recomendadas"
            )
            Spacer(Modifier.height(12.dp))
            StandardProfessorCard(
                name = "Profª. Ana Clara",
                department = "Psicologia",
                works = "5 obras recomendadas"
            )

            Spacer(Modifier.height(24.dp))
            BuscaEspecificaCard()
            Spacer(Modifier.height(32.dp)) // Bottom padding before Nav Bar
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 1 · Top App Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ProfessoresTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppBackground)
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .padding(top = 24.dp), // Safe area padding simulation
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Voltar",
            tint = AzureBlue,
            modifier = Modifier.size(24.dp).clickable { }
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = "Professores",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = AzureBlue,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Buscar",
            tint = AzureBlue,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
        // User Avatar Profile
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFF8D8D8D)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 2 · Search Bar (Interactive)
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun SearchBarSection() {
    var searchQuery by remember { mutableStateOf("") }

    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(SearchBarBg, RoundedCornerShape(12.dp)),
            placeholder = { Text("Buscar professor...", color = Color(0xFFA0A0A0)) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFA0A0A0))
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AzureBlue,
                unfocusedBorderColor = SearchBarBorder,
                cursorColor = AzureBlue
            ),
            singleLine = true
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 3 · Section Header
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun SectionHeader(title: String, count: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = TextSecondary,
            letterSpacing = 1.sp
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(BadgeBlueBg)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = count,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = AzureBlue
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 4 · Featured Professor Card
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun FeaturedProfessorCard() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
            .border(1.dp, SearchBarBorder, RoundedCornerShape(16.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Blue Accent Line
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height(110.dp) // Approximate height to match card
                    .background(AzureBlue)
            )
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar with Verified Badge
                Box {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(AvatarBgPlaceholder),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
                    }
                    // Verified Badge
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Verificado",
                        tint = VerifiedBlue,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = 4.dp, y = 4.dp)
                            .background(Color.White, CircleShape)
                    )
                }

                Spacer(Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Prof.\nKaran",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        lineHeight = 22.sp
                    )
                    Text(
                        text = "Ciência da Computação",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzureBlue,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.MenuBook, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(text = "12 obras recomendadas", fontSize = 12.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                    }
                }

                // Destaque Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BadgeBlueBg)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "EM\nDESTAQUE",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzureBlue,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 5 · Standard Professor Card
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun StandardProfessorCard(name: String, department: String, works: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardBg)
            .border(1.dp, SearchBarBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(AvatarBgPlaceholder),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
            }

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = department,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.MenuBook, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(text = works, fontSize = 12.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                }
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ver Perfil",
                tint = Color(0xFFBDBDBD),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 6 · Busca Específica CTA Card
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun BuscaEspecificaCard() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CtaBg)
            .border(1.dp, BadgeBlueBg, RoundedCornerShape(16.dp))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD6E4FB)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.School, contentDescription = null, tint = AzureBlue, modifier = Modifier.size(24.dp))
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Busca Específica?",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = AzureBlue
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Filtre por departamento para\nencontrar bibliografias\nrecomendadas para o seu curso.",
                fontSize = 13.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(0.8f).height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AzureBlue),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Ver Departamentos", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 7 · Bottom Navigation Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ProfessoresBottomBar() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Início") },
            label = { Text("Início", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Map, contentDescription = "Mapa") },
            label = { Text("Mapa", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = true, // Highlighted tab in image
            onClick = {},
            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Livros") },
            label = { Text("Livros", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Perfil") },
            label = { Text("Perfil", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
            colors = navBarColors()
        )
    }
}

@Composable
private fun navBarColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = AzureBlue,
    selectedTextColor = AzureBlue,
    indicatorColor = BadgeBlueBg,
    unselectedIconColor = NavUnselected,
    unselectedTextColor = NavUnselected
)

// ══════════════════════════════════════════════════════════════════════════════
// Preview
// ══════════════════════════════════════════════════════════════════════════════
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessoresPreview() {
    val navController = rememberNavController()

    MaterialTheme {
        LivroProfessoresScreen(navController)
    }
}
