package com.projeto.unibook1.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditarLivroScreen(
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToEmprestimos: () -> Unit,
    onNavigateToLivros: () -> Unit
) {
    // Cores do layout
    val roxoPrincipal = Color(0xFF7B2CBF)
    val fundoClaro = Color(0xFFF4F6F9)
    val corInput = Color(0xFFEef1f5)
    val cinzaTexto = Color(0xFF6B7280)
    val vermelhoExcluir = Color(0xFFDC2626)


    var titulo by remember { mutableStateOf("Duna") }
    var autor by remember { mutableStateOf("Frank Herbert") }
    var sinopse by remember { mutableStateOf("No futuro distante, o jovem Paul Atreides e sua família aceitam o controle do planeta deserto Arrakis, a única fonte de uma substância valiosa...") }
    var codigo by remember { mutableStateOf("978-8576573\n005") }
    var acervo by remember { mutableStateOf("042") }
    var categoria by remember { mutableStateOf("Ficção Científica") }
    var publicacao by remember { mutableStateOf("1965") }
    var editora by remember { mutableStateOf("Aleph") }
    var exemplares by remember { mutableIntStateOf(5) }

    Scaffold(
        containerColor = fundoClaro,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Editar Livro",
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Fechar", tint = roxoPrincipal)
                    }
                },
                actions = {
                    // Espaço vazio para centralizar o título perfeitamente
                    Spacer(modifier = Modifier.width(48.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fundoClaro)
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.DarkGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                modifier = Modifier.clickable { },
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Image, contentDescription = null, tint = roxoPrincipal, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Alterar imagem", color = roxoPrincipal, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))


            CampoDeTextoEditavel("TÍTULO", titulo, corInput) { titulo = it }
            CampoDeTextoEditavel("AUTOR", autor, corInput) { autor = it }
            CampoDeTextoEditavel("SINOPSE", sinopse, corInput, isSingleLine = false, minHeight = 100.dp) { sinopse = it }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CampoDeTextoEditavel("CÓDIGO (ISBN)", codigo, corInput, modifier = Modifier.weight(1f)) { codigo = it }
                CampoDeTextoEditavel("N° ACERVO", acervo, corInput, modifier = Modifier.weight(1f)) { acervo = it }
            }


            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                Text("CATEGORIA DO LIVRO", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = cinzaTexto, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = categoria,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = roxoPrincipal) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = corInput,
                        unfocusedContainerColor = corInput,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CampoDeTextoEditavel("PUBLICAÇÃO", publicacao, corInput, modifier = Modifier.weight(1f)) { publicacao = it }
                CampoDeTextoEditavel("EDITORA", editora, corInput, modifier = Modifier.weight(1f)) { editora = it }
            }


            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)) {
                Text("EXEMPLARES DISPONÍVEIS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = cinzaTexto, modifier = Modifier.padding(bottom = 8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(corInput, RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { if (exemplares > 0) exemplares-- }) {
                        Icon(Icons.Default.Remove, contentDescription = "Menos", tint = roxoPrincipal)
                    }
                    Text(text = exemplares.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    IconButton(onClick = { exemplares++ }) {
                        Icon(Icons.Default.Add, contentDescription = "Mais", tint = roxoPrincipal)
                    }
                }
            }


            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = roxoPrincipal),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Salvar alterações", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = corInput),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = vermelhoExcluir, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Excluir livro", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = vermelhoExcluir)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun CampoDeTextoEditavel(
    label: String,
    value: String,
    corFundo: Color,
    modifier: Modifier = Modifier,
    isSingleLine: Boolean = true,
    minHeight: androidx.compose.ui.unit.Dp = 56.dp,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Text(text = label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6B7280), modifier = Modifier.padding(bottom = 8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = minHeight),
            singleLine = isSingleLine,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = corFundo,
                unfocusedContainerColor = corFundo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditarLivro() {
    AdminEditarLivroScreen({}, {}, {}, {})
}