// ******* PRECISA ADICIONAR PERMISSÃO NO ANDROIDMANIFEST
// <uses-permission android:name="android.permission.READ_CONTACTS"/>

package com.example.recursossmartphone.telas

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

// Interface gráfica
@Composable
fun Contatos() {

    // Contexto
    val contexto = LocalContext.current

    // Lista de contatos
    var listaContatos by remember { mutableStateOf(listOf<String>()) }

    // Permissão para acessar os contatos
    val permissao = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {

        retorno ->

        if(retorno) {
            listaContatos = obterContatos(contexto)
        }

    }

    // Coluna
    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        // Pedir permissão para efetuar a listagem de contatos
        Button(
            onClick = {
                // Verificar se já possui permissão
                val verificarPermissao = ContextCompat.checkSelfPermission(contexto, Manifest.permission.READ_CONTACTS)

                // Condicional
                if(verificarPermissao == PackageManager.PERMISSION_GRANTED) {
                    listaContatos = obterContatos(contexto)
                } else {
                    permissao.launch(Manifest.permission.READ_CONTACTS)
                }
            }
        ) { Text("Carregar contatos") }

        Spacer(modifier = Modifier.padding(10.dp))

        LazyColumn {
            items(listaContatos) { nome ->
                Text(
                    text = nome,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }



    }

}






// Função para obter todos os contatos
fun obterContatos(contexto: Context): List<String> {

    // Lista contendo o nome dos contatos
    val lista = mutableListOf<String>()

    // Cursor - Cópia dos contatos
    val cursor = contexto.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,
        null,
        null,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
    )

    // Trabalhar com os dados copiados/obtidos via cursor
    cursor?.use {

        // Índice de nome
        val indiceNome = it.getColumnIndex(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        // Laço de repetição
        while(it.moveToNext()) {
            val nome = it.getString(indiceNome)

            lista.add(nome)
        }

    }

    // Retorno
    return lista.distinct()

}




