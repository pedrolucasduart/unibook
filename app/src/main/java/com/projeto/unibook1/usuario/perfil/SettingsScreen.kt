package com.projeto.unibook1.usuario.perfil

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Cores ─────────────────────────────────────────────────────────
private val Blue        = Color(0xFF2563EB)
private val LightBlue   = Color(0xFFEFF6FF)
private val Background  = Color(0xFFF1F5F9)
private val CardBg      = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF1E293B)
private val TextSecond  = Color(0xFF64748B)
private val TextMuted   = Color(0xFF94A3B8)
private val GreenOk     = Color(0xFF16A34A)
private val RedLogout   = Color(0xFFDC2626)
private val GoldPremium = Color(0xFF92400E)
private val GoldBg      = Color(0xFFFEF3C7)

// ── Modelo de dados ───────────────────────────────────────────────

data class SettingsUser(
    val name: String,
    val course: String,
    val isPremium: Boolean
)

// ── Tela de Configurações ─────────────────────────────────────────

@Composable
fun SettingsScreen(
    user: SettingsUser = SettingsUser(
        name = "Mariana Silva",
        course = "Estudante de Design Editorial",
        isPremium = true
    ),
    appVersion: String = "v24.0-stable",
    onChangePassword: (current: String, newPass: String) -> Unit = { _, _ -> },
    onUpdateEmail: () -> Unit = {},
    onLGPD: () -> Unit = {},
    onExportData: () -> Unit = {},
    onAbout: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var showChangePassword by remember { mutableStateOf(false) }
    var showLogoutDialog   by remember { mutableStateOf(false) }
    var pushEnabled        by remember { mutableStateOf(true) }
    var emailEnabled       by remember { mutableStateOf(false) }
    var ecoMode            by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutDialog(
            onConfirm = { showLogoutDialog = false; onLogout() },
            onDismiss = { showLogoutDialog = false }
        )
    }

    Scaffold(containerColor = Background) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // ── Cabeçalho ────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Blue)
                }
                Text("Configurações", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Blue)
            }

            Spacer(Modifier.height(8.dp))

            // ── Card do Usuário ───────────────────────────────────
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE2D9F3)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF7C3AED), modifier = Modifier.size(36.dp))
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(user.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                        Text(user.course, fontSize = 13.sp, color = TextSecond)
                        Spacer(Modifier.height(4.dp))
                        if (user.isPremium) {
                            Surface(shape = RoundedCornerShape(4.dp), color = GoldBg) {
                                Text(
                                    "CONTA PREMIUM",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldPremium,
                                    letterSpacing = 1.sp
                                )
                            }
                        } else {
                            Surface(shape = RoundedCornerShape(4.dp), color = LightBlue) {
                                Text(
                                    "CONTA PADRÃO",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Blue,
                                    letterSpacing = 1.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // ── CONTA ─────────────────────────────────────────────
            SectionLabel("CONTA")
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                // Alterar senha — expansível
                Column {
                    SettingsMenuItem(
                        icon = Icons.Outlined.Lock,
                        title = "Alterar senha",
                        onClick = { showChangePassword = !showChangePassword }
                    )
                    AnimatedVisibility(
                        visible = showChangePassword,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        ChangePasswordSection(onChangePassword = onChangePassword)
                    }
                }

                MenuDivider()

                SettingsMenuItem(
                    icon = Icons.Outlined.Email,
                    title = "Atualizar e-mail",
                    onClick = onUpdateEmail
                )
            }

            Spacer(Modifier.height(20.dp))

            // ── PRIVACIDADE ───────────────────────────────────────
            SectionLabel("PRIVACIDADE")
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                SettingsMenuItem(icon = Icons.Outlined.Shield, title = "Conformidade LGPD", onClick = onLGPD)
                MenuDivider()
                SettingsMenuItem(icon = Icons.Outlined.Download, title = "Exportar meus dados", onClick = onExportData)
            }

            Spacer(Modifier.height(20.dp))

            // ── NOTIFICAÇÕES ──────────────────────────────────────
            SectionLabel("NOTIFICAÇÕES")
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                SettingsMenuToggle(
                    icon = Icons.Outlined.Notifications,
                    title = "Alertas Push",
                    checked = pushEnabled,
                    onCheckedChange = { pushEnabled = it }
                )
                MenuDivider()
                SettingsMenuToggle(
                    icon = Icons.Outlined.AlternateEmail,
                    title = "Alertas por E-mail",
                    checked = emailEnabled,
                    onCheckedChange = { emailEnabled = it }
                )
            }

            Spacer(Modifier.height(20.dp))

            // ── TEMA ──────────────────────────────────────────────
            SectionLabel("TEMA")
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                SettingsMenuToggle(
                    icon = Icons.Outlined.EnergySavingsLeaf,
                    title = "Modo Econômico",
                    subtitle = "Interface escura para poupar bateria",
                    checked = ecoMode,
                    onCheckedChange = { ecoMode = it }
                )
            }

            Spacer(Modifier.height(20.dp))

            // ── Sobre o Unifriends ────────────────────────────────
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clickable { onAbout() },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = LightBlue),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(Icons.Outlined.Info, contentDescription = null, tint = Blue, modifier = Modifier.size(22.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Blue, modifier = Modifier.size(20.dp))
                    }
                    Spacer(Modifier.height(8.dp))
                    Text("Sobre o Unifriends", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Blue)
                    Spacer(Modifier.height(4.dp))
                    Text("Conheça a missão da nossa biblioteca digital acadêmica.", fontSize = 13.sp, color = TextSecond)
                }
            }

            Spacer(Modifier.height(12.dp))

            // ── Informações da Versão ─────────────────────────────
            SectionCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text("Informações da Versão", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = TextPrimary)
                Spacer(Modifier.height(4.dp))
                Text(appVersion, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextMuted)
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(GreenOk)
                    )
                    Text(
                        "TUDO ATUALIZADO",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextMuted,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // ── Sair da Conta ─────────────────────────────────────
            TextButton(
                onClick = { showLogoutDialog = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Sair da conta", color = RedLogout, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            }

            Spacer(Modifier.height(12.dp))

            Text(
                "© 2024 UNIFRIENDS DIGITAL LIBRARY",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                color = TextMuted,
                letterSpacing = 1.sp
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

// ── Seção de Alteração de Senha ───────────────────────────────────

@Composable
private fun ChangePasswordSection(onChangePassword: (String, String) -> Unit) {
    var current         by remember { mutableStateOf("") }
    var newPass         by remember { mutableStateOf("") }
    var confirmPass     by remember { mutableStateOf("") }
    var showCurrent     by remember { mutableStateOf(false) }
    var showNew         by remember { mutableStateOf(false) }
    var showConfirm     by remember { mutableStateOf(false) }

    val hasMinLength    = newPass.length >= 8
    val hasSymbol       = newPass.any { !it.isLetterOrDigit() }
    val passwordsMatch  = newPass == confirmPass && confirmPass.isNotEmpty()
    val canSubmit       = hasMinLength && hasSymbol && passwordsMatch && current.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 4.dp)
    ) {
        HorizontalDivider(color = Color(0xFFE2E8F0))
        Spacer(Modifier.height(14.dp))

        // Senha atual
        PasswordField(
            value = current,
            onValueChange = { current = it },
            label = "Senha atual",
            showPassword = showCurrent,
            onToggleShow = { showCurrent = !showCurrent }
        )

        Spacer(Modifier.height(10.dp))

        // Nova senha
        PasswordField(
            value = newPass,
            onValueChange = { newPass = it },
            label = "Nova senha",
            showPassword = showNew,
            onToggleShow = { showNew = !showNew }
        )

        Spacer(Modifier.height(8.dp))

        // Indicadores de requisitos
        RequirementRow("Mínimo de 8 caracteres", hasMinLength)
        Spacer(Modifier.height(2.dp))
        RequirementRow("Contém símbolos (ex: @, #, !)", hasSymbol)

        Spacer(Modifier.height(10.dp))

        // Confirmar nova senha
        PasswordField(
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = "Confirmar nova senha",
            showPassword = showConfirm,
            onToggleShow = { showConfirm = !showConfirm },
            isError = confirmPass.isNotEmpty() && !passwordsMatch,
            supportingText = if (confirmPass.isNotEmpty() && !passwordsMatch) "As senhas não coincidem" else null
        )

        Spacer(Modifier.height(4.dp))

        // Link recuperar conta
        TextButton(
            onClick = {},
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp)
        ) {
            Text(
                "Esqueceu sua senha? Recuperar conta",
                color = Blue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(8.dp))

        // Botão Alterar Senha
        Button(
            onClick = { if (canSubmit) onChangePassword(current, newPass) },
            enabled = canSubmit,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                disabledContainerColor = Color(0xFFBFDBFE)
            )
        ) {
            Text("Alterar Senha", fontWeight = FontWeight.SemiBold)
        }

        Spacer(Modifier.height(4.dp))
    }
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    showPassword: Boolean,
    onToggleShow: () -> Unit,
    isError: Boolean = false,
    supportingText: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 13.sp) },
        singleLine = true,
        isError = isError,
        supportingText = if (supportingText != null) {{ Text(supportingText, color = Color(0xFFDC2626), fontSize = 11.sp) }} else null,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = onToggleShow) {
                Icon(
                    if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = if (showPassword) "Ocultar senha" else "Mostrar senha",
                    tint = TextSecond,
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Blue,
            unfocusedBorderColor = Color(0xFFE2E8F0)
        )
    )
}

@Composable
private fun RequirementRow(text: String, satisfied: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            if (satisfied) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
            contentDescription = null,
            tint = if (satisfied) GreenOk else TextMuted,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text,
            fontSize = 12.sp,
            color = if (satisfied) GreenOk else TextMuted
        )
    }
}

// ── Dialog de logout ──────────────────────────────────────────────

@Composable
private fun LogoutDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        containerColor = CardBg,
        title = { Text("Sair da conta", fontWeight = FontWeight.Bold, color = TextPrimary) },
        text = { Text("Tem certeza que deseja encerrar sua sessão?", color = TextSecond) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Sair", color = RedLogout, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = TextSecond)
            }
        }
    )
}

// ── Componentes auxiliares ────────────────────────────────────────

@Composable
private fun SectionLabel(text: String) {
    Text(
        text,
        modifier = Modifier.padding(start = 20.dp, bottom = 8.dp),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = TextSecond,
        letterSpacing = 1.sp
    )
}

@Composable
private fun SectionCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), content = content)
    }
}

@Composable
private fun SettingsMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Blue, modifier = Modifier.size(18.dp))
            }
            Column {
                Text(title, fontSize = 14.sp, color = TextPrimary)
                if (subtitle != null) Text(subtitle, fontSize = 12.sp, color = TextSecond)
            }
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextMuted, modifier = Modifier.size(18.dp))
    }
}

@Composable
private fun SettingsMenuToggle(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Blue, modifier = Modifier.size(18.dp))
            }
            Column {
                Text(title, fontSize = 14.sp, color = TextPrimary)
                if (subtitle != null) Text(subtitle, fontSize = 12.sp, color = TextSecond)
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Blue,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFCBD5E1)
            )
        )
    }
}

@Composable
private fun MenuDivider() {
    HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 0.5.dp)
}

// ── Preview ───────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen()
    }
}