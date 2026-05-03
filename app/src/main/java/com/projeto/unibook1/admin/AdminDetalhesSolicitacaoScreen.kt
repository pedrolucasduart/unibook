package com.projeto.unibook1.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDetalhesSolicitacaoScreen(
    onCloseClick: () -> Unit,
    // 👇 ADICIONAMOS OS PARÂMETROS DA NAVBAR AQUI 👇
    onNavigateToHome: () -> Unit,
    onNavigateToEmprestimos: () -> Unit,
    onNavigateToLivros: () -> Unit
) {
    val corPrimaria = Color(0xFF8A2BE2)
    val corFundo = Color(0xFFF4F5FA)
    val corVerde = Color(0xFF10B981)
    val corVermelha = Color(0xFFF43F5E)
    val corAmarela = Color(0xFFFEF3C7)
    val corTextoAmarelo = Color(0xFFD97706)

    Scaffold(
        containerColor = corFundo,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalhes da solicitação",
                        color = corPrimaria,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    IconButton(onClick = onCloseClick) {
                        Icon(Icons.Default.Close, contentDescription = "Fechar", tint = Color.Gray)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = corFundo)
            )
        },
        bottomBar = {
            AdminBottomNavBar(
                currentRoute = "admin_emprestimos", // Mantém a aba de empréstimos acesa
                onNavigateToHome = onNavigateToHome,
                onNavigateToEmprestimos = onNavigateToEmprestimos,
                onNavigateToLivros = onNavigateToLivros
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 1. CARD DO ALUNO
            CardPadrao {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFFFFEDD5), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("NOME DO ALUNO", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                        Text("Ana Clara Silva", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Row(modifier = Modifier.padding(top = 4.dp)) {
                            Column {
                                Text("MATRÍCULA", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                                Text("20240129", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text("CURSO", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                                Text("Engenharia de\nProdução", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }


            CardPadrao {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(corPrimaria.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.MenuBook, contentDescription = null, tint = corPrimaria, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Dados do Livro", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("TÍTULO", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("O Algoritmo da Vitória", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("CÓDIGO", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Surface(color = corPrimaria.copy(alpha = 0.1f), shape = RoundedCornerShape(4.dp)) {
                        Text("LV-9823", color = corPrimaria, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                }
            }


            CardPadrao {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.DateRange, contentDescription = null, tint = corPrimaria, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Prazos e Datas", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Text("DATA DO EMPRÉSTIMO", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = "15/05/2026", onValueChange = {}, readOnly = true,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFF9FAFB), unfocusedBorderColor = Color.Transparent)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("PRAZO DE ENTREGA", fontSize = 10.sp, color = corPrimaria, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = "22/05/2026", onValueChange = {}, readOnly = true,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color(0xFFF9FAFB), unfocusedBorderColor = Color.LightGray)
                    )
                }
            }


            CardPadrao(padding = 12.dp) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("STATUS", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Surface(color = corAmarela, shape = RoundedCornerShape(8.dp)) {
                        Text("PENDENTE", color = corTextoAmarelo, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    }
                }
            }

            CardPadrao(padding = 12.dp) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("TOTAL EMPRÉSTIMOS", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("12", color = corPrimaria, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                }
            }

            CardPadrao(padding = 12.dp) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("ATRASOS PRÉVIOS", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                    Text("0", color = corVerde, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                }
            }


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = {  },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = corVerde),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Aceitar")
                }

                Button(
                    onClick = { /* Lógica Recusar */ },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = corVermelha),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Cancel, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Recusar")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
fun CardPadrao(padding: androidx.compose.ui.unit.Dp = 16.dp, content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Box(modifier = Modifier.padding(padding)) {
            content()
        }
    }
}

@Preview
@Composable
fun PreviewDetalhes() {
    AdminDetalhesSolicitacaoScreen(
        onCloseClick = {},
        onNavigateToHome = {},
        onNavigateToEmprestimos = {},
        onNavigateToLivros = {}
    )
}