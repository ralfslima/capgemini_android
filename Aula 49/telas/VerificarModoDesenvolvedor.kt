package com.example.recursossmartphone.telas

import android.content.Context
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun VerificarModoDesenvolvedor() {

    // Contexto
    val contexto = LocalContext.current

    // Coluna
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        // Botão
        Button(
            onClick = {

                // Verificar modo desenvolvedor
                val ativo = verificarModoDesenvolvedor(contexto)

                // Exibir mensagem
                if (ativo) {
                    Toast.makeText(
                        contexto,
                        "Modo desenvolvedor ATIVO",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        contexto,
                        "Modo desenvolvedor DESATIVADO",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        ) {
            Text("Verificar modo desenvolvedor")
        }

    }
}





// Função para verificar o modo desenvolvedor
fun verificarModoDesenvolvedor(contexto: Context): Boolean {

    return Settings.Global.getInt(
        contexto.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
        0
    ) != 0

}