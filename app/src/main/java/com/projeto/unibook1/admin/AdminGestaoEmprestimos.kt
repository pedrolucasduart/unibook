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
fun AdminGestaoEmprestimos(
    // Rotas das subtelas dos cards
    onNavigateToSolicitacoes: () -> Unit,
    onNavigateToRegulares: () -> Unit = {},
    onNavigateToIrregulares: () -> Unit = {},
    onNavigateToBloqueados: () -> Unit = {},
    // Rotas da barra de navegação principal
    onNavigateToHome: () -> Unit = {},
    onNavigateToEmprestimos: () -> Unit = {},
    onNavigateToLivros: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "BIBLIOTECA",
                        color = AdminColor.PrimaryPurple,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Icon(Icons.Default.Menu, contentDescription = null, tint = AdminColor.TextGray)
                },
                actions = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = AdminColor.TextGray)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AdminColor.BackgroundGray
                )
            )
        },

        bottomBar = {
            // Barra oficial do admin
            AdminBottomNavBar(
                currentRoute = "admin_emprestimos",
                onNavigateToHome = onNavigateToHome,
                onNavigateToEmprestimos = onNavigateToEmprestimos,
                onNavigateToLivros = onNavigateToLivros
            )
        },

        containerColor = AdminColor.BackgroundGray
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "GESTÃO DE EMPRÉSTIMOS",
                color = AdminColor.PrimaryPurple,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(60.dp)
                    .padding(top = 4.dp),
                thickness = 3.dp,
                color = AdminColor.PrimaryPurple
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                MenuCard(
                    title = "Solicitações de empréstimo",
                    subtitle = "VER PENDENTES",
                    icon = Icons.Default.Email,
                    onClick = onNavigateToSolicitacoes
                )

                MenuCard(
                    title = "Empréstimos ativos regulares",
                    subtitle = "DENTRO DO PRAZO",
                    icon = Icons.AutoMirrored.Filled.LibraryBooks,
                    onClick = onNavigateToRegulares
                )

                MenuCard(
                    title = "Empréstimos ativos irregulares",
                    subtitle = "ATRASADOS",
                    icon = Icons.Default.Warning,
                    statusColor = AdminColor.StatusRed,
                    onClick = onNavigateToIrregulares
                )

                MenuCard(
                    title = "Alunos bloqueados",
                    subtitle = "RESTRIÇÕES DE ACESSO",
                    icon = Icons.Default.Block,
                    onClick = onNavigateToBloqueados
                )
            }
        }
    }
}

@Composable
fun MenuCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    statusColor: Color = AdminColor.TextGray,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        shape = RoundedCornerShape(16.dp),
        color = AdminColor.CardWhite,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                icon,
                contentDescription = null,
                tint = if (statusColor == AdminColor.StatusRed)
                    AdminColor.StatusRed
                else
                    AdminColor.PrimaryPurple
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, color = statusColor, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminGestaoEmprestimosPreview() {
    AdminGestaoEmprestimos(onNavigateToSolicitacoes = {})
}