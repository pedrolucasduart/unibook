package com.projeto.unibook1.usuario.cadastro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CadastroScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToSuporte: () -> Unit,
    emailsJaCadastrados: List<String> = emptyList()
) {
    var nomeCompleto by remember { mutableStateOf("") }
    var matricula by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf("") }

    fun validar(): Boolean {
        if (nomeCompleto.isBlank() || matricula.isBlank() || email.isBlank() ||
            senha.isBlank() || confirmarSenha.isBlank()
        ) {
            mensagemErro = "Preencha todos os campos"
            return false
        }
        if (emailsJaCadastrados.contains(email)) {
            mensagemErro = "O e-mail informado já está cadastrado"
            return false
        }
        val senhaValida = senha.length >= 8 &&
                senha.any { it.isUpperCase() } &&
                senha.any { it.isDigit() }
        if (!senhaValida) {
            mensagemErro = "A senha deve atender aos requisitos mínimos"
            return false
        }
        mensagemErro = ""
        return true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onNavigateToLogin) {
                Text("←", fontSize = 20.sp, color = Color(0xFF2196F3))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Surface(
            shape = RoundedCornerShape(50),
            color = Color(0xFFE3F0FF),
            modifier = Modifier.size(72.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("🎓", fontSize = 32.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Criar Conta", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(
            "Preencha os dados abaixo para acessar a biblioteca",
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        CampoTexto("Nome Completo", "Seu nome completo", nomeCompleto) { nomeCompleto = it }
        CampoTexto("Número da Matrícula", "ex. 2023-0045", matricula) { matricula = it }
        CampoTexto("E-mail Institucional", "aluno@unifor.br", email, KeyboardType.Email) { email = it }
        CampoSenha("Senha", senha) { senha = it }
        CampoSenha("Confirmar Senha", confirmarSenha) { confirmarSenha = it }

        RequisitosSenha(senha = senha)

        if (mensagemErro.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(mensagemErro, color = Color.Red, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { validar() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Cadastrar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onNavigateToLogin) {
            Text("Já possui conta? Fazer Login", color = Color(0xFF2196F3))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Precisa de ajuda?", color = Color.Gray, fontSize = 12.sp)
        TextButton(onClick = onNavigateToSuporte) {
            Text("📞 Contatar Suporte", color = Color(0xFF2196F3), fontSize = 13.sp)
        }
    }
}

@Composable
fun RequisitosSenha(senha: String) {
    val temOitoCaracteres = senha.length >= 8 // verificar se tem 8 caracteres
    val temMaiusculaENumero = senha.any { it.isUpperCase() }
            && senha.any { it.isDigit() }  // verfifica se tem letra maiuscula e numeros

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            "REQUISITOS DA SENHA:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(6.dp))
        ItemRequisito("Mínimo de 8 caracteres", temOitoCaracteres)
        ItemRequisito("Letras maiúsculas e números", temMaiusculaENumero)
    }
}

@Composable
fun ItemRequisito(texto: String, atendido: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        if (atendido) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(18.dp)
            )
        } else {
            Surface(
                shape = RoundedCornerShape(50),
                color = Color.Transparent,
                border = BorderStroke(1.5.dp, Color.Gray),
                modifier = Modifier.size(18.dp)
            ) {}
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            texto,
            fontSize = 13.sp,
            color = if (atendido) Color(0xFF4CAF50) else Color.Gray
        )
    }
}

@Composable
fun CampoTexto(
    label: String,
    placeholder: String,
    valor: String,
    teclado: KeyboardType = KeyboardType.Text,
    onValorChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            placeholder = { Text(placeholder, color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = teclado),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun CampoSenha(label: String, valor: String, onValorChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            placeholder = { Text("••••••••", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}