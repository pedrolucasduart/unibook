package com.projeto.unibook1.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.AdminColor

// Modelo de dados específico para esta tela
data class AlunoBloqueado(val nome: String, val matricula: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAlunosBloqueados(onBack: () -> Unit) {
    val listaBloqueados = listOf(
        AlunoBloqueado("Ana Beatriz Cavalcanti", "MATRÍCULA: 2023001042"),
        AlunoBloqueado("Carlos Eduardo Mendes", "MATRÍCULA: 2022004591"),
        AlunoBloqueado("Mariana Silva Braga", "MATRÍCULA: 2023001180"),
        AlunoBloqueado("Ricardo Ferreira Lima", "MATRÍCULA: 2021008722"),
        AlunoBloqueado("Juliana Costa Pereira", "MATRÍCULA: 2023002015")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "ALUNOS BLOQUEADOS",
                        color = AdminColor.PrimaryPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null, tint = AdminColor.TextGray)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Menu */ }) {
                        Icon(Icons.Default.MoreVert, null, tint = AdminColor.TextGray)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AdminColor.BackgroundGray)
            )
        },
        bottomBar = { BottomNavigationBarAlunos() },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            SearchBarBloqueados()

            Spacer(modifier = Modifier.height(24.dp))

            // Lista de Alunos
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(listaBloqueados) { aluno ->
                    CardAlunoBloqueado(aluno)
                }
            }
        }
    }
}

@Composable
fun CardAlunoBloqueado(aluno: AlunoBloqueado) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 6.dp // Efeito de sombra da imagem
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = aluno.nome,
                    color = AdminColor.PrimaryPurple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = aluno.matricula,
                    color = AdminColor.TextGray,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Botão de Lupa (Detalhes)
            Surface(
                modifier = Modifier.size(45.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF8F0FF), // Roxo bem clarinho
                shadowElevation = 2.dp
            ) {
                IconButton(onClick = { /* Detalhes */ }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = AdminColor.PrimaryPurple,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBarBloqueados() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        color = AdminColor.SearchBarGray,
        shadowElevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(Icons.Default.Search, null, tint = Color.LightGray)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Buscar aluno bloqueado...", color = Color.LightGray, fontSize = 15.sp)
        }
    }
}

@Composable
fun BottomNavigationBarAlunos() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Início") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, null) },
            label = { Text("Alunos") },
            selected = true,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AdminColor.PrimaryPurple,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Book, null) },
            label = { Text("Livros") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Perfil") },
            selected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminAlunosBloqueadosPreview() {
    AdminAlunosBloqueados(onBack = {})
}