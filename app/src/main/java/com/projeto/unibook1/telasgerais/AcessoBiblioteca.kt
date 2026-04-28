package com.projeto.unibook1.telasgerais

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.usuario.Inicio.BottomNavBar

@Composable
fun AcessoBiblioteca() {
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
            Text(text = "⬅️ Acesso à Biblioteca",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Meu Identificador", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Apresente esse código na entrada para liberar seu acesso.", color = Color.Gray, fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(32.dp))
                Card(modifier = Modifier.size(250.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("QR CODE", fontWeight = FontWeight.Bold)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "🔵 CÓDIGO ATIVO", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(60.dp).background(Color.LightGray))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = "Lucas Silva", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                        Text(text = "Estudante de Psicologia", color = Color(0xFF1976D2))
                    }
                }
            }
        }
    }
}
