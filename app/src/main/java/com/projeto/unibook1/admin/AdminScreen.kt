
package com.projeto.unibook1.ui.admin // O pacote deve bater com a pasta!

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AdminScreen() {
    Text(text = "Esta é a tela do ADMIN")
}

@Preview(showBackground = true)
@Composable
fun AdminPreview() {
    AdminScreen()
}