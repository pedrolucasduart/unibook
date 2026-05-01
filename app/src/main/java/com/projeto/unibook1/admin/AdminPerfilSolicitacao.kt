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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.AdminColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPerfilSolicitacao(onBack: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Detalhes da Solicitação",
                        color = AdminColor.PrimaryPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                actions = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Fechar", tint = AdminColor.TextGray)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AdminColor.BackgroundGray)
            )
        },
        bottomBar = { BottomNavigationBarSolicitacao() },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // --- CARD DO ALUNO ---
            CardPerfilAluno()

            Spacer(modifier = Modifier.height(24.dp))

            // --- SEÇÃO LIVRO ---
            CardInformacaoLivro()

            Spacer(modifier = Modifier.height(24.dp))

            // --- SEÇÃO DATAS ---
            CardPrazosEDatas()

            Spacer(modifier = Modifier.height(24.dp))

            // --- STATUS E RESUMO ---
            ResumoStatus()

            Spacer(modifier = Modifier.height(32.dp))

            // --- BOTÕES DE AÇÃO ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BotaoAcaoSolicitacao(
                    texto = "Aceitar",
                    cor = AdminColor.StatusGreen,
                    icon = Icons.Default.CheckCircle,
                    modifier = Modifier.weight(1f)
                )
                BotaoAcaoSolicitacao(
                    texto = "Recusar",
                    cor = AdminColor.StatusRed,
                    icon = Icons.Default.Cancel,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun CardPerfilAluno() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar Placeholder
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color(0xFFFFE0B2) // Tom pastel laranja da imagem
            ) {
                Icon(Icons.Default.Person, null, modifier = Modifier.padding(15.dp), tint = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text("NOME DO ALUNO", fontSize = 11.sp, color = AdminColor.TextGray, fontWeight = FontWeight.Bold)
                Text("Ana Clara Silva", fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold)

                Row(modifier = Modifier.padding(top = 4.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("MATRÍCULA", fontSize = 10.sp, color = AdminColor.TextGray)
                        Text("20240129", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(1.2f)) {
                        Text("CURSO", fontSize = 10.sp, color = AdminColor.TextGray)
                        Text("Engenharia de Produção", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CardInformacaoLivro() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFFF3E5F5).copy(alpha = 0.5f), // Roxo clarinho de fundo
        shadowElevation = 0.dp
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LibraryBooks, null, tint = AdminColor.PrimaryPurple)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Dados do Livro", fontWeight = FontWeight.Bold, color = AdminColor.TextGray)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("TÍTULO", fontSize = 11.sp, color = AdminColor.TextGray)
            Text("O Algoritmo da Vitória", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Row(modifier = Modifier.padding(top = 12.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("CÓDIGO", fontSize = 11.sp, color = AdminColor.TextGray)
                    Surface(color = AdminColor.PrimaryPurple, shape = RoundedCornerShape(8.dp)) {
                        Text("LV-9823", color = Color.White, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 12.sp)
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("DISPONÍVEIS", fontSize = 11.sp, color = AdminColor.TextGray)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(AdminColor.StatusGreen))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("3 exemplares", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CardPrazosEDatas() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.CalendarMonth, null, tint = AdminColor.PrimaryPurple)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Prazos e Datas", fontWeight = FontWeight.Bold, color = AdminColor.TextGray)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("DATA DO EMPRÉSTIMO", fontSize = 11.sp, color = AdminColor.TextGray)
        BoxDataInformacao("15/05/2026")

        Spacer(modifier = Modifier.height(12.dp))

        Text("PRAZO DE ENTREGA", fontSize = 11.sp, color = AdminColor.TextGray)
        BoxDataInformacao("22/05/2026")
    }
}

@Composable
fun BoxDataInformacao(data: String) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Text(data, modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ResumoStatus() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text("STATUS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AdminColor.TextGray)
        Surface(color = Color(0xFFFFECB3), shape = RoundedCornerShape(16.dp)) {
            Text("PENDENTE", color = Color(0xFFFFA000), modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("TOTAL EMPRÉSTIMOS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AdminColor.PrimaryPurple)
        Text("12", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = AdminColor.PrimaryPurple)

        Spacer(modifier = Modifier.height(8.dp))

        Text("ATRASOS PRÉVIOS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AdminColor.StatusRed)
        Text("0", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = AdminColor.StatusRed)
    }
}

@Composable
fun BotaoAcaoSolicitacao(texto: String, cor: Color, icon: ImageVector, modifier: Modifier) {
    Button(
        onClick = { /* Ação */ },
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = cor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(icon, null, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(texto, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BottomNavigationBarSolicitacao() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(icon = { Icon(Icons.Default.List, null) }, label = { Text("Catálogo") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.SyncAlt, null) }, label = { Text("Empréstimo") }, selected = true, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = AdminColor.PrimaryPurple))
        NavigationBarItem(icon = { Icon(Icons.Default.People, null) }, label = { Text("Alunos") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Person, null) }, label = { Text("Perfil") }, selected = false, onClick = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminPerfilSolicitacaoPreview() {
    AdminPerfilSolicitacao(onBack = {})
}