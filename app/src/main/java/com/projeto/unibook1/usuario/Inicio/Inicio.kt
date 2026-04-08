package com.projeto.unibook1.usuario.Inicio

import android.R
import android.graphics.Color.green
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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

class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                TelaInicial(onReservaClick = {})
            }
        }
    }
}
@Composable
fun TelaInicial(onReservaClick: () -> Unit) {
    var nomeAluno by remember { mutableStateOf("Nome do Aluno") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "\uD83D\uDCD6\u200B ")
            Text(text = "Unifriend",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold

            )
            Button(
                onClick = { onReservaClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                modifier = Modifier.size(48.dp).background(color = Color(0xFF1976D2))
            ){
                Text(
                    text = "Notificações",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Gray
                )

            }

        }
        Text(text = "Olá, $nomeAluno! 👋", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Bem-vindo à biblioteca da Unifor!")
    }


}

@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    TelaInicial(onReservaClick = {})
}

