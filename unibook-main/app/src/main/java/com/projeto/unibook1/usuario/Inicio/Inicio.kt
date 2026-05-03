package com.projeto.unibook1.usuario.Inicio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.unibook1.telasgerais.AcessoBiblioteca
import com.projeto.unibook1.usuario.mapa.MapScreen

class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                
                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio") {
                        TelaInicial(
                            onReservaClick = { navController.navigate("reservas") },
                            onQrCodeClick = { navController.navigate("acesso_biblioteca") },
                            onMapaClick = { navController.navigate("mapa") },
                            onArmarioClick = { navController.navigate("verificar_armario") }
                        )
                    }
                    composable("reservas") {
                        TelaReservas(onBackClick = { navController.popBackStack() })
                    }
                    composable("acesso_biblioteca") {
                        AcessoBiblioteca(onBackClick = { navController.popBackStack() })
                    }
                    composable("mapa") {
                        MapScreen(
                            onBackClick = { navController.popBackStack() },
                            onReservaClick = { navController.navigate("reservas") }
                        )
                    }
                    composable("verificar_armario") {
                        ArmarioScreen(
                            onBackClick = { navController.popBackStack() },
                            onPerdiChaveClick = { navController.navigate("perdi_chave") }
                        )
                    }
                    composable("perdi_chave") {
                        PerdiScreen(onBackClick = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
@Composable
fun TelaInicial(
    onReservaClick: () -> Unit,
    onQrCodeClick: () -> Unit,
    onMapaClick: () -> Unit,
    onArmarioClick: () -> Unit
) {
    var nomeAluno by remember { mutableStateOf("Nome do Aluno") }
    var livrosAtivos by remember { mutableStateOf("3") }
    var totalReservas by remember { mutableStateOf("1") }


    Scaffold(
        bottomBar = {BottomNavBar()}
    ) {paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF6F6F9)).padding(14.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "\uD83D\uDCD6\u200B Unifriend",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold

                )
                Button(
                    onClick = { /* Ação de abrir notificações*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6F6F9)),

                    ){
                    Text(
                        text = "\uD83D\uDD14\u200B",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Gray
                    )

                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Olá, $nomeAluno! 👋",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Bem-vindo à biblioteca da Unifor!")

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("\uD83D\uDD0D\u200B Livro, autor, assunto ou professor...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                border = BorderStroke(1.dp, Color(0xFFF0F0F0)),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "EMPRÉSTIMOS E RESERVAS",
                        color = Color(0xFF1976D2),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelLarge,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (livrosAtivos.length == 1) "0$livrosAtivos" else livrosAtivos,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1C274C)
                            )
                            Text(
                                text = "Livros\nAtivos",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF5C616F)
                            )
                        }

                        // Divisor vertical
                        Box(
                            modifier = Modifier
                                .height(45.dp)
                                .width(1.dp)
                                .background(Color(0xFFE0E0E0))
                        )

                        Column(modifier = Modifier.weight(1f).padding(start = 24.dp)) {
                            Text(
                                text = if (totalReservas.length == 1) "0$totalReservas" else totalReservas,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF8D4B1F)
                            )
                            Text(
                                text = "Reservas",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF5C616F)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Ver detalhes →",
                        color = Color(0xFF1976D2),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable { onReservaClick() }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9EFEE)),
                border = BorderStroke(1.dp, Color(0xFFF2DEDE))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ALERTAS RECENTES",
                            color = Color(0xFF8B1A10),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "⚠", color = Color(0xFF8B1A10))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Prazo de devolução amanhã",
                        color = Color(0xFF5D120D),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Livro: Psicologia Experimental",
                        color = Color(0xFF8B1A10),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onReservaClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B1A10)),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                        modifier = Modifier.height(38.dp)
                    ) {
                        Text("Renovar", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Acesso Rápido",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Column(
                        modifier = Modifier.background(Color.White)
                            .padding(10.dp),
                    ) {
                        Button(
                            onClick = { onQrCodeClick() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.background(color = Color.White),

                            ) {
                            Text(text = "\uD83D\uDCF7\u200B \n QR \n Code",
                                color = Color.Black
                            )
                        }

                    }
                }

                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Column(
                        modifier = Modifier.background(Color.White)
                            .padding(10.dp),
                    ) {
                        Button(
                            onClick = { onMapaClick() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.background(color = Color.White),

                            ) {
                            Text(text = "\uD83D\uDDFA\uFE0F\u200B \n Mapa",
                                color = Color.Black
                            )
                        }

                    }
                }


                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Column(
                        modifier = Modifier.background(Color.White)
                            .padding(10.dp),
                    ) {
                        Button(
                            onClick = { onArmarioClick() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.background(color = Color.White),

                            ) {
                            Text(text = "\uD83D\uDDC4\uFE0F\u200B \nArmario",
                                color = Color.Black
                            )
                        }

                    }
                }





            }
        }
    }



}


@Composable
fun BottomNavBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "🏠 Início")
        Text(text = "🗺️ Mapa")
        Text(text = "📚 Livros")
        Text(text = "👤 Perfil")
    }
}

@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    TelaInicial(
        onReservaClick = {},
        onQrCodeClick = {},
        onMapaClick = {},
        onArmarioClick = {}
    )
}

