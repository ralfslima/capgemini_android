package com.example.exercicio1_navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exercicio1_navegacao.ui.theme.Exercicio1_NavegacaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exercicio1_NavegacaoTheme {
                AppNavigation(listaMidias)
            }
        }
    }
}

// Classe
class Midia(
    val id: Int,
    val titulo: String,
    val tipo: String,
    val genero: String,
    val ano: Int,
    val avaliacao: Double,
    val duracao: String,
    val sinopse: String,
    val imagemRes: Int,
    val plataforma: String
)

// Lista de filmes/séries
val listaMidias = listOf(
    Midia(1, "Breaking Bad", "Série", "Drama", 2008, 9.5, "5 temporadas",
        "Um professor de química vira fabricante de metanfetamina.",
        R.drawable.breaking_bad, "Netflix"),

    Midia(2, "Stranger Things", "Série", "Ficção Científica", 2016, 8.7, "4 temporadas",
        "Crianças enfrentam forças sobrenaturais em uma pequena cidade.",
        R.drawable.stranger_things, "Netflix"),

    Midia(3, "O Poderoso Chefão", "Filme", "Crime", 1972, 9.2, "175 min",
        "A história da família mafiosa Corleone.",
        R.drawable.poderoso_chefao, "Prime Video"),

    Midia(4, "Interestellar", "Filme", "Ficção Científica", 2014, 8.6, "169 min",
        "Exploradores viajam pelo espaço para salvar a humanidade.",
        R.drawable.interestellar, "Prime Video"),

    Midia(5, "Dark", "Série", "Mistério", 2017, 8.8, "3 temporadas",
        "Viagens no tempo afetam várias gerações.",
        R.drawable.dark, "Netflix"),

    Midia(6, "Game of Thrones", "Série", "Fantasia", 2011, 9.2, "8 temporadas",
        "Famílias disputam o trono de ferro.",
        R.drawable.got, "HBO"),

    Midia(7, "The Witcher", "Série", "Fantasia", 2019, 8.2, "3 temporadas",
        "Um caçador de monstros luta para encontrar seu lugar.",
        R.drawable.the_witcher, "Netflix"),

    Midia(8, "Inception", "Filme", "Ação", 2010, 8.8, "148 min",
        "Um ladrão invade sonhos para roubar segredos.",
        R.drawable.inception, "HBO"),

    Midia(9, "Matrix", "Filme", "Ação", 1999, 8.7, "136 min",
        "Um hacker descobre a realidade simulada.",
        R.drawable.matrix, "HBO"),

    Midia(10, "The Office", "Série", "Comédia", 2005, 8.9, "9 temporadas",
        "O dia a dia de um escritório nada comum.",
        R.drawable.the_office, "Netflix"),

    Midia(11, "Friends", "Série", "Comédia", 1994, 8.9, "10 temporadas",
        "Seis amigos vivendo em Nova York.",
        R.drawable.friends, "HBO"),

    Midia(12, "Vingadores: Ultimato", "Filme", "Ação", 2019, 8.4, "181 min",
        "Heróis enfrentam Thanos em batalha final.",
        R.drawable.vingadores, "Disney+"),

    Midia(13, "Loki", "Série", "Aventura", 2021, 8.3, "2 temporadas",
        "O deus da trapaça causa caos no tempo.",
        R.drawable.loki, "Disney+"),

    Midia(14, "The Mandalorian", "Série", "Aventura", 2019, 8.7, "3 temporadas",
        "Um caçador de recompensas no universo Star Wars.",
        R.drawable.mandalorian, "Disney+"),

    Midia(15, "Coringa", "Filme", "Drama", 2019, 8.4, "122 min",
        "A origem sombria do vilão Joker.",
        R.drawable.coringa, "HBO"),

    Midia(16, "Parasita", "Filme", "Drama", 2019, 8.6, "132 min",
        "Uma família pobre se infiltra na vida de ricos.",
        R.drawable.parasita, "Prime Video"),

    Midia(17, "Round 6", "Série", "Drama", 2021, 8.0, "1 temporada",
        "Pessoas competem em jogos mortais por dinheiro.",
        R.drawable.round6, "Netflix"),

    Midia(18, "House of the Dragon", "Série", "Fantasia", 2022, 8.5, "2 temporadas",
        "A história da casa Targaryen.",
        R.drawable.house_of_dragon, "HBO"),

    Midia(19, "Gladiador", "Filme", "Ação", 2000, 8.5, "155 min",
        "Um general busca vingança em Roma.",
        R.drawable.gladiador, "Prime Video"),

    Midia(20, "Avatar", "Filme", "Ficção Científica", 2009, 7.8, "162 min",
        "Humanos exploram um planeta alienígena.",
        R.drawable.avatar, "Disney+")
)

@Composable
fun AppNavigation(lista: List<Midia>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "lista") {

        composable("lista") {
            TelaLista(lista, navController)
        }

        composable("detalhes/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            val item = lista.find { it.id == id }

            item?.let {
                TelaDetalhes(it, navController)
            }
        }
    }
}

@Composable
fun TelaLista(lista: List<Midia>, navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Filmes e Séries",
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        )

        LazyVerticalGrid (
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),

        ) {
            items(lista) { item ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(270.dp)
                        .padding(6.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = item.imagemRes),
                            contentDescription = item.titulo,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = item.titulo,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("detalhes/${item.id}")
                            }
                        ) {
                            Text("Detalhes")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TelaDetalhes(item: Midia, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text("Voltar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = item.imagemRes),
            contentDescription = item.titulo,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Título: ${item.titulo}")
        Text("Tipo: ${item.tipo}")
        Text("Gênero: ${item.genero}")
        Text("Ano: ${item.ano}")
        Text("Avaliação: ${item.avaliacao}")
        Text("Duração: ${item.duracao}")
        Text("Plataforma: ${item.plataforma}")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Sinopse:")
        Text(item.sinopse)
    }
}