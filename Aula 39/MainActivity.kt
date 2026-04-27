package com.example.projetosqlitecapgemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.projetosqlitecapgemini.data.database.AppDatabase
import com.example.projetosqlitecapgemini.data.entity.Person
import com.example.projetosqlitecapgemini.ui.theme.ProjetoSQLiteCapgeminiTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Testar SQLite
        val db = AppDatabase.getDatabase(applicationContext)
        val dao = db.personDao()

        lifecycleScope.launch(Dispatchers.IO) {
            //dao.insert(Person(name = "Fernanda", age = 29))

            //dao.update(Person(id = 3, name = "Juliana", age = 23))

            dao.delete(Person(id = 4, name = "Augusto", age = 47))

            dao.select().collect { persons ->
                persons.forEach { person ->
                    println("Pessoa -> ${person.name} - ${person.age}")
                }
            }
        }



        enableEdgeToEdge()
        setContent {
            ProjetoSQLiteCapgeminiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjetoSQLiteCapgeminiTheme {
        Greeting("Android")
    }
}