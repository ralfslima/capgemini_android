package com.example.recursossmartphone.telas

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import kotlin.math.sqrt

@Composable
fun DetectarMovimento() {

    // Contexto
    val contexto = LocalContext.current

    // Texto exibido
    var mensagem by remember {
        mutableStateOf("Celular parado")
    }

    // Sensor manager
    val sensorManager = remember {
        contexto.getSystemService(
            Context.SENSOR_SERVICE
        ) as SensorManager
    }

    // Acelerômetro
    val acelerometro = remember {
        sensorManager.getDefaultSensor(
            Sensor.TYPE_ACCELEROMETER
        )
    }

    // Listener do sensor
    DisposableEffect(Unit) {

        val listener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent?) {

                event ?: return

                // Valores dos eixos
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                // Intensidade
                val aceleracao = sqrt(
                    (x * x + y * y + z * z).toDouble()
                )

                // Verificar movimento
                mensagem =
                    if(aceleracao > 12) {
                        "Celular em movimento"
                    } else {
                        "Celular parado"
                    }

            }

            override fun onAccuracyChanged(
                sensor: Sensor?,
                accuracy: Int
            ) {
            }

        }

        // Registrar listener
        sensorManager.registerListener(
            listener,
            acelerometro,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        onDispose {

            // Remover listener
            sensorManager.unregisterListener(listener)

        }

    }

    // Interface
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = mensagem,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyLarge
        )

    }

}