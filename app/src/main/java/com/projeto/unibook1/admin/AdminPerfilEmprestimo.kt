package com.projeto.unibook1.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projeto.unibook1.ui.theme.AdminColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPerfilEmprestimo(onBack: () -> Unit) {

    val scroll = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Detalhes do Empréstimo",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFF0F3F9),
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = AdminColor.PrimaryPurple,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFF0F3F9),
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = AdminColor.TextGray,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AdminColor.BackgroundGray
                )
            )
        },
        containerColor = AdminColor.BackgroundGray
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scroll)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // ================= ALUNO =================
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.size(80.dp),
                        color = Color.LightGray
                    ) {
                        Icon(
                            Icons.Default.Person,
                            null,
                            tint = Color.White,
                            modifier = Modifier.padding(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            "Ricardo Oliveira",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text("MATRÍCULA", fontSize = 10.sp, color = AdminColor.TextGray)
                        Text("202400123", fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(6.dp))

                        Text("CURSO", fontSize = 10.sp, color = AdminColor.TextGray)
                        Text("Direito", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ================= LIVRO =================
            SectionTitle("LIVRO", Icons.Default.Book)

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Introdução ao Direito Civil",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text("Código: LV-1290", color = AdminColor.TextGray)

                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("CONDIÇÃO NA ENTREGA", fontSize = 10.sp, color = AdminColor.TextGray)

                    Spacer(modifier = Modifier.height(6.dp))

                    Surface(
                        color = Color(0xFFF0F3F9),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Excelente",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ================= CRONOGRAMA =================
            SectionTitle("CRONOGRAMA", Icons.Default.CalendarToday)

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("DATA DO EMPRÉSTIMO", fontSize = 10.sp, color = AdminColor.TextGray)
                            Text("10/05/2026", fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("ENTREGA PREVISTA", fontSize = 10.sp, color = AdminColor.TextGray)
                            Text("24/05/2026", fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF8FBFF),
                        border = BorderStroke(1.dp, Color(0xFFE0E0E0))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Dias Restantes", color = AdminColor.TextGray)
                            Text("4 dias", color = AdminColor.StatusGreen, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ================= RENOVAÇÃO =================
            SectionTitle("CONTROLE DE RENOVAÇÃO", Icons.Default.Sync)

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 6.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    InfoBox("REALIZADAS", "1")
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoBox("LIMITE", "3")

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFE8F5E9),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("STATUS", fontSize = 10.sp, color = AdminColor.TextGray)
                            Text(
                                "REGULAR",
                                color = AdminColor.StatusGreen,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ================= BOTÕES =================

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFF7B2FF7), Color(0xFF9A4DFF))
                            ),
                            shape = RoundedCornerShape(50)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Autorenew, null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Renovar empréstimo", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(50),
                border = BorderStroke(2.dp, AdminColor.StatusGreen)
            ) {
                Icon(Icons.Default.CheckCircleOutline, null, tint = AdminColor.StatusGreen)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Marcar como devolvido",
                    color = AdminColor.StatusGreen,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontSize = 11.sp,
            color = AdminColor.TextGray,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
        Icon(icon, null, tint = AdminColor.PrimaryPurple, modifier = Modifier.size(18.dp))
    }
}

@Composable
fun InfoBox(label: String, value: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF0F3F9),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, fontSize = 10.sp, color = AdminColor.TextGray)
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminPerfilEmprestimoPreview() {
    AdminPerfilEmprestimo(onBack = {})
}