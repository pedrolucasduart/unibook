package com.projeto.unibook1.admin

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAdicionarLivroScreen(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToEmprestimos: () -> Unit,
    onNavigateToLivros: () -> Unit
) {
    val rPrincipal = Color(0xFF7B2CBF)
    val cinzaSuave = Color(0xFFF4F6F9)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Adicionar Livro", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("LIB", color = rPrincipal, fontWeight = FontWeight.Black)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = null) }
                }
            )
        },
        bottomBar = {
            AdminBottomNavBar(
                currentRoute = "admin_livros",
                onNavigateToHome = onNavigateToHome,
                onNavigateToEmprestimos = onNavigateToEmprestimos,
                onNavigateToLivros = onNavigateToLivros
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Placeholder Carregar Imagem
            Box(
                modifier = Modifier
                    .size(150.dp, 200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(cinzaSuave)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.AddAPhoto, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(40.dp))
                    Text("CARREGAR IMAGEM", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Formulário
            InputComLabel("INSERIR TÍTULO", "Ex: O Senhor dos Anéis")
            InputComLabel("INSERIR AUTOR", "Ex: J.R.R. Tolkien")
            InputComLabel("COLOCAR SINOPSE", "Breve resumo da obra...", isLong = true)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(Modifier.weight(1f)) { InputComLabel("CÓDIGO", "ISBN-13") }
                Box(Modifier.weight(1f)) { InputComLabel("NÚMERO ACERVO", "000") }
            }

            InputComLabel("CATEGORIA DO LIVRO", "Selecione uma categoria", isDropdown = true)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(Modifier.weight(1f)) { InputComLabel("ANO PUBLICAÇÃO", "AAAA") }
                Box(Modifier.weight(1f)) { InputComLabel("EDITORA", "Nome da editora") }
            }

            // Quantidade
            Column(Modifier.fillMaxWidth()) {
                Text("QUANTIDADE DE EXEMPLARES DISPONÍVEIS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                    IconButton(onClick = {}) { Icon(Icons.Default.RemoveCircleOutline, contentDescription = null, tint = rPrincipal) }
                    Text("1", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(horizontal = 16.dp))
                    IconButton(onClick = {}) { Icon(Icons.Default.AddCircleOutline, contentDescription = null, tint = rPrincipal) }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botões de Ação
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = rPrincipal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Salvar livro", fontWeight = FontWeight.Bold)
            }

            TextButton(
                onClick = onBack,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.Red, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("CANCELAR", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun InputComLabel(label: String, placeholder: String, isLong: Boolean = false, isDropdown: Boolean = false) {
    Column(Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(placeholder, fontSize = 14.sp) },
            modifier = Modifier.fillMaxWidth().height(if (isLong) 120.dp else 56.dp),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = if (isDropdown) { { Icon(Icons.Default.KeyboardArrowDown, null) } } else null,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF9FAFB),
                unfocusedBorderColor = Color.LightGray
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAddBook() {
    AdminAdicionarLivroScreen({}, {}, {}, {})
}