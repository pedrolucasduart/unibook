package com.projeto.unibook1.usuario.cadastro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.Unibook1Theme

// Cores do Unibook
val UnibookNavy = Color(0xFF2196F3)
val UnibookNavyLight = Color(0xFFEEF2F7)
val UnibookGray = Color(0xFF9E9E9E)
val UnibookTextDark = Color(0xFF1C1C1E)

@Composable
fun SelecaoScreen(
    onAlunoClick: () -> Unit,
    onAdminClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {

                // Ícone de formatura com fundo arredondado
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .background(UnibookNavyLight, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("🎓", fontSize = 32.sp)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Título
                Text(
                    text = "Unibook",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = UnibookNavy,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Subtítulo
                Text(
                    text = "Como deseja entrar?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = UnibookGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botão Aluno (preenchido)
                Button(
                    onClick = onAlunoClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UnibookNavy,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Text(
                        text = "Aluno",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Botão Administrador (outline)
                OutlinedButton(
                    onClick = onAdminClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.5.dp, UnibookNavy),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = UnibookNavy,
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        text = "Administrador",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                }
            }
        }
    }
}


@Preview(
    name = "Tela de Seleção",
    showBackground = true,
    showSystemUi = false,
    device = "spec:width=390dp,height=844dp,dpi=420"
)
@Composable
fun SelecaoScreenPreview() {
    Unibook1Theme {
        SelecaoScreen(
            onAlunoClick = {},
            onAdminClick = {}
        )
    }
}