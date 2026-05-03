
package com.projeto.unibook1.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefinirSenhaScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
) {
    var novaSenha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    val roxoPrincipal = Color(0xFFA056F3)
    val fundoClaro = Color(0xFFF4F0FA)
    val cinzaTexto = Color(0xFF666666)
    val fundoInput = Color(0xFFF5F5F5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BIBLIOTECA", color = Color.DarkGray, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Menu, contentDescription = null) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.Login, contentDescription = null) },
                    label = { Text("Login") },
                    selected = false,
                    onClick = onNavigateToLogin,
                    colors = NavigationBarItemDefaults.colors(unselectedIconColor = cinzaTexto, unselectedTextColor = cinzaTexto)
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.PersonAdd, contentDescription = null) },
                    label = { Text("Register") },
                    selected = false,
                    onClick = onNavigateToRegister,
                    colors = NavigationBarItemDefaults.colors(unselectedIconColor = cinzaTexto, unselectedTextColor = cinzaTexto)
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().background(fundoClaro).padding(paddingValues), contentAlignment = Alignment.Center) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                    // Ícone de Escudo/Segurança
                    Box(modifier = Modifier.size(80.dp).background(roxoPrincipal.copy(0.1f), RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Security, contentDescription = null, tint = roxoPrincipal, modifier = Modifier.size(40.dp))
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Segurança Administrativa", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                    Text("Crie sua nova senha de administrador para acessar o painel.", textAlign = TextAlign.Center, color = cinzaTexto, fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(32.dp))

                    // Campo Nova Senha
                    Text("NOVA SENHA", fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
                    TextField(
                        value = novaSenha,
                        onValueChange = { novaSenha = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        trailingIcon = { Icon(Icons.Default.Visibility, contentDescription = null) },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(focusedContainerColor = fundoInput, unfocusedContainerColor = fundoInput, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo Confirmar Senha
                    Text("CONFIRMAR NOVA SENHA", fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.fillMaxWidth())
                    TextField(
                        value = confirmarSenha,
                        onValueChange = { confirmarSenha = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
                        trailingIcon = { Icon(Icons.Default.Visibility, contentDescription = null) },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(focusedContainerColor = fundoInput, unfocusedContainerColor = fundoInput, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {  },
                        modifier = Modifier.fillMaxWidth().height(55.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = roxoPrincipal),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("DEFINIR SENHA", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefinirSenhaPreview() {
    DefinirSenhaScreen({}, {})
}