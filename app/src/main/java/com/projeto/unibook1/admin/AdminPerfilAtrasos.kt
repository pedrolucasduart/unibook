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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.AdminColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDetalhesAtraso(onBack: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Detalhes do Atraso", color = AdminColor.PrimaryPurple, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Fechar", tint = AdminColor.TextGray)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AdminColor.BackgroundGray)
            )
        },
        bottomBar = { BottomBarAtraso() },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- CARD ALUNO ---
            CardInfoAtraso {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(65.dp), shape = CircleShape, color = Color.LightGray) {
                        Icon(Icons.Default.Person, null, modifier = Modifier.padding(10.dp), tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Ana Beatriz Cavalcanti", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("Matrícula: 2023001042", fontSize = 12.sp, color = AdminColor.TextGray)
                        Text("CIÊNCIA DA COMPUTAÇÃO", fontSize = 11.sp, color = AdminColor.PrimaryPurple, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- CARD LIVRO ---
            CardInfoAtraso {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Book, null, tint = AdminColor.PrimaryPurple, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("LIVRO EMPRESTADO", fontSize = 11.sp, color = AdminColor.TextGray, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Algoritmos e Estruturas de Dados", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("Código: AED-404", fontSize = 13.sp, color = AdminColor.TextGray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- SEÇÃO STATUS E MULTA ---
            CardInfoAtraso {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Warning, null, tint = AdminColor.StatusRed, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("STATUS: ATRASADO", color = AdminColor.StatusRed, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        BoxMulta(label = "TEMPO", value = "12 dias", modifier = Modifier.weight(1f))
                        BoxMulta(label = "MULTA ATUAL", value = "R$ 18,00", modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- LINHA DO TEMPO ---
            CardInfoAtraso {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CalendarToday, null, tint = AdminColor.TextGray, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Linha do Tempo", fontWeight = FontWeight.Bold, color = AdminColor.TextGray)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TimelineRow(label = "EMPRÉSTIMO", date = "05/05/2026", isActive = false)
                    TimelineRow(label = "DATA PREVISTA", date = "19/05/2026", isActive = true)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- RENOVAÇÕES E HISTÓRICO ---
            CardInfoAtraso {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Renovações Realizadas", fontSize = 13.sp, color = AdminColor.TextGray)
                        Text("0 / 2", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Histórico de Atrasos", fontSize = 13.sp, color = AdminColor.TextGray)
                        Surface(color = Color(0xFFFFE0B2), shape = RoundedCornerShape(8.dp)) {
                            Text("1 ATRASO ANTERIOR", color = Color(0xFFE65100), modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// --- COMPONENTES INTERNOS ---

@Composable
fun CardInfoAtraso(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = AdminColor.CardWhite,
        shadowElevation = 4.dp,
        content = content
    )
}

@Composable
fun BoxMulta(label: String, value: String, modifier: Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = AdminColor.SearchBarGray,
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, fontSize = 9.sp, color = AdminColor.TextGray, fontWeight = FontWeight.Bold)
            Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = AdminColor.StatusRed)
        }
    }
}

@Composable
fun TimelineRow(label: String, date: String, isActive: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(if (isActive) AdminColor.PrimaryPurple else Color.LightGray)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 10.sp, color = if (isActive) AdminColor.PrimaryPurple else AdminColor.TextGray, fontWeight = FontWeight.Bold)
            Text(date, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BottomBarAtraso() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = AdminColor.CardWhite,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Payment, null, tint = AdminColor.TextGray)
                Text("REGISTRAR", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AdminColor.TextGray)
            }

            // BOTÃO CENTRAL NOTIFICAR
            Button(
                onClick = { /* Notificar */ },
                modifier = Modifier.height(56.dp).width(140.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AdminColor.PrimaryPurple),
                shape = CircleShape
            ) {
                Icon(Icons.Default.NotificationsActive, null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("NOTIFICAR", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.AssignmentReturn, null, tint = AdminColor.TextGray)
                Text("DEVOLVIDO", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = AdminColor.TextGray)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminDetalhesAtrasoPreview() {
    AdminDetalhesAtraso(onBack = {})
}