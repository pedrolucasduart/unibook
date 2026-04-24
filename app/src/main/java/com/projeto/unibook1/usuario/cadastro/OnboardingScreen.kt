package com.projeto.unibook1.usuario.cadastro

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.R

data class OnboardingPage(
    val imageRes: Int,
    val titulo: String,
    val descricao: String
)

@Composable
fun OnboardingScreen(
    onPular: () -> Unit,
    onConcluir: () -> Unit
) {
    val paginas = listOf(
        OnboardingPage(
            imageRes = R.drawable.moema,
            titulo = "Olá, Unifriend!",
            descricao = "Sua jornada acadêmica ficou mais inteligente. Explore o acervo, faça reservas e conecte-se com outros alunos em um só lugar."
        ),
        OnboardingPage(
            imageRes = R.drawable.mulherlendo, // troca quando tiver as outras imagens
            titulo = "Avalie e Compartilhe!",
            descricao = "Veja recomendações personalizadas para o seu curso e semestre. Avalie obras e ajude outros Unifriends a encontrar os melhores materiais."
        ),
        OnboardingPage(
            imageRes = R.drawable.mulhercomlivro, // troca quando tiver as outras imagens
            titulo = "Sua Jornada Unificada",
            descricao = "Gerencie seus empréstimos, faça reservas e acompanhe a disponibilidade do acervo físico de forma rápida e segura. A Unifor conectada com você."
        )
    )

    var paginaAtual by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Botão Pular
        TextButton(
            onClick = onPular,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text(
                text = "Pular",
                color = Color(0xFF1565C0),
                fontSize = 16.sp
            )
        }

        // Conteúdo central
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagem
            Image(
                painter = painterResource(id = paginas[paginaAtual].imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Título
            Text(
                text = paginas[paginaAtual].titulo,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF1A1A1A)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Descrição
            Text(
                text = paginas[paginaAtual].descricao,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF555555),
                lineHeight = 24.sp
            )
        }

        // Indicador + Botão
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 32.dp, vertical = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador de progresso (bolinhas)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                paginas.forEachIndexed { index, _ ->
                    val largura by animateDpAsState(
                        targetValue = if (index == paginaAtual) 24.dp else 8.dp,
                        label = "indicador"
                    )
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(largura)
                            .clip(CircleShape)
                            .background(
                                if (index == paginaAtual) Color(0xFF1565C0)
                                else Color(0xFFBBBBBB)
                            )
                    )
                }
            }

            // Botão avançar/concluir
            FloatingActionButton(
                onClick = {
                    if (paginaAtual < paginas.size - 1) {
                        paginaAtual++
                    } else {
                        onConcluir()
                    }
                },
                containerColor = Color(0xFF1565C0),
                shape = CircleShape
            ) {
                Text(text = "→", color = Color.White, fontSize = 26.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        onPular = {},
        onConcluir = {}
    )
}