package com.example.recursossmartphone.telas

import android.content.Context
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

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
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "ASC"
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
    return lista

}




