package com.example.api1.data.remote

import com.example.api1.data.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // URL da API
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Instância da API
    val api: ApiService by lazy {

        // Build - Configurar o acesso para a API
        Retrofit.Builder()
            .baseUrl(BASE_URL)                                  // Disponibilizar a URL da API
            .addConverterFactory(GsonConverterFactory.create()) // Tipo de informação (JSON, TXT, XML, etc.)
            .build()                                            // Realizar o build (construção)
            .create<ApiService>(ApiService::class.java)         // Disponibilizar os serviços
    }

}