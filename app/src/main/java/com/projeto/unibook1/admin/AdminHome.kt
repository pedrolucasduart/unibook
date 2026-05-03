package com.projeto.unibook1.admin // 👈 Verifique se o pacote bate com a sua pasta!

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Cores baseadas no seu design
val FundoApp = Color(0xFFF4F6F9)
val RoxoPrincipal = Color(0xFF7B2CBF)
val RoxoClaro = Color(0xFFE9D8FD)
val TextoCinza = Color(0xFF6B7280)

@Composable
fun AdminMainScreen(
    modifier: Modifier = Modifier,
    onOpenScannerClick: () -> Unit = {},
    onStudentClick: (String) -> Unit = {},
    onProfileClick: () -> Unit = {},
    onEmprestimosClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = FundoApp
    ) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // 1. Cabeçalho (Perfil e Nome)
            TopBarAdmin(
                nomeAdmin = "KELSON",
                onProfileClick = onProfileClick
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 2. Seção do Scanner
            Text(
                text = "SCANNER",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            ScannerCard(onOpenScannerClick = onOpenScannerClick)

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Seção de Estudantes
            Text(
                text = "ESTUDANTES NA BIBLIOTECA",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))

            StudentItemCard(nome = "Zíltom mac", matricula = "2510359", onClick = { onStudentClick("Zíltom") })
            Spacer(modifier = Modifier.height(12.dp))
            StudentItemCard(nome = "Renan Rabelo", matricula = "2510364", onClick = { onStudentClick("Renan") })
            Spacer(modifier = Modifier.height(12.dp))
            StudentItemCard(nome = "José Valdenor", matricula = "2510491", onClick = { onStudentClick("José") })
            Spacer(modifier = Modifier.height(12.dp))
            StudentItemCard(nome = "Pedro Mendonça", matricula = "2310359", onClick = { onStudentClick("Mariana") })

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}





@Composable
fun TopBarAdmin(
    nomeAdmin: String,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botão de Perfil
        IconButton(
            onClick = onProfileClick,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                tint = Color.DarkGray,
                modifier = Modifier.size(24.dp)
            )
        }

        // Nome do Admin
        Text(
            text = nomeAdmin.uppercase(),
            color = RoxoPrincipal,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            letterSpacing = 2.sp
        )
    }
}

@Composable
fun ScannerCard(onOpenScannerClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(RoxoClaro),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🔳", fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Scanner QR",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Clique na câmera para scannear",
                color = TextoCinza,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onOpenScannerClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RoxoPrincipal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Open Scanner", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun StudentItemCard(nome: String, matricula: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(RoxoClaro),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🎓", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nome,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = matricula,
                    fontSize = 12.sp,
                    color = TextoCinza
                )
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(FundoApp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Ver detalhes",
                    tint = TextoCinza
                )
            }
        }
    }
}

// 2. Parâmetro adicionado nesta declaração
@Composable
fun AdminBottomNavBar(
    onEmprestimosClick: () -> Unit = {},
    onNavigateToHome: () -> Unit,
    currentRoute: String,
    onNavigateToEmprestimos: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(32.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item Início
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(FundoApp, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(text = "🏠", fontSize = 20.sp)
                Text(text = "INÍCIO", color = RoxoPrincipal, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }

            // Item Empréstimos (3. Modificador clickable adicionado aqui)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onEmprestimosClick() }
            ) {
                Text(text = "📖", fontSize = 20.sp)
                Text(text = "EMPRÉSTIMOS", color = TextoCinza, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }

            // Item Livros
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "👥", fontSize = 20.sp)
                Text(text = "LIVROS", color = TextoCinza, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminMainScreenPreview() {
    MaterialTheme {
        AdminMainScreen()
    }
}