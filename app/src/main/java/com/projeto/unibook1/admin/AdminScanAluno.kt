package com.projeto.unibook1.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminConcluirScreen(
    onClose: () -> Unit,
    onConcluir: () -> Unit
) {
    val fundoClaro = Color(0xFFF4F6F9)
    val roxoPrincipal = Color(0xFF7B2CBF)
    val roxoClaro = Color(0xFFF3E5F5)
    val verdeBotao = Color(0xFF22C55E)

    var vaiLevarCesta by remember { mutableStateOf(true) }
    var observacoes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fundoClaro)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(32.dp))
            Text("Mariana Dias", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
            IconButton(
                onClick = onClose,
                modifier = Modifier.size(32.dp).background(Color.White, CircleShape)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Fechar", tint = Color.Red, modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(12.dp))


        Box(
            modifier = Modifier
                .background(roxoClaro, RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text("Perfil do Aluno", color = roxoPrincipal, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                ItemDado("NOME COMPLETO", "Mariana Dias de Souza")
                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF3F4F6))
                ItemDado("MATRÍCULA", "2310359")
                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFF3F4F6))
                ItemDado("CURSO", "Arquitetura e Urbanismo")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text("Selecionar armário", fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "Nenhum selecionado",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, null, tint = roxoPrincipal) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color(0xFFE5E7EB)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))


        Text("O aluno vai levar cesta?", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(if (vaiLevarCesta) fundoClaro else Color.Transparent, RoundedCornerShape(8.dp))
                    .clickable { vaiLevarCesta = true },
                contentAlignment = Alignment.Center
            ) {
                Text("Sim", color = if (vaiLevarCesta) roxoPrincipal else Color.Gray, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(if (!vaiLevarCesta) fundoClaro else Color.Transparent, RoundedCornerShape(8.dp))
                    .clickable { vaiLevarCesta = false },
                contentAlignment = Alignment.Center
            ) {
                Text("Não", color = if (!vaiLevarCesta) roxoPrincipal else Color.Gray, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Text("Observações", fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = observacoes,
            onValueChange = { observacoes = it },
            placeholder = { Text("Adicione observações importantes aqui...", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth().height(120.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color(0xFFE5E7EB)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = onConcluir,
            modifier = Modifier.fillMaxWidth().height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = verdeBotao),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Concluir", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun ItemDado(titulo: String, valor: String) {
    Column {
        Text(titulo, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(valor, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewConcluir() {
    AdminConcluirScreen({}, {})
}
