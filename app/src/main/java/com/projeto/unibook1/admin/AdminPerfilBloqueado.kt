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
fun AdminPerfilBloqueado(onBack: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("BIBLIOTECA", color = AdminColor.PrimaryPurple, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Voltar", tint = AdminColor.PrimaryPurple)
                    }
                },
                actions = {
                    Text("Perfil do Aluno", modifier = Modifier.padding(end = 16.dp), color = AdminColor.TextGray, fontSize = 14.sp)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AdminColor.BackgroundGray)
            )
        },
        bottomBar = { BottomBarPerfilBloqueado() },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // --- FOTO E NOME ---
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = Color.LightGray,
                shadowElevation = 4.dp
            ) {
                Icon(Icons.Default.Person, null, modifier = Modifier.padding(20.dp), tint = Color.White)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Lucas Silva", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("ID: 20240988-1", fontSize = 14.sp, color = AdminColor.TextGray)

            Spacer(modifier = Modifier.height(24.dp))

            // --- DADOS ACADÊMICOS ---
            CardPerfil {
                Column(Modifier.padding(16.dp)) {
                    Text("DADOS ACADÊMICOS", fontSize = 11.sp, color = AdminColor.TextGray, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Nome Completo", fontSize = 10.sp, color = AdminColor.TextGray)
                    Text("Lucas Pereira da Silva", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Curso", fontSize = 10.sp, color = AdminColor.TextGray)
                    Text("Ciências da Computação", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- STATUS BLOQUEADO ---
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = AdminColor.CardWhite,
                shadowElevation = 2.dp,
                border = androidx.compose.foundation.BorderStroke(1.dp, AdminColor.StatusRed.copy(alpha = 0.5f))
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(8.dp).clip(CircleShape).background(AdminColor.StatusRed))
                        Spacer(Modifier.width(8.dp))
                        Text("STATUS: BLOQUEADO", color = AdminColor.StatusRed, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Motivo: Atraso recorrente na entrega de livros e multas pendentes superiores a R$ 50,00.",
                        fontSize = 13.sp, color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("Admin:", fontSize = 10.sp, color = AdminColor.TextGray)
                            Text("Ricardo Santos", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Data:", fontSize = 10.sp, color = AdminColor.TextGray)
                            Text("15/05/2026", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- HISTÓRICO E PENDÊNCIAS ---
            Text(
                "HISTÓRICO E PENDÊNCIAS",
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                fontSize = 11.sp, color = AdminColor.TextGray, fontWeight = FontWeight.Bold
            )

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BoxMetrica(label = "Livros Atrasados", value = "4", color = AdminColor.StatusRed, modifier = Modifier.weight(1f))
                BoxMetrica(label = "Multas Pendentes", value = "R$ 55,00", color = AdminColor.StatusRed, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(12.dp))
            BoxMetrica(label = "Total de Empréstimos", value = "28", color = Color.Black, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun CardPerfil(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = AdminColor.CardWhite,
        shadowElevation = 4.dp,
        content = content
    )
}

@Composable
fun BoxMetrica(label: String, value: String, color: Color, modifier: Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = AdminColor.SearchBarGray,
    ) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(label, fontSize = 10.sp, color = AdminColor.TextGray)
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
        }
    }
}

@Composable
fun BottomBarPerfilBloqueado() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = AdminColor.CardWhite,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionButton(icon = Icons.Default.LockOpen, label = "DESBLOQUEAR", color = AdminColor.PrimaryPurple)
            ActionButton(icon = Icons.Default.Edit, label = "EDITAR", color = AdminColor.TextGray)
            ActionButton(icon = Icons.Default.DeleteForever, label = "REMOVER", color = AdminColor.StatusRed)
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = color, modifier = Modifier.size(24.dp))
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminPerfilBloqueadoPreview() {
    AdminPerfilBloqueado(onBack = {})
}