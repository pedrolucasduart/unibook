package com.projeto.unibook1.telasgerais

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.R
import com.projeto.unibook1.usuario.Inicio.BottomNavBar

@Composable
fun AcessoBiblioteca(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
){
    Scaffold(
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF6F6F9))
                .verticalScroll(rememberScrollState())
                .padding(14.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "⬅\uFE0F\u200B Acesso à Biblioteca",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBackClick() }
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Meu Indentificador",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Apresente esse código na entrada para liberar seu acesso.")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "QR CODE"
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "\uD83D\uDD35\u200B CÓDIGO ATIVO",
                        color = Color(0xFF1976D2),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = R.drawable.img_1),
                            contentDescription = "QR CODE"
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Column {
                            Text(text = "Lucas Silva",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "Estudante de Psicologia",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF1976D2),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
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
                        modifier = Modifier.padding(16.dp),

                        ) {
                        Text(text = "Matrícula",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        Text(text = "2510368",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
//                Spacer(modifier = Modifier.width(20.dp))
                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),

                        ) {
                        Text(text = "Validade",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        Text(text = "Dez 2026",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }


            }
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF1F5))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "⚠\uFE0F\u200B")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Se o código não for lido corretamente,\n" +
                            "certifique-se de que o brilho da sua tela está no máximo e tente novamente.",
                        color = Color(0xFF485569)
                    )

                }
            }

        }




    }

}

@Preview(showBackground = true)
@Composable
fun AcessoBibliotecaPreview() {
    AcessoBiblioteca(onBackClick = {})
}
