package com.projeto.unibook1.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminLoginScreen(
    onNavigateToForgotPassword: () -> Unit,
    onLoginSuccess: () -> Unit
) {

    var matricula by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Área do Administrador", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = matricula,
            onValueChange = { novoValor ->

                if (novoValor.all { it.isDigit() }) {
                    matricula = novoValor
                }
            },
            label = { Text("Matrícula") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Abre o teclado numérico
        )

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },

            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = onNavigateToForgotPassword,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = "Esqueci a senha",
                color = Color(0xFFA056F3),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        Button(
            onClick = {
                // a lógica de validação
                onLoginSuccess()
            },
            modifier = Modifier.width(280.dp)
        ) {
            Text("Entrar")
        }
    }
}