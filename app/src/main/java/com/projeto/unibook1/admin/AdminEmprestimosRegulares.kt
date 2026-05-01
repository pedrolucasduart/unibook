package com.projeto.unibook1.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.AdminColor

data class AlunoEmprestimo(val nome: String, val matricula: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEmprestimosRegulares(onBack: () -> Unit) {
    val listaAlunos = listOf(
        AlunoEmprestimo("Ricardo Oliveira", "MAT: 202400123"),
        AlunoEmprestimo("Ana Beatriz Silva", "MAT: 202400456"),
        AlunoEmprestimo("Carlos Eduardo Lima", "MAT: 202300889"),
        AlunoEmprestimo("Juliana Mendes", "MAT: 202400332")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("EMPRÉSTIMOS", color = AdminColor.PrimaryPurple, fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = AdminColor.TextGray) } },
                actions = { Icon(Icons.Default.Search, null, tint = AdminColor.TextGray, modifier = Modifier.padding(end = 16.dp)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AdminColor.BackgroundGray)
            )
        },
        bottomBar = { BottomNavigationBar() },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp)) {
            SearchBarRegulares()
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "STATUS ATIVO", color = AdminColor.PrimaryPurple, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Regulares", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Surface(shape = RoundedCornerShape(8.dp), color = AdminColor.CardWhite, shadowElevation = 2.dp) {
                    Text(text = "Total: 42", modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AdminColor.TextGray)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(listaAlunos) { aluno -> AlunoCard(aluno) }
            }
        }
    }
}

@Composable
fun AlunoCard(aluno: AlunoEmprestimo) {
    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), color = AdminColor.CardWhite, shadowElevation = 4.dp) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(50.dp), shape = CircleShape, color = AdminColor.BackgroundGray) {
                Icon(Icons.Default.Person, null, tint = AdminColor.PrimaryPurple, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = aluno.nome, fontWeight = FontWeight.Bold, color = AdminColor.PrimaryPurple, fontSize = 16.sp)
                Text(text = aluno.matricula, color = AdminColor.TextGray, fontSize = 13.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, null, tint = AdminColor.StatusGreen, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("STATUS: REGULAR", color = AdminColor.StatusGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Icon(Icons.Default.Search, null, tint = AdminColor.PrimaryPurple)
        }
    }
}

@Composable
fun SearchBarRegulares() {
    Surface(modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp), color = AdminColor.SearchBarGray, shadowElevation = 2.dp) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {
            Icon(Icons.Default.Search, null, tint = Color.LightGray)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Buscar aluno por nome...", color = Color.LightGray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminEmprestimosRegularesPreview() {
    AdminEmprestimosRegulares(onBack = {})
}