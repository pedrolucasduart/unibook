package com.projeto.unibook1.admin

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Cores de apoio do seu sistema
private object AdminColorCustom {
    val PrimaryPurple = Color(0xFF7B2CBF)
    val BackgroundGray = Color(0xFFF4F6F9)
    val TextGray = Color(0xFF6B7280)
    val StatusGreen = Color(0xFF059669)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPerfilEmprestimo(onBack: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Detalhes do Empréstimo", color = Color(0xFF1A1C1E), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Surface(shape = CircleShape, color = Color(0xFFF0F3F9), modifier = Modifier.size(40.dp)) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = AdminColorCustom.PrimaryPurple, modifier = Modifier.padding(8.dp))
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Surface(shape = CircleShape, color = Color(0xFFF0F3F9), modifier = Modifier.size(40.dp)) {
                            Icon(Icons.Default.MoreVert, contentDescription = null, tint = AdminColorCustom.TextGray, modifier = Modifier.padding(8.dp))
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AdminColorCustom.BackgroundGray)
            )
        },
        containerColor = AdminColorCustom.BackgroundGray
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- SEÇÃO ALUNO ---
            SectionCard {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
                    Surface(modifier = Modifier.size(70.dp), shape = CircleShape, color = Color.LightGray) {
                        Icon(Icons.Default.Person, null, tint = Color.White, modifier = Modifier.padding(15.dp))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Ricardo Oliveira", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Text("MATRÍCULA", fontSize = 10.sp, color = AdminColorCustom.TextGray)
                        Text("202400123", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Text("CURSO", fontSize = 10.sp, color = AdminColorCustom.TextGray)
                        Text("Direito", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // --- SEÇÃO LIVRO ---
            SectionHeader(title = "LIVRO", icon = Icons.Default.Book)
            SectionCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Introdução ao Direito Civil", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Código: LV-1290", fontSize = 14.sp, color = AdminColorCustom.TextGray)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("CONDIÇÃO NA ENTREGA", fontSize = 10.sp, color = AdminColorCustom.TextGray)
                    Surface(color = Color(0xFFF0F3F9), shape = RoundedCornerShape(12.dp)) {
                        Text("Excelente", modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            // --- SEÇÃO CRONOGRAMA ---
            SectionHeader(title = "CRONOGRAMA", icon = Icons.Default.CalendarToday)
            SectionCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("DATA DO EMPRÉSTIMO", fontSize = 10.sp, color = AdminColorCustom.TextGray)
                            Text("10/05/2026", fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("ENTREGA PREVISTA", fontSize = 10.sp, color = AdminColorCustom.TextGray)
                            Text("24/05/2026", fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFF8FBFF),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
                    ) {
                        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Dias Restantes", color = AdminColorCustom.TextGray)
                            Text("4 dias", color = AdminColorCustom.StatusGreen, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // --- CONTROLE DE RENOVAÇÃO ---
            SectionHeader(title = "CONTROLE DE RENOVAÇÃO", icon = Icons.Default.Sync)
            SectionCard {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    RenovacaoItem("REALIZADAS", "1")
                    Spacer(modifier = Modifier.height(12.dp))
                    RenovacaoItem("LIMITE", "3")
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("STATUS", fontSize = 10.sp, color = AdminColorCustom.TextGray)
                    Text("REGULAR", color = AdminColorCustom.StatusGreen, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- BOTÕES FINAIS ---
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AdminColorCustom.PrimaryPurple),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Autorenew, null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Renovar empréstimo", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, Color(0xFF00A36C))
            ) {
                Icon(Icons.Default.CheckCircleOutline, null, tint = AdminColorCustom.StatusGreen, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Marcar como devolvido", color = AdminColorCustom.StatusGreen, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AdminColorCustom.TextGray, modifier = Modifier.weight(1f))
        Icon(icon, null, tint = AdminColorCustom.PrimaryPurple, modifier = Modifier.size(18.dp))
    }
}

@Composable
fun SectionCard(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        content = content
    )
}

@Composable
fun RenovacaoItem(label: String, value: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFF0F3F9),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
            Text(label, fontSize = 9.sp, color = AdminColorCustom.TextGray)
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminPerfilEmprestimoPreview() {
    AdminPerfilEmprestimo(onBack = {})
}