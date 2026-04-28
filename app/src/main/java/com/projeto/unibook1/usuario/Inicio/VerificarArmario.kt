package com.projeto.unibook1.usuario.Inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArmarioScreen(
    onReservaClick: () -> Unit = {}
){
    Scaffold(
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF6F6F9)).padding(14.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "⬅️ Verificar Armário",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0XFF2377D2))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(text = "⚪ ATIVO",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                        )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Armário 123",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium
                        )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Tamanho: Grande",
                        color = Color(0xFFD1E3F6),
                        )

                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0XFF3582D7))
                    ) {
                        Row(
                            modifier = Modifier.padding(6.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Perdeu a chave?",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                                )

                            Button(
                                onClick = { /* Ação de ir para  "Perdi minha chave" */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            ) {
                                Text(text = "🗝️")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "COMPARTILHADO COM", color = Color(0xFF485569))
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = com.projeto.unibook1.R.drawable.avatar),
                            contentDescription = "Foto de perfil",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.White, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Zíltom Machado",
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Text(text = "💬")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "📍")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Localização:")
                        Text(text = "Bloco C - Piso 2",
                            fontWeight = FontWeight.Bold,
                            )

                    }
                }

                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "🗓️")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Expira em:")
                        Text(text = "25 dez 2026",
                            fontWeight = FontWeight.Bold,
                        )

                    }
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ArmariosPreview() {
    ArmarioScreen(onReservaClick = {})
}
