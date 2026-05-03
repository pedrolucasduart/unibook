package com.projeto.unibook1.usuario.Inicio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaInicial(
    onReservaClick: () -> Unit,
    onQrCodeClick: () -> Unit,
    onMapaClick: () -> Unit,
    onArmarioClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    var nomeAluno by remember { mutableStateOf("Lucas") }
    var livrosAtivos by remember { mutableStateOf("3") }
    var totalReservas by remember { mutableStateOf("1") }

    Scaffold(
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F9))
                .padding(paddingValues)
                .padding(14.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Unifriend",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { /* Notificações */ }) {
                    Text(text = "🔔", style = MaterialTheme.typography.headlineSmall)
                }
            }
            
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Olá, $nomeAluno! 👋", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Text(text = "Bem-vindo à biblioteca da Unifor!")

            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("🔍 Livro, autor, assunto...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .clickable { onSearchClick() },
                enabled = false,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledBorderColor = Color.Gray,
                    disabledPlaceholderColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, Color(0xFFF0F0F0)),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("EMPRÉSTIMOS E RESERVAS", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = livrosAtivos.padStart(2, '0'), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Text(text = "Livros Ativos", style = MaterialTheme.typography.bodySmall)
                        }
                        Box(modifier = Modifier.height(45.dp).width(1.dp).background(Color(0xFFE0E0E0)))
                        Column(modifier = Modifier.weight(1f).padding(start = 24.dp)) {
                            Text(text = totalReservas.padStart(2, '0'), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Color(0xFF8D4B1F))
                            Text(text = "Reservas", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Ver detalhes →", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onReservaClick() })
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9EFEE)),
                border = BorderStroke(1.dp, Color(0xFFF2DEDE))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("ALERTAS RECENTES", color = Color(0xFF8B1A10), fontWeight = FontWeight.Bold)
                        Text("⚠️")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Prazo de devolução amanhã", fontWeight = FontWeight.Bold, color = Color(0xFF5D120D))
                    Text("Livro: Psicologia Experimental", color = Color(0xFF8B1A10))
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = { /* Renovar */ }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B1A10))) {
                        Text("Renovar")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Acesso Rápido", fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                QuickAccessButton("📷\nQR Code", onQrCodeClick)
                QuickAccessButton("🗺️\nMapa", onMapaClick)
                QuickAccessButton("🗄️\nArmário", onArmarioClick)
            }
        }
    }
}

@Composable
fun QuickAccessButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE9F1FB)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.size(100.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        Text(text = text, color = Color.Black, textAlign = TextAlign.Center, fontSize = 12.sp)
    }
}

@Composable
fun BottomNavBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Text("🏠") },
            label = { Text("Início") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("🗺️") },
            label = { Text("Mapa") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("📚") },
            label = { Text("Livros") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("👤") },
            label = { Text("Perfil") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    TelaInicial(
        onReservaClick = {},
        onQrCodeClick = {},
        onMapaClick = {},
        onArmarioClick = {},
        onSearchClick = {}
    )
}
