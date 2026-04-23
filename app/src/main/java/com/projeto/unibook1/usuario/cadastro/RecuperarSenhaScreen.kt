package com.projeto.unibook1.usuario.cadastro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecuperarSenhaScreen(
    onVoltarLogin: () -> Unit,
    onContinuar: () -> Unit  // 👈 adicionado
) {
    var email by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Ícone
        Surface(
            shape = RoundedCornerShape(50),
            color = Color(0xFFE3F0FF),
            modifier = Modifier.size(80.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Filled.School,
                    contentDescription = "Logo",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Recuperar Senha",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Insira seu e-mail institucional para receber as instruções de redefinição",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "E-MAIL INSTITUCIONAL",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("aluno@unifor.br", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2196F3),
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        mensagem?.let {
            Text(
                text = it,
                color = if (it.contains("enviado", ignoreCase = true)) Color(0xFF2E7D32) else Color.Red,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        Button(
            onClick = {
                if (email.isBlank()) {
                    mensagem = "Por favor, insira seu e-mail institucional."
                } else if (!email.endsWith("@unifor.br")) {
                    mensagem = "Use um e-mail institucional válido (@unifor.br)."
                } else {
                    isLoading = true
                    mensagem = null
                    isLoading = false
                    mensagem = "Instruções enviadas! Verifique seu e-mail."
                    onContinuar() // 👈 adicionado
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            } else {
                Text("Enviar Instruções", color = Color.White, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onVoltarLogin) {
            Text(
                text = "Voltar para o Login",
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecuperarSenhaPreview() {
    RecuperarSenhaScreen(onVoltarLogin = {}, onContinuar = {})  // 👈 adicionado no preview
}