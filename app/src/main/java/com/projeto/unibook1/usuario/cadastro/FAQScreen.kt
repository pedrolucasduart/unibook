package com.projeto.unibook1.usuario.suporte

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AzulPrimario = Color(0xFF2196F3)
private val AzulClaro    = Color(0xFFE8F1FB)
private val CinzaFundo   = Color(0xFFF0F4F8)

data class FaqItem(
    val pergunta: String,
    val resposta: String
)

data class FaqCategoria(
    val titulo: String,
    val itens: List<FaqItem>
)

val faqDados = listOf(
    FaqCategoria(
        titulo = "Empréstimos",
        itens = listOf(
            FaqItem(
                "Qual o prazo de devolução?",
                "O prazo de devolução é de 7 dias para livros comuns e 3 dias para livros de alta demanda. Fique atento às notificações do app!"
            ),
            FaqItem(
                "Como renovar um empréstimo?",
                "Vá em 'Rentals' no menu inferior, selecione o livro desejado e clique em 'Renovar'. Você pode renovar até 2 vezes consecutivas."
            )
        )
    ),
    FaqCategoria(
        titulo = "Acesso",
        itens = listOf(
            FaqItem(
                "Esqueci minha senha",
                "Na tela de Login, clique em 'Esqueceu a senha?'. Insira seu e-mail institucional e enviaremos um link de recuperação."
            )
        )
    ),
    FaqCategoria(
        titulo = "Reservas",
        itens = listOf(
            FaqItem(
                "Como reservar um livro?",
                "Encontre o livro na busca e clique em 'Reservar'. Você será notificado assim que o livro estiver disponível para retirada."
            )
        )
    ),
    FaqCategoria(
        titulo = "Conta",
        itens = listOf(
            FaqItem(
                "Quantos livros posso pegar emprestado?",
                "Alunos de graduação podem pegar até 3 livros. Alunos de pós-graduação têm direito a 5 livros simultaneamente."
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQScreen(onVoltar: () -> Unit = {}) {
    var busca by remember { mutableStateOf("") }

    // Filtra categorias e perguntas com base na busca
    val dadosFiltrados = remember(busca) {
        if (busca.isBlank()) {
            faqDados
        } else {
            faqDados.mapNotNull { categoria ->
                val itensFiltrados = categoria.itens.filter {
                    it.pergunta.contains(busca, ignoreCase = true) ||
                            it.resposta.contains(busca, ignoreCase = true)
                }
                if (itensFiltrados.isNotEmpty()) categoria.copy(itens = itensFiltrados) else null
            }
        }
    }

    Scaffold(
        containerColor = CinzaFundo,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Perguntas Frequentes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF1A1A2E)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = AzulPrimario
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Barra de busca
            item {
                OutlinedTextField(
                    value = busca,
                    onValueChange = { busca = it },
                    placeholder = { Text("Buscar pergunta...", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = AzulPrimario,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    singleLine = true
                )
            }

            // Categorias e perguntas
            items(dadosFiltrados) { categoria ->
                FaqCategoriaSection(categoria)
            }

            // Mensagem quando não encontra resultado
            if (dadosFiltrados.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Nenhuma pergunta encontrada.",
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FaqCategoriaSection(categoria: FaqCategoria) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // Título da categoria
        Text(
            text = categoria.titulo,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = AzulPrimario,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        // Card com as perguntas
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                categoria.itens.forEachIndexed { index, item ->
                    FaqItemRow(item)
                    if (index < categoria.itens.size - 1) {
                        HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun FaqItemRow(item: FaqItem) {
    var expandido by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expandido = !expandido }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.pergunta,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color(0xFF1A1A2E),
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expandido) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Gray
            )
        }

        AnimatedVisibility(visible = expandido) {
            Text(
                text = item.resposta,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 21.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun FAQScreenPreview() {
    FAQScreen(onVoltar = {})
}