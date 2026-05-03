package com.projeto.unibook1.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.projeto.unibook1.ui.theme.AdminColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEmprestimosAtrasados(
    onBack: () -> Unit,
    onAlunoClick: () -> Unit
) {

    val alunos = listOf(
        "João Pedro",
        "Marcos Vinicius",
        "Fernanda Souza",
        "Lucas Alves"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("ATRASADOS", color = AdminColor.PrimaryPurple, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Menu, null, tint = AdminColor.PrimaryPurple)
                    }
                },
                actions = {
                    Icon(Icons.Default.Search, null, tint = AdminColor.PrimaryPurple, modifier = Modifier.padding(end = 16.dp))
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
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                color = AdminColor.SearchBarGray
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(Icons.Default.Search, null, tint = Color.LightGray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Buscar aluno...", color = Color.LightGray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "STATUS CRÍTICO",
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Atrasados",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(alunos) { nome ->
                    AtrasadoCard(nome = nome, onClick = onAlunoClick)
                }
            }
        }
    }
}

@Composable
fun AtrasadoCard(nome: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = AdminColor.CardWhite,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = Color(0xFFFFEBEE)
            ) {
                Icon(
                    Icons.Default.Person,
                    null,
                    tint = Color.Red,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(nome, fontWeight = FontWeight.Bold)

                Text("MAT: 202400000", color = AdminColor.TextGray)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Warning,
                        null,
                        tint = Color.Red,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("ATRASADO", color = Color.Red)
                }
            }
        }
    }
}