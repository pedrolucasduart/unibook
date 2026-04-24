package com.projeto.unibook1.usuario.cadastro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.HelpOutline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuporteAlunoScreen(
    onVoltar: () -> Unit,
    onIniciarConversa: (categoria: String) -> Unit,
    onAbrirFaq: () -> Unit
) {
    val categorias = listOf(
        Triple("📚", "Dúvidas sobre\nEmpréstimos", "emprestimos"),
        Triple("🔒", "Problemas de\nAcesso", "acesso"),
        Triple("📖", "Sugestão de\nLivros", "sugestao"),
        Triple("💬", "Outros Assuntos", "outros")
    )

    var categoriaSelecionada by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Suporte ao Aluno",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    // espaço vazio do mesmo tamanho do ícone para equilibrar
                    IconButton(onClick = {}) {}
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {

            // Ícone de interrogação
            Surface(
                shape = RoundedCornerShape(50),
                color = Color(0xFFE3F0FF),
                modifier = Modifier.size(56.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.HelpOutline,
                        contentDescription = null,
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Como podemos\najudar você hoje?",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Selecione uma categoria abaixo ou entre em\ncontato diretamente com nossa equipe.",
                fontSize = 13.sp,
                color = Color.Gray,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Seção de categorias
            Text(
                "CATEGORIAS DE SUPORTE",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Grid 2x2 de categorias
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CategoriaCard(
                    emoji = categorias[0].first,
                    titulo = categorias[0].second,
                    selecionada = categoriaSelecionada == categorias[0].third,
                    modifier = Modifier.weight(1f),
                    onClick = { categoriaSelecionada = categorias[0].third }
                )
                CategoriaCard(
                    emoji = categorias[1].first,
                    titulo = categorias[1].second,
                    selecionada = categoriaSelecionada == categorias[1].third,
                    modifier = Modifier.weight(1f),
                    onClick = { categoriaSelecionada = categorias[1].third }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CategoriaCard(
                    emoji = categorias[2].first,
                    titulo = categorias[2].second,
                    selecionada = categoriaSelecionada == categorias[2].third,
                    modifier = Modifier.weight(1f),
                    onClick = { categoriaSelecionada = categorias[2].third }
                )
                CategoriaCard(
                    emoji = categorias[3].first,
                    titulo = categorias[3].second,
                    selecionada = categoriaSelecionada == categorias[3].third,
                    modifier = Modifier.weight(1f),
                    onClick = { categoriaSelecionada = categorias[3].third }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Botão Iniciar Conversa
            Button(
                onClick = {
                    onIniciarConversa(categoriaSelecionada ?: "geral")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Icon(
                    Icons.Filled.Chat,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Iniciar Conversa agora",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Seção canais de atendimento
            Text(
                "CANAIS DE ATENDIMENTO",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            CanalAtendimento(
                icon = Icons.Filled.Email,
                label = "E-MAIL",
                valor = "suporte.biblioteca@unifor.br"
            )

            Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)

            CanalAtendimento(
                icon = Icons.Filled.Phone,
                label = "TELEFONE / WHATSAPP",
                valor = "(85) 3477-3000"
            )

            Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Link FAQ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAbrirFaq() }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Ver Perguntas Frequentes (FAQ)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF2196F3)
                    )
                }
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CategoriaCard(
    emoji: String,
    titulo: String,
    selecionada: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val borderColor = if (selecionada) Color(0xFF2196F3) else Color(0xFFE0E0E0)
    val bgColor = if (selecionada) Color(0xFFE3F0FF) else Color.White

    Surface(
        modifier = modifier
            .height(90.dp)
            .clickable { onClick() }
            .border(
                BorderStroke(if (selecionada) 2.dp else 1.dp, borderColor),
                RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        color = bgColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(emoji, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                titulo,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp,
                color = if (selecionada) Color(0xFF1565C0) else Color(0xFF333333)
            )
        }
    }
}

@Composable
fun CanalAtendimento(
    icon: ImageVector,
    label: String,
    valor: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF2196F3),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(label, fontSize = 10.sp, color = Color.Gray, letterSpacing = 0.5.sp)
            Text(valor, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuporteAlunoScreenPreview() {
    SuporteAlunoScreen(
        onVoltar = {},
        onIniciarConversa = {},
        onAbrirFaq = {}
    )
}