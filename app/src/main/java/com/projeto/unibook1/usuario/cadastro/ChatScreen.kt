package com.projeto.unibook1.usuario.cadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

import com.projeto.unibook1.R

val AzulPrimario = Color(0xFF2196F3)
val AzulClaro    = Color(0xFFE8F1FB)
val CinzaFundo   = Color(0xFFF0F4F8)


data class Mensagem(
    val texto: String,
    val isUsuario: Boolean
)

fun gerarResposta(input: String): String {
    val texto = input.lowercase()
    return when {
        "prazo" in texto || "devolução" in texto || "entrega" in texto ->
            "O prazo de devolução é de 7 dias para livros comuns e 3 dias para livros de alta demanda. Você pode renovar pelo app antes do vencimento!"

        "renovar" in texto || "empréstimo" in texto ->
            "Para renovar um empréstimo, selecione o livro e clique em 'Renovar'. Você pode renovar até 2 vezes seguidas."

        "reservar" in texto || "reserva" in texto ->
            "Para reservar um livro, encontre-o na busca e clique em 'Reservar'. Você será notificado quando ele estiver disponível."

        "senha" in texto || "acesso" in texto || "login" in texto ->
            "Se esqueceu sua senha, vá na tela de Login e clique em 'Esqueci minha senha'. Um e-mail de recuperação será enviado para você."

        "livros" in texto || "quantos" in texto ->
            "Você pode pegar até 3 livros emprestados ao mesmo tempo. Alunos de pós-graduação têm direito a 5 livros."

        "sugestão" in texto || "sugerir" in texto ->
            "Adoramos sugestões! Envie o título e autor do livro para suporte@biblioteca.unibook.ar e nossa equipe avaliará a aquisição."

        "atendente" in texto || "humano" in texto || "pessoa" in texto ->
            "Para falar com um atendente humano, entre em contato pelo e-mail suporte@biblioteca ou pelo WhatsApp (85) 93477-3000."

        else ->
            "Não entendi muito bem. Posso te ajudar com empréstimos, devoluções, reservas ou acesso à conta. Use os chips abaixo ou reformule sua pergunta!"
    }
}

val chipsGerais = listOf(
    "Prazo de entrega",
    "Renovar empréstimo",
    "Fazer reserva",
    "Esqueci minha senha",
    "Quantos livros posso pegar?",
    "Falar com atendente"
)

val chipsPorCategoria = mapOf(
    "Dúvidas sobre Empréstimos" to listOf(
        "Prazo de entrega",
        "Renovar empréstimo",
        "Quantos livros posso pegar?",
        "Falar com atendente"
    ),
    "Problemas de Acesso" to listOf(
        "Esqueci minha senha",
        "Conta bloqueada",
        "Falar com atendente"
    ),
    "Sugestão de Livros" to listOf(
        "Fazer uma sugestão",
        "Ver sugestões anteriores",
        "Falar com atendente"
    ),
    "Outros Assuntos" to listOf(
        "Prazo de entrega",
        "Fazer reserva",
        "Falar com atendente"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    categoria: String = "",          // vazio =Iniciar Conversa agora
    onVoltar: () -> Unit = {}
) {
    val chips = if (categoria.isNotEmpty()) {
        chipsPorCategoria[categoria] ?: chipsGerais
    } else {
        chipsGerais
    }

    val mensagemInicial = if (categoria.isNotEmpty()) {
        "Olá! Sou a MoemaBot, seu assistente da Unibook. Vejo que você tem dúvidas sobre \"$categoria\". Como posso te ajudar?"
    } else {
        "Olá! Sou a MoemaBot, seu assistente da Unibook. Como posso te ajudar hoje?"
    }

    val mensagens = remember {
        mutableStateListOf(Mensagem(mensagemInicial, isUsuario = false))
    }
    var inputTexto by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    fun enviarMensagem(texto: String) {
        if (texto.isBlank()) return
        mensagens.add(Mensagem(texto, isUsuario = true))
        val resposta = gerarResposta(texto)
        mensagens.add(Mensagem(resposta, isUsuario = false))
        inputTexto = ""
        coroutineScope.launch {
            listState.animateScrollToItem(mensagens.size - 1)
        }
    }

    Scaffold(
        containerColor = CinzaFundo,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Avatar do bot
                        Image(
                            painter = painterResource(id = R.drawable.moemachat),
                            contentDescription = "MoemaBot",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                "MoemaBot",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = AzulPrimario
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF4CAF50))
                                )
                                Spacer(Modifier.width(4.dp))
                                Text("Online", fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = AzulPrimario)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(bottom = 8.dp)
            ) {
                // Chips com scroll horizontal
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(chips) { chip ->
                        FilterChip(
                            selected = false,
                            onClick = { enviarMensagem(chip) },
                            label = { Text(chip, fontSize = 13.sp) },
                            shape = RoundedCornerShape(50),
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color.White,
                                labelColor = AzulPrimario
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = false,
                                borderColor = AzulPrimario
                            )
                        )
                    }
                }

                // Campo de texto
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputTexto,
                        onValueChange = { inputTexto = it },
                        placeholder = { Text("Digite sua mensagem...", color = Color.Gray) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(50),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = AzulPrimario,
                            unfocusedContainerColor = CinzaFundo,
                            focusedContainerColor = CinzaFundo
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = { enviarMensagem(inputTexto) }),
                        singleLine = true
                    )
                    Spacer(Modifier.width(8.dp))
                    IconButton(
                        onClick = { enviarMensagem(inputTexto) },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(AzulPrimario)
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Enviar", tint = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(mensagens) { mensagem ->
                BolhaMensagem(mensagem)
            }
        }
    }
}

@Composable
fun BolhaMensagem(mensagem: Mensagem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (mensagem.isUsuario) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!mensagem.isUsuario) {
            Image(
                painter = painterResource(id = R.drawable.moemachat),
                contentDescription = "MoemaBot",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(8.dp))
        }

        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (mensagem.isUsuario) 16.dp else 4.dp,
                        bottomEnd = if (mensagem.isUsuario) 4.dp else 16.dp
                    )
                )
                .background(if (mensagem.isUsuario) AzulPrimario else Color.White)
                .padding(12.dp)
        ) {
            Text(
                text = mensagem.texto,
                color = if (mensagem.isUsuario) Color.White else Color(0xFF1A1A2E),
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        categoria = "Dúvidas sobre Empréstimos",
        onVoltar = {}
    )
}