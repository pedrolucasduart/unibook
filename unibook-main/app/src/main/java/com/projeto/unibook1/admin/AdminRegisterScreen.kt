package com.projeto.unibook1.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AdminRegisterScreen(onBackToLogin: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cadastro de Admin", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Nome Completo") })
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Matrícula") })
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Senha") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Lógica de cadastro */ }) {
            Text("Finalizar Cadastro")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onBackToLogin) {
            Text("Já tem conta? Faça Login")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminRegisterScreenPreview() {
    // Passamos um bloco vazio {} para o parâmetro de navegação
    AdminRegisterScreen(onBackToLogin = {})
}