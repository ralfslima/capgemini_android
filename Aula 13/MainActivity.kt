package com.example.aninterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.aninterface.ui.theme.InterfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Componente4(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}




// COMPONENTES
@Composable
fun Componente4(modifier: Modifier){

    // Linguagens de programação
    val linguagens = listOf<String>("C#", "Java", "Kotlin", "Python")

    // Obter a linguagem selecionada
    var linguagemSelecionada by remember { mutableStateOf(linguagens[0]) }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Percorrer a lista de linguagens
        linguagens.forEach {
            linguagem ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = linguagem == linguagemSelecionada,
                        onClick = {linguagemSelecionada = linguagem}
                    )
            ) {
                RadioButton(
                    selected = linguagem == linguagemSelecionada,
                    onClick = null
                )
                Text(
                    text = linguagem
                )
            }
        }

        Text(
            text = "A linguagem selecionada é $linguagemSelecionada",
            fontSize = 25.sp
        )
    }

}

//linguagens.forEach {
//    Row() {
//        Text(it)
//    }
//}
















@Composable
fun Componente3(modifier: Modifier){

    // Variável de status do checkbox
    var status by remember { mutableStateOf(false) }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Checkbox(
            checked = status,
            onCheckedChange = { valorAtual -> status = valorAtual}
        )
        Text(
            text = if(status) "Ativo" else "Inativo",
            fontSize = 30.sp,
            color = Color.Red
        )
    }

}


















@Composable
fun Componente2(modifier: Modifier){
    // Variável
    var numero by remember { mutableStateOf(0) }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0058ab)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagem_numeros),
            contentDescription = "Ícone de números"
        )
        Text(
            text = numero.toString(),
            color = Color.White,
            fontSize = 50.sp,
            modifier = Modifier
                .padding(vertical = 15.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Botao("Incrementar") { numero++ }
            Botao("Decrementar") { numero-- }
        }
    }
}

















@Composable
fun Componente1(modifier: Modifier){
    // Variável contendo um texto
    //var texto = remember { mutableStateOf("Ralf") }
    var texto by remember { mutableStateOf("") }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = texto,
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        TextField(
            value = texto,
            onValueChange = {texto = it},
            //label = { Text("Digite algo...") },
            placeholder = { Text("Digite algo...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}















/*
Características que podem ser encadeadas
📐 Layout / tamanho
padding(...)
fillMaxSize()
width(), height()
size()
aspectRatio()

📍 Posicionamento
offset()
absoluteOffset()

🎨 Aparência
background()
border()
clip()
alpha()

🖱️ Interação
clickable()
pointerInput()
scrollable()

🧭 Comportamento
nestedScroll()
draggable()
focusable()
*/



/*Layouts
* Box    -> Agrupador
* Row    -> Estrutura de linha
* Column -> Estrutura de coluna
* */

@Composable
fun Estrutura3(modifier: Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .weight(1f)
                .fillMaxWidth()
            //.size(50.dp)
        ){Text("1")}

        Box(
            modifier = Modifier
                .background(Color.Blue)
                .weight(1f)
                .fillMaxWidth()
        ){Text("2")}

        Box(modifier = Modifier
            .background(Color.Green)
            .weight(1f)
            .fillMaxWidth()
        ){Text("3")}

        Box(modifier = Modifier
            .background(Color.Magenta)
            .weight(1f)
            .fillMaxWidth()
        ){Text("4")}
    }
}
















@Composable
fun Estrutura2(modifier: Modifier){
    Row(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .background(Color.Red)
                .weight(1f)
                //.size(50.dp)
        ){Text("1")}

        Box(
            modifier = modifier
                .background(Color.Blue)
                .weight(1f)
        ){Text("2")}

        Box(modifier = modifier
            .background(Color.Green)
            .weight(1f)
        ){Text("3")}

        Box(modifier = modifier
            .background(Color.Magenta)
            .weight(1f)
        ){Text("4")}
    }
}








@Composable
fun Estrutura1(modifier: Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center

    ){
        Text("Meu primeiro layout...", fontSize = 30.sp)
    }
}












