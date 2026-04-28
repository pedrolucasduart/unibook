package com.projeto.unibook1.usuario.Inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projeto.unibook1.usuario.mapa.BottomNavBar

@Composable
fun TelaReservas(
    modifier: Modifier = Modifier,
){

    Scaffold(
        modifier = modifier,
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F9))
                .padding(paddingValues)
                .padding(14.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Empréstimos e Reservas",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
                )

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Olá, Narak! 👋",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Você tem 2 livros para devolver em breve")

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "Empréstimos Atuais",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )

                Card { }
            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun TelaReservasPreview() {
        TelaReservas()
}

