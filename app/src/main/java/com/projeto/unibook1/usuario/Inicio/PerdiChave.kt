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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PerdiScreen() {
    Scaffold(
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF6F6F9))
                .padding(14.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "⬅️ Perdi minha chave",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(text = "🗝️", style = MaterialTheme.typography.displayLarge)
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Fique calmo, Unifriend! \nTudo tem uma solução.",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Não se preocupe. Para recuperar o acesso ao seu armário ou solicitar uma nova chave, por favor, dirija-se à recepção principal da biblioteca munido do seu número de matrícula ou documento com foto.",
                textAlign = TextAlign.Center, color = Color.Gray)
            
            Spacer(modifier = Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "NÚMERO DO ARMÁRIO", fontWeight = FontWeight.Bold, color = Color.Gray)
                    Text(text = "123", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF1F7))) {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("📍")
                    Text(text = "Recepção Principal", fontWeight = FontWeight.Bold)
                    Text(text = "Biblioteca Central")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerdiPreview() {
    PerdiScreen()
}
