package com.example.recursossmartphone.telas

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Interface gráfica
@Composable
fun Bateria() {

    // Criar contexto (para obter os dados do smartphone naquele momento)
    val contexto = LocalContext.current

    // Obter o nível da bateria
    val nivelBateria = obterNivelBateria(contexto)

    // Exibir nível da bateria
    Text(
        text = "Nível de bateria: ${nivelBateria}%",
        fontSize = 30.sp,
        modifier = Modifier.padding(30.dp)
    )

}




// Função para extrair o percentual de bateria
fun obterNivelBateria(contexto: Context): Int {

    // Obter informações da bateria
    val statusBateria: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filtro -> contexto.registerReceiver(null, filtro) }

    // Obter o nível da bateria
    val nivel = statusBateria?.getIntExtra(
        BatteryManager.EXTRA_LEVEL, -1
    ) ?: -1

    // Retorno
    return nivel
}