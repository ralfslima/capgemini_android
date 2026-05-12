// ******* PRECISA ADICIONAR PERMISSÃO NO ANDROIDMANIFEST
// <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

// ******* ADICIONAR NO GRADLE (APP)
// implementation("com.google.android.gms:play-services-location:21.2.0")

package com.example.recursossmartphone.telas

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.google.android.gms.location.LocationServices

@Composable
fun Geolocalizacao() {

    val contexto = LocalContext.current

    // Mensagem que será exibida
    var mensagem by remember { mutableStateOf("Verificando GPS...") }

    // Permissão
    val permissao = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { permitido ->
        if (permitido) {
            // Se permissão concedida, verifica GPS e pega localização
            atualizarLocalizacao(contexto) { msg ->
                mensagem = msg
            }
        } else {
            mensagem = "Permissão de localização negada"
        }
    }

    // Solicita permissão automaticamente
    LaunchedEffect(Unit) {
        permissao.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    // Interface
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mensagem,
            fontSize = 22.sp
        )
    }
}


// Atualiza a mensagem conforme GPS/Localização
@SuppressLint("MissingPermission")
fun atualizarLocalizacao(
    contexto: Context,
    callback: (String) -> Unit
) {

    val locationManager = contexto.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        callback("GPS desativado")
        return
    }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(contexto)

    fusedLocationClient.lastLocation.addOnSuccessListener { location ->

        if (location != null) {
            val lat = location.latitude
            val lon = location.longitude
            callback("Latitude: $lat\nLongitude: $lon")
        } else {
            callback("GPS ativo, mas localização não disponível")
        }

    }.addOnFailureListener {
        callback("Erro ao obter localização")
    }

}