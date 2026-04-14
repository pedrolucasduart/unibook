package com.projeto.unibook1.admin

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onChangeProfilePictureClick: () -> Unit
) {
    // Cores baseadas na imagem
    val backgroundColor = Color(0xFFF7F8FB)
    val primaryPurple = Color(0xFF8B5CF6)
    val lightPurpleBg = Color(0xFFF3EDFF)
    val textColorDark = Color(0xFF333333)
    val textColorGray = Color(0xFF888888)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Top Bar (Botão de voltar e Título)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = primaryPurple
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Perfil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColorDark
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 2. Foto de Perfil com Botão de Editar
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            // Imagem circular com borda roxa
            Image(
                // Substitua pelo seu R.drawable.sua_imagem_placeholder
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Foto do Administrador",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(3.dp, primaryPurple, CircleShape)
                    .background(Color.LightGray) // Placeholder visual
            )

            // Botão de Editar (Lápis) sobreposto
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-4).dp, y = (-4).dp)
                    .size(32.dp)
                    .background(primaryPurple, CircleShape)
                    .clip(CircleShape)
                    .clickable { onChangeProfilePictureClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Alterar foto",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Nome e Função
        Text(
            text = "Perfil",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = primaryPurple
        )
        Text(
            text = "Kelson",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = textColorDark
        )
        Text(
            text = "Bibliotecário",
            fontSize = 16.sp,
            color = textColorGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 4. Cards de Informação
        ProfileInfoCard(
            icon = Icons.Default.Badge,
            label = "Número da matrícula",
            value = "LIB-2024-0892",
            showArrow = false,
            iconBgColor = lightPurpleBg,
            iconTint = primaryPurple
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileInfoCard(
            icon = Icons.Default.Email,
            label = "Email",
            value = "kelson.admin@biblioteca.com",
            showArrow = true,
            iconBgColor = lightPurpleBg,
            iconTint = primaryPurple
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileInfoCard(
            icon = Icons.Default.Phone,
            label = "Telefone",
            value = "+55 (85) 99234-5678",
            showArrow = true,
            iconBgColor = lightPurpleBg,
            iconTint = primaryPurple
        )
    }
}

// Componente reutilizável para criar as barras horizontais padronizadas
@Composable
fun ProfileInfoCard(
    icon: ImageVector,
    label: String,
    value: String,
    showArrow: Boolean,
    iconBgColor: Color,
    iconTint: Color
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Container do Ícone
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(iconBgColor, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Textos (Label e Valor)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = value,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // Seta da direita (Chevron)
            if (showArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Mais opções",
                    tint = Color.LightGray
                )
            }
        }
    }
}
@Preview(showBackground = true, name = "Visualização do Perfil")

@Composable
fun ProfileScreenPreview() {

    ProfileScreen(
        onBackClick = {},
        onChangeProfilePictureClick = {}
    )
}