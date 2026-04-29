package com.example.api1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api1.data.model.Post
import com.example.api1.data.repository.PostRepository
import com.example.api1.ui.theme.API1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    // Repositório
    val repository: PostRepository = PostRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            API1Theme {
                PostScreen(repository)
            }
        }
    }
}

@Composable
fun PostScreen(repository: PostRepository) {

    // Vetor para armazenar as postagens
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }

    // Ao carregar o componente/tela, realizar a requisição para API
    LaunchedEffect(Unit) {

        try {
            var result = withContext(Dispatchers.IO) {
                repository.getPosts()
            }

            posts = result
        } catch (e: Exception) {
            println("FALHA AO OBTER AS POSTAGENS -> ${e.message}")
        }

    }

    // Estrutura da tela - UI
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)
    ) {

        // Percorrer as postagens
        items(posts) { post ->

            // Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
            ) {
                // Id do usuário
                Text("Id do usuário: ${post.userId}")

                // Id da postagem
                Text("Id da postagem: ${post.id}")

                // Título da postagem
                Text("Título: ${post.title}")

                // Texto da postagem
                Text("Texto: ${post.body}")
            }

        }

    }

}








