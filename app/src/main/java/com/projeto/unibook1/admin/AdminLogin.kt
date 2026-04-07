package com.projeto.unibook1.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun AdminLoginScreen() {

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

        Button(
            onClick = {

                println("Logando com: $matricula")
            },
            modifier = Modifier.width(280.dp)
        ) {
            Text("Entrar")
        }
    }
}