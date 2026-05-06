package com.projeto.unibook1.usuario.Inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun NotificacoesScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                onInicioClick = { navController.navigate("inicio") },
                onMapaClick = { navController.navigate("mapa") },
                onLivrosClick = { navController.navigate("livros") },
                onPerfilClick = { navController.navigate("perfil") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF6F6F9))
                .padding(14.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "⬅️ Notificações",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "HOJE", color = Color(0xFF485569), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("⏰")
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Prazo de devolução próximo", fontWeight = FontWeight.Bold)
                        Text("O Livro de Psicologia Social deve ser devolvido até as 18h", style = MaterialTheme.typography.bodyMedium)
                    }
                    Text("10:40", color = Color.Gray)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificacoesPreview() {
    NotificacoesScreen(navController = rememberNavController())
}