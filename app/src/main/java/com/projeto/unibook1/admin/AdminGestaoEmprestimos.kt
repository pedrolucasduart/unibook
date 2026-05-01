package com.projeto.unibook1.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.AdminColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminGestaoEmprestimos(onNavigateToSolicitacoes: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("BIBLIOTECA", color = AdminColor.PrimaryPurple, fontWeight = FontWeight.Bold) },
                navigationIcon = { Icon(Icons.Default.Menu, null, tint = AdminColor.TextGray) },
                actions = { Icon(Icons.Default.Search, null, tint = AdminColor.TextGray) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = AdminColor.BackgroundGray)
            )
        },
        bottomBar = { BottomNavigationBar() },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 20.dp)) {
            SearchBar()
            Spacer(modifier = Modifier.height(24.dp))
            Text("GESTÃO DE EMPRÉSTIMOS", color = AdminColor.PrimaryPurple, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.width(60.dp).padding(top = 4.dp), thickness = 3.dp, color = AdminColor.PrimaryPurple)

            Spacer(modifier = Modifier.height(20.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuCard("Solicitações de empréstimo", "VER PENDENTES", Icons.Default.Email, onClick = onNavigateToSolicitacoes)
                MenuCard("Empréstimos ativos regulares", "DENTRO DO PRAZO", Icons.AutoMirrored.Filled.LibraryBooks)
                MenuCard("Empréstimos ativos irregulares", "ATRASADOS", Icons.Default.WarningAmber, statusColor = AdminColor.StatusRed)
                MenuCard("Alunos bloqueados", "RESTRIÇÕES DE ACESSO", Icons.Default.Block)
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = AdminColor.CardWhite) {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, null) }, label = { Text("Início") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Book, null) }, label = { Text("Empréstimos") }, selected = true, onClick = {}, colors = NavigationBarItemDefaults.colors(selectedIconColor = AdminColor.PrimaryPurple))
        NavigationBarItem(icon = { Icon(Icons.Default.People, null) }, label = { Text("Alunos") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Person, null) }, label = { Text("Perfil") }, selected = false, onClick = {})
    }
}

@Composable
fun MenuCard(title: String, subtitle: String, icon: ImageVector, statusColor: Color = AdminColor.TextGray, onClick: (() -> Unit)? = null) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(90.dp).clickable(enabled = onClick != null) { onClick?.invoke() },
        shape = RoundedCornerShape(16.dp), color = AdminColor.BackgroundGray, shadowElevation = 4.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Surface(modifier = Modifier.size(50.dp), shape = RoundedCornerShape(25.dp), color = AdminColor.BackgroundGray, shadowElevation = 2.dp) {
                Icon(icon, null, modifier = Modifier.padding(12.dp), tint = if (statusColor == AdminColor.StatusRed) AdminColor.StatusRed else AdminColor.PrimaryPurple)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, color = Color.DarkGray, fontSize = 16.sp)
                Text(subtitle, color = statusColor, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun SearchBar() {
    Surface(modifier = Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp), color = AdminColor.SearchBarGray, shadowElevation = 2.dp) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 16.dp)) {
            Icon(Icons.Default.Search, null, tint = AdminColor.PrimaryPurple)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Buscar aluno...", color = Color.LightGray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminGestaoEmprestimosPreview() {
    AdminGestaoEmprestimos(onNavigateToSolicitacoes = {})
}