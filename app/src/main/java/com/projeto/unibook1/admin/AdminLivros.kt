package com.projeto.unibook1.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val RoxoForte = Color(0xFF7B2CBF) // Cor primária do design

data class LivroMock(val titulo: String, val autor: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLivros(
    onNavigateToHome: () -> Unit,
    onNavigateToEmprestimos: () -> Unit,
    onNavigateToLivros: () -> Unit,
    onNavigateToAddBook: () -> Unit,
    onNavigateToEditBook: () -> Unit
) {

    val listaDeLivros = listOf(
        LivroMock("Duna", "Frank Herbert"),
        LivroMock("Steve Jobs", "Walter Isaacson"),
        LivroMock("1984", "George Orwell"),
        LivroMock("Minha História", "Michelle Obama")
    )

    Scaffold(
        containerColor = Color(0xFFF4F6F9),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF4F6F9)),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.MenuBook, contentDescription = null, tint = RoxoForte)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "COLEÇÃO DE LIVROS",
                            style = MaterialTheme.typography.titleMedium,
                            color = RoxoForte,
                            fontWeight = FontWeight.Black
                        )
                    }
                },
                actions = {

                }
            )
        },
        bottomBar = {
            AdminBottomNavBar(
                currentRoute = "admin_livros",
                onNavigateToHome = onNavigateToHome,
                onNavigateToEmprestimos = onNavigateToEmprestimos,
                onNavigateToLivros = onNavigateToLivros

            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Procurar livro") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = RoxoForte,
                    unfocusedBorderColor = Color.Transparent,
                )
            )


            Button(
                onClick = { onNavigateToAddBook() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RoxoForte),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Adicionar livro", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "Livros gerais",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(listaDeLivros) { livro ->
                    CardLivro(livro = livro, onClick = onNavigateToEditBook)
                }
            }
        }
    }
}

@Composable
fun CardLivro(livro: LivroMock, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable{onClick()}
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray) // Aqui entrará a imagem no futuro
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nome e Autor
        Text(text = livro.titulo, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
        Text(text = livro.autor, fontSize = 12.sp, color = Color.Gray)
    }
}


@Composable
fun NavItem(nome: String, icone: androidx.compose.ui.graphics.vector.ImageVector, isActive: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isActive) Color(0xFFF3E5F5) else Color.Transparent
    val contentColor = if (isActive) RoxoForte else Color.Gray

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icone, contentDescription = nome, tint = contentColor, modifier = Modifier.size(20.dp))
        if (isActive) {
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = nome, color = contentColor, fontWeight = FontWeight.Bold, fontSize = 10.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLivros() {
    AdminLivros({},{}, {}, {}, {})
}