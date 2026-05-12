// ******* PRECISA ADICIONAR PERMISSÃO NO ANDROIDMANIFEST
// <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

package com.example.recursossmartphone.telas

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
fun VerificarInternet() {

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

                // Verificar conexão
                val internet = verificarInternet(contexto)

                // Exibir mensagem
                if(internet) {
                    Toast.makeText(
                        contexto,
                        "Internet disponível",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        contexto,
                        "Sem internet",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        ) {
            Text("Verificar internet")
        }
    }

}





fun verificarInternet(contexto: Context): Boolean {

    val connectivityManager = contexto.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false

    val activeNetwork = connectivityManager.getNetworkCapabilities(network)
        ?: return false

    return when {

        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

        else -> false
    }

}