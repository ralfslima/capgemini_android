package com.example.api2.data.remote

import com.example.api2.data.service.StudentService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // URL base da API (todas as requisições partem daqui)
    private const val BASE_URL = "https://api-capgemini.onrender.com/"

    // Instância da API criada de forma lazy (só quando for usada)
    val api: StudentService by lazy {

        // Builder do Retrofit → usado para configurar o cliente
        Retrofit.Builder()

            // Define a URL base da API
            .baseUrl(BASE_URL)

            // Define o conversor JSON → objeto Kotlin
            .addConverterFactory(GsonConverterFactory.create())

            // Cria a instância do Retrofit com as configs acima
            .build()

            // Cria a implementação da interface ApiService
            .create(StudentService::class.java)
    }

}