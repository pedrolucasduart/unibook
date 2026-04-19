package com.projeto.unibook1.usuario.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefinirNovaSenhaScreen(
    onVoltarLogin: () -> Unit,
    onSenhaAtualizada: () -> Unit
) {
    var novaSenha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var novaSenhaVisivel by remember { mutableStateOf(false) }
    var confirmarSenhaVisivel by remember { mutableStateOf(false) }
    var mensagemErro by remember { mutableStateOf("") }

    // Requisitos
    val temMinimoCaracteres = novaSenha.length >= 8
    val temMaiusculaENumero = novaSenha.any { it.isUpperCase() } && novaSenha.any { it.isDigit() }

    fun validar(): Boolean {
        return when {
            novaSenha.isBlank() || confirmarSenha.isBlank() -> {
                mensagemErro = "Preencha todos os campos"
                false
            }
            !temMinimoCaracteres -> {
                mensagemErro = "A senha deve ter pelo menos 8 caracteres"
                false
            }
            !temMaiusculaENumero -> {
                mensagemErro = "A senha deve ter letras maiúsculas e números"
                false
            }
            novaSenha != confirmarSenha -> {
                mensagemErro = "As senhas não coincidem"
                false
            }
            else -> {
                mensagemErro = ""
                true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Ícone
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

                Text(
                    "Definir Nova Senha",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Crie uma senha forte para proteger\nseu acesso ao sistema da biblioteca\nUnifor.",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Campo Nova Senha
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Nova Senha", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = novaSenha,
                        onValueChange = { novaSenha = it },
                        placeholder = { Text("Digite sua nova senha", color = Color.LightGray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        visualTransformation = if (novaSenhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { novaSenhaVisivel = !novaSenhaVisivel }) {
                                Icon(
                                    imageVector = if (novaSenhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Confirmar Senha
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Confirmar Nova Senha", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = confirmarSenha,
                        onValueChange = { confirmarSenha = it },
                        placeholder = { Text("Repita sua nova senha", color = Color.LightGray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        visualTransformation = if (confirmarSenhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { confirmarSenhaVisivel = !confirmarSenhaVisivel }) {
                                Icon(
                                    imageVector = if (confirmarSenhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                RequisitosSenha(senha = novaSenha)

                // Mensagem de erro
                if (mensagemErro.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        mensagemErro,
                        color = Color.Red,
                        fontSize = 13.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botão Atualizar Senha
                Button(
                    onClick = { if (validar()) onSenhaAtualizada() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                ) {
                    Text("Atualizar Senha", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Voltar para o Login
                TextButton(onClick = onVoltarLogin) {
                    Text("Voltar para o Login", color = Color(0xFF2196F3), fontSize = 14.sp)
                }
            }
        }
    }
}

// Componente de requisito com check dinâmico
@Composable
fun RequisiteItem(texto: String, atendido: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = if (atendido) "✅" else "⭕",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = texto,
            fontSize = 13.sp,
            color = if (atendido) Color(0xFF2E7D32) else Color.Gray
        )
    }
}