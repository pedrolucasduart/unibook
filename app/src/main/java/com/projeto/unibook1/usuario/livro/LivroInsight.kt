package com.projeto.unibook1.usuario.livro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// ── Color palette ──────────────────────────────────────────────────────────────
private val AzureBlue        = Color(0xFF1A73E8)
private val AzureBlueDark    = Color(0xFF1558B0)
private val HeroBg           = Color(0xFFF0F4FB)
private val SectionBg        = Color(0xFFF5F7FA)
private val CardBg           = Color.White
private val CardBorder       = Color(0xFFE4E8EF)
private val BlueBorderAccent = Color(0xFF1A73E8)
private val StarYellow       = Color(0xFFFFC107)
private val TextPrimary      = Color(0xFF1A1A1A)
private val TextSecondary    = Color(0xFF777777)
private val TextBlue         = Color(0xFF1A73E8)
private val BadgeBlueBg      = Color(0xFFDEEAFD)
private val BadgeBlueText    = Color(0xFF1558B0)
private val EspecialistaBg   = Color(0xFFDEEAFD)
private val EspecialistaText = Color(0xFF1558B0)
private val AvatarPurpleBg   = Color(0xFF7E57C2)
private val AvatarPurpleText = Color.White
private val AvatarGreenBg    = Color(0xFF4CAF50)
private val AvatarGreenText  = Color.White
private val AvatarOrangeBg   = Color(0xFFFF7043)
private val AvatarOrangeText = Color.White
private val BookCoverTeal    = Color(0xFF6E9E8F)
private val BookCoverBg      = Color(0xFFEEF5F3)
private val DividerColor     = Color(0xFFECEFF4)
private val NavSelected      = Color(0xFF1A73E8)
private val NavUnselected    = Color(0xFF9E9E9E)
private val ButtonBlue       = Color(0xFF1A73E8)
private val DescBlue         = Color(0xFF1A73E8)

// ══════════════════════════════════════════════════════════════════════════════
// Entry point  (swap out if embedding in an existing Activity)
// ══════════════════════════════════════════════════════════════════════════════


// ══════════════════════════════════════════════════════════════════════════════
// Root screen
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun LivroInsightScreen(navController: NavController) {
    Column {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar"
            )
        }

        Text("Tela de Insight")
    }


    Scaffold(
        containerColor = Color.White,
        topBar = { InsightTopBar() },
        bottomBar = { InsightBottomBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 90.dp)          // room for FAB-style button
            ) {
                Spacer(Modifier.height(8.dp))
                HeroCard()
                Spacer(Modifier.height(16.dp))
                ProfessoresSection()
                Spacer(Modifier.height(16.dp))
                MonitoresSection()
                Spacer(Modifier.height(16.dp))
                AlunosSection()
                Spacer(Modifier.height(16.dp))
            }

            // "Adicionar minha nota" sticky button
            AdicionarNotaButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 1 · Top App Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun InsightTopBar() {
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
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Book Insights",
            fontSize = 19.sp,
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
// 2 · Hero Card  (book cover + meta)
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun HeroCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(HeroBg)
            .padding(16.dp)
    ) {
        Column {
            // Book cover
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BookCoverBg),
                contentAlignment = Alignment.Center
            ) {
                // Simulated teal cover with text overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(BookCoverTeal),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text("CAPA",     fontSize = 9.sp,  color = Color.White.copy(alpha = 0.85f), fontWeight = FontWeight.Light)
                        Text("DO",       fontSize = 9.sp,  color = Color.White.copy(alpha = 0.85f), fontWeight = FontWeight.Light)
                        Spacer(Modifier.height(4.dp))
                        Text("PRINCÍPIOS", fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        Text("DE",       fontSize = 9.sp,  color = Color.White.copy(alpha = 0.85f), fontWeight = FontWeight.Light)
                        Spacer(Modifier.height(6.dp))
                        // decorative lines
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(1.dp)
                                    .background(Color.White.copy(alpha = 0.4f))
                            )
                            Spacer(Modifier.height(3.dp))
                        }
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            // Rating pill
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(BadgeBlueBg)
                    .padding(horizontal = 12.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Star, null, tint = StarYellow, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "4.8  (124 notas)",
                    fontSize = 13.sp,
                    color = BadgeBlueText,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Princípios de Design Visual",
                fontSize = 22.sp,
                fontWeight = FontWeight.Light,
                color = TextPrimary,
                lineHeight = 28.sp
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Bruce Block & Elena Vitruvia",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Exploração profunda dos componentes visuais do cinema e design, " +
                        "focando em espaço, linha, forma, tom, cor e movimento.",
                fontSize = 14.sp,
                color = DescBlue,
                lineHeight = 20.sp
            )
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 3 · Dicas de Professores
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun ProfessoresSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        SectionHeader(
            icon = Icons.Default.School,
            title = "Dicas de Professores"
        )
        Spacer(Modifier.height(10.dp))

        // Card with blue left border
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .background(CardBg)
        ) {
            // Blue left accent bar
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(BlueBorderAccent)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                // Author row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // RR avatar
                    InitialsAvatar(initials = "RR", bg = AvatarPurpleBg, textColor = AvatarPurpleText)
                    Spacer(Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Prof. Ricardo Rocha",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(text = "Há 2 dias", fontSize = 12.sp, color = TextSecondary)
                    }
                    // ESPECIALISTA badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(EspecialistaBg)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "ESPECIALISTA",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = EspecialistaText
                        )
                    }
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Livro base para a disciplina de Composição Visual. " +
                            "Recomendo focar no capítulo 4 (Contraste e Afinidade), pois define toda a " +
                            "estrutura de tensão visual necessária para projetos de interface modernos.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 19.sp
                )
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 4 · Dicas de Monitores
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun MonitoresSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        SectionHeader(
            icon = Icons.Default.Shield,
            title = "Dicas de Monitores"
        )
        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
                .background(CardBg)
                .padding(12.dp)
        ) {
            Column {
                // Author row with photo-style avatar
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Photo placeholder avatar
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF8D6E63)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Ana Clara - Monitora",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(text = "Há 1 semana", fontSize = 12.sp, color = TextSecondary)
                    }
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "O exemplar disponível na biblioteca central possui anotações úteis feitas " +
                            "por alunos de anos anteriores na margem da página 112 sobre a regra dos " +
                            "terços aplicada ao design mobile.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 19.sp
                )
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 5 · Comentários de Alunos
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun AlunosSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        SectionHeader(
            icon = Icons.Default.Forum,
            title = "Comentários de Alunos"
        )
        Spacer(Modifier.height(10.dp))

        StudentCommentCard(
            quote = "\"A explicação sobre ritmo visual mudou a forma como eu organizo meus grids " +
                    "no Figma. Muito prático!\"",
            initials = "LM",
            avatarBg = Color(0xFF1A73E8),
            name = "Lucas Martins",
            role = "Estudante de UX"
        )

        Spacer(Modifier.height(10.dp))

        StudentCommentCard(
            quote = "\"Leitura densa mas essencial. Recomendo ler um capítulo e tentar identificar " +
                    "os princípios em apps reais.\"",
            initials = "BF",
            avatarBg = Color(0xFF546E7A),
            name = "Beatriz Fonseca",
            role = "Design Gráf..."
        )
    }
}

@Composable
fun StudentCommentCard(
    quote: String,
    initials: String,
    avatarBg: Color,
    name: String,
    role: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(12.dp))
            .background(CardBg)
            .padding(14.dp)
    ) {
        Column {
            Text(
                text = quote,
                fontSize = 13.sp,
                color = TextSecondary,
                lineHeight = 19.sp,
                fontStyle = FontStyle.Italic
            )
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                InitialsAvatar(initials = initials, bg = avatarBg, textColor = Color.White, size = 34)
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(text = name, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text(text = role, fontSize = 12.sp, color = TextSecondary)
                }
            }
        }
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 6 · "Adicionar minha nota" sticky button
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun AdicionarNotaButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AddBox,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Adicionar minha nota",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// 7 · Bottom Navigation Bar
// ══════════════════════════════════════════════════════════════════════════════
@Composable
fun InsightBottomBar() {
    HorizontalDivider(color = DividerColor, thickness = 0.5.dp)
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Library", modifier = Modifier.size(22.dp)) },
            label = { Text("Library", fontSize = 11.sp) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Groups, contentDescription = "Community", modifier = Modifier.size(22.dp)) },
            label = { Text("Community", fontSize = 11.sp) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.StickyNote2, contentDescription = "Notes", modifier = Modifier.size(22.dp)) },
            label = { Text("Notes", fontSize = 11.sp) },
            colors = navBarColors()
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile", modifier = Modifier.size(22.dp)) },
            label = { Text("Profile", fontSize = 11.sp) },
            colors = navBarColors()
        )
    }
}

@Composable
private fun navBarColors() = NavigationBarItemDefaults.colors(
    selectedIconColor   = NavSelected,
    selectedTextColor   = NavSelected,
    unselectedIconColor = NavUnselected,
    unselectedTextColor = NavUnselected,
    indicatorColor      = Color.Transparent
)

// ══════════════════════════════════════════════════════════════════════════════
// Shared helpers
// ══════════════════════════════════════════════════════════════════════════════

/** Coloured circle with 2-letter initials */
@Composable
fun InitialsAvatar(
    initials: String,
    bg: Color,
    textColor: Color,
    size: Int = 40
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(bg),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            fontSize = (size * 0.35f).sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

/** Section title row with icon */
@Composable
fun SectionHeader(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AzureBlue,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
    }
}

// ══════════════════════════════════════════════════════════════════════════════
// Preview
// ══════════════════════════════════════════════════════════════════════════════
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LivroInsightPreview() {
    val navController = rememberNavController()

    MaterialTheme {
        LivroInsightScreen(navController)
    }
}
