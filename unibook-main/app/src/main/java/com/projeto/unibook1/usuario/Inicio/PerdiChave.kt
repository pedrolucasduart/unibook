package com.projeto.unibook1.usuario.Inicio

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun PerdiScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
){
    Scaffold(
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF6F6F9)).padding(14.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "⬅\uFE0F\u200B Perdi minha chave",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = com.projeto.unibook1.R.drawable.chave_perdida),
                    contentDescription = "Chave"
                )

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Fique calmo, Unifriend! \nTudo tem uma solução.",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Não se preocupe. Para recuperar o\n" +
                        "acesso ao seu armário ou solicitar\n" +
                        "uma nova chave, por favor, dirija-se à\n" +
                        "recepção principal da biblioteca\n" +
                        "munido do seu número de matrícula ou\n" +
                        "documento com foto.",
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "NÚMERO DO ARMÁRIO",
                        color = Color(0xFF485569),
                        fontWeight = FontWeight.Bold
                        )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "123",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFF1976D2)
                        )

                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF1F7))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "\uD83D\uDCCD\u200B",
                        color = Color(0xFF485569),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Recepção Principal",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(text = "Biblioteca Central")

                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEEF1F5))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "⚠\uFE0F\u200B")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "A substituição da chave pode estar\n" +
                            "sujeita a uma pequena taxa\n" +
                            "administrativa. Consulte as normas de\n" +
                            "uso do seu campus.",
                        color = Color(0xFF485569)
                    )

                }
            }

        }

    }
}




@Preview(showBackground = true)
@Composable
fun PerdiPreview() {
    PerdiScreen(onBackClick = {})
}