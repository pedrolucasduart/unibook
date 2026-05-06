package com.projeto.unibook1.usuario.mapa

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onReservaClick: () -> Unit
) {
    var andarSelecionado by remember { mutableStateOf("Térreo") }

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
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Unifriends",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "🔔")
            }

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Pesquisar Localização") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (andarSelecionado == "Térreo") {
                    Button(
                        onClick = { andarSelecionado = "Térreo" },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                    ) { Text(text = "Térreo", color = Color.White) }
                } else {
                    OutlinedButton(
                        onClick = { andarSelecionado = "Térreo" },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF8B9CB6))
                    ) { Text(text = "Térreo") }
                }

                if (andarSelecionado == "1º Andar") {
                    Button(
                        onClick = { andarSelecionado = "1º Andar" },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                    ) { Text(text = "1º Andar", color = Color.White) }
                } else {
                    OutlinedButton(
                        onClick = { andarSelecionado = "1º Andar" },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF8B9CB6))
                    ) { Text(text = "1º Andar") }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4F8))
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (andarSelecionado == "Térreo") "[ Imagem do Mapa Térreo Aqui ]" else "[ Imagem do Mapa 1º Andar Aqui ]",
                        color = Color(0xFF8B9CB6),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Status da sua prateleira",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Card(modifier = Modifier.weight(1f)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "\uD83D\uDD13")
                            Text(text = "10 LIVRES")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Armários", fontWeight = FontWeight.Bold)
                        Text(text = "Bloco A - Térreo")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { onReservaClick() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Reserva")
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Card(modifier = Modifier.weight(1f)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "\uD83E\uDDFA")
                            Text(text = "Esgotado")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Armários", fontWeight = FontWeight.Bold)
                        Text(text = "Entrada Principal")
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4F8))
                        ) {
                            Text(
                                text = "Indisponível",
                                textAlign = TextAlign.Center,
                                color = Color(0xFF8B9CB6),
                                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "LEGENDA DO MAPA",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF8B9CB6)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (andarSelecionado == "Térreo") {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🔵 ")
                                Text(text = "Engenharia")
                            }
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🟠 ")
                                Text(text = "Humanas")
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "\uD83D\uDFE3")
                                Text(text = "Direito")
                            }
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "\uD83D\uDFE2")
                                Text(text = "Salas de Estudo")
                            }
                        }
                    } else {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🔴 ")
                                Text(text = "Saúde")
                            }
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "⚪ ")
                                Text(text = "Informática")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun BottomNavBar(
    onInicioClick: () -> Unit,
    onMapaClick: () -> Unit,
    onLivrosClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "🏠 Início", modifier = Modifier.clickable { onInicioClick() })
        Text(text = "🗺️ Mapa", modifier = Modifier.clickable { onMapaClick() })
        Text(text = "📚 Livros", modifier = Modifier.clickable { onLivrosClick() })
        Text(text = "👤 Perfil", modifier = Modifier.clickable { onPerfilClick() })
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MaterialTheme {
        MapScreen(
            navController = rememberNavController(),
            onReservaClick = {}
        )
    }
}