package com.projeto.unibook1.admin

import androidx.compose.foundation.clickable
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

val CorPrimaria = Color(0xFF6A1B9A)
val CorDeFundo = Color(0xFFF8F9FF)

data class SolicitacaoEmprestimo(
    val nome: String,
    val matricula: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEmprestimos(
    BackClick: () -> Unit,
    onStudentClick: () -> Unit
) {
    val listaDeSolicitacoes = listOf(
        SolicitacaoEmprestimo("Ana Clara Silva", "20240129"),
        SolicitacaoEmprestimo("Lucas Ferreira", "20240842"),
        SolicitacaoEmprestimo("Mariana Costa", "20241105")
    )

    Scaffold(
        containerColor = CorDeFundo,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = CorPrimaria)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "BIBLIOTECA CENTRAL",
                            color = CorPrimaria,
                            fontWeight = FontWeight.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = BackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomBarSimples()
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar aluno...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "SOLICITAÇÕES",
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(listaDeSolicitacoes) { item ->
                    CardEstudante(item, onStudentClick)
                }
            }
        }
    }
}

@Composable
fun CardEstudante(
    solicitacao: SolicitacaoEmprestimo,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = Color.LightGray,
                modifier = Modifier.size(45.dp)
            ) {
                Icon(Icons.Default.Person, null, tint = Color.White)
            }

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(solicitacao.nome, fontWeight = FontWeight.Bold)
                Text(solicitacao.matricula, fontSize = 12.sp)
            }

            Icon(Icons.Default.KeyboardArrowRight, null)
        }
    }
}

@Composable
fun BottomBarSimples() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        color = Color.White,
        shadowElevation = 6.dp
    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Home, null)
            Icon(Icons.Default.Refresh, null, tint = CorPrimaria)
            Icon(Icons.Default.Person, null)
        }
    }
}

@Preview
@Composable
fun Preview() {
    AdminEmprestimos({}, {})
}
