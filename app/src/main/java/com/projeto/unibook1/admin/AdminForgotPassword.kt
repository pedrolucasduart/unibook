package com.projeto.unibook1.admin

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarSenhaScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    val roxoPrincipal = Color(0xFFA056F3)
    val fundoClaro = Color(0xFFF4F0FA)
    val cinzaTexto = Color(0xFF666666)
    val fundoInput = Color(0xFFF5F5F5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "BIBLIOTECA",
                        color = roxoPrincipal,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = roxoPrincipal)
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "Info", tint = roxoPrincipal)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.Login, contentDescription = "Login") },
                    label = { Text("Login") },
                    selected = false,
                    onClick = onNavigateToLogin,
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = cinzaTexto,
                        unselectedTextColor = cinzaTexto
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.PersonAdd, contentDescription = "Register") },
                    label = { Text("Register") },
                    selected = false,
                    onClick = onNavigateToRegister,
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = cinzaTexto,
                        unselectedTextColor = cinzaTexto
                    )
                )
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fundoClaro)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(color = roxoPrincipal.copy(alpha = 0.6f), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LockReset,
                            contentDescription = "Recuperar",
                            tint = roxoPrincipal,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "RECUPERAR SENHA",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Informe seu e-mail para receber as instruções de recuperação.",
                        fontSize = 14.sp,
                        color = cinzaTexto,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))


                    Text(
                        text = "EMAIL",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = cinzaTexto,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("exemplo@email.com", color = Color.LightGray) },
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email", tint = cinzaTexto)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = fundoInput,
                            unfocusedContainerColor = fundoInput,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(32.dp))


                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = roxoPrincipal)
                    ) {
                        Text("ENVIAR", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecuperarSenhaScreenPreview() {

    RecuperarSenhaScreen(
        onNavigateToLogin = {},
        onNavigateToRegister = {}
    )
}