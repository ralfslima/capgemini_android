package com.example.projetosqlitecapgemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.projetosqlitecapgemini.data.database.AppDatabase
import com.example.projetosqlitecapgemini.data.entity.Address
import com.example.projetosqlitecapgemini.data.entity.Person
import com.example.projetosqlitecapgemini.ui.screen.PersonScreen
import com.example.projetosqlitecapgemini.ui.theme.ProjetoSQLiteCapgeminiTheme
import com.example.projetosqlitecapgemini.viewModel.PersonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    // Camada ViewModel
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Chamar a tela e dispor o viewModel para cadastrar e listar
            PersonScreen(viewModel = viewModel)
        }
    }
}