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
fun AdminLoginScreen(onNavigateToRegister: () -> Unit) {
    // ESTADOS: Guardam o que o usuário digita
    var matricula by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement=Arrangement.Center,
        horizontalAlignment=Alignment.CenterHorizontally
    ) {

        Text(
            text = "Área do Administrador",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // CAMPO MATRÍCULA (Apenas números)
        OutlinedTextField(
            value = matricula,
            onValueChange = { novoValor ->
                // Só aceita se for número
                if (novoValor.all { it.isDigit() }) {
                    matricula = novoValor
                }
            },
            label = { Text("Matrícula") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CAMPO SENHA
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // BOTÃO ENTRAR
        Button(
            onClick = {
                // Lógica de login (por enquanto apenas log no console)
                println("Tentativa de login: $matricula")
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TEXTO PARA CADASTRO
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Não tem conta?")
            TextButton(onClick = onNavigateToRegister) {
                Text(
                    text = "Cadastre-se",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}