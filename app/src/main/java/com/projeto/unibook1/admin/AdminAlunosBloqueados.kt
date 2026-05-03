package com.projeto.unibook1.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.AdminColor

data class AlunoBloqueado(val nome: String, val matricula: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAlunosBloqueados(
    onBack: () -> Unit,
    onAlunoClick: () -> Unit
) {

    val listaBloqueados = listOf(
        AlunoBloqueado("Ana Beatriz Cavalcanti", "MATRÍCULA: 2023001042"),
        AlunoBloqueado("Carlos Eduardo Mendes", "MATRÍCULA: 2022004591"),
        AlunoBloqueado("Mariana Silva Braga", "MATRÍCULA: 2023001180"),
        AlunoBloqueado("Ricardo Ferreira Lima", "MATRÍCULA: 2021008722"),
        AlunoBloqueado("Juliana Costa Pereira", "MATRÍCULA: 2023002015")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "ALUNOS BLOQUEADOS",
                        color = AdminColor.PrimaryPurple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null, tint = AdminColor.TextGray)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AdminColor.BackgroundGray
                )
            )
        },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                items(listaBloqueados) { aluno ->
                    CardAlunoBloqueado(
                        aluno = aluno,
                        onClick = onAlunoClick
                    )
                }
            }
        }
    }
}

@Composable
fun CardAlunoBloqueado(
    aluno: AlunoBloqueado,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = aluno.nome,
                    color = AdminColor.PrimaryPurple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = aluno.matricula,
                    color = AdminColor.TextGray,
                    fontSize = 13.sp
                )
            }

            Icon(Icons.Default.Search, null, tint = AdminColor.PrimaryPurple)
        }
    }
}