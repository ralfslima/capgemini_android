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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.aninterface.ui.theme.InterfaceTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Atividade3(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// EXERCÍCIOS
@Composable
fun Atividade3(modifier: Modifier){

    // Variáveis
    val numeroAleatorio = remember { Random.nextInt(1, 101) }
    var palpite by remember { mutableStateOf("") }
    var retorno by remember { mutableStateOf("") }
    var tentativas by remember { mutableStateOf(0) }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = palpite,
            onValueChange = {palpite = it},
            label = {Text("Informe um número")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                // Contabilizar tentiva
                tentativas++

                // Diferença entre o palpite e o número gerado
                val diferenca = kotlin.math.abs(palpite.toInt() - numeroAleatorio)

                // Retorno
                if(palpite.toInt() == numeroAleatorio){
                    retorno = "Acertou!!! Você precisou de $tentativas tentativas"
                }else{

                    if(diferenca <= 5)
                        retorno = "Muito perto!"
                    else if(diferenca <= 10)
                        retorno = "Perto!"
                    else if(diferenca <= 15)
                        retorno = "Longe!"
                    else
                        retorno = "Muito longe!"
                }

                // Limpar o valor do TextField
                palpite = ""
            }
        ) {
            Text("Vamos lá!")
        }
        Text(
            text = retorno,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 20.dp)
        )
    }


}
















@Composable
fun Atividade2(modifier: Modifier){

    // Variáveis
    var valor by remember { mutableStateOf("") }
    val formasPagamento = listOf<String>("Cartão de Crédito", "Cartão de Débito", "PIX", "TED", "Dinheiro")
    var formaPagamentoSelecionada by remember { mutableStateOf(formasPagamento[0]) }
    var pagamentoTotal by remember { mutableStateOf("") }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = valor,
            onValueChange = {valor = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Column() {
            formasPagamento.forEach { forma ->
                Row(
                    modifier = Modifier
                    .selectable(
                        selected = forma == formaPagamentoSelecionada,
                        onClick = { formaPagamentoSelecionada = forma}
                    )) {
                    RadioButton(
                        selected = forma == formaPagamentoSelecionada,
                        onClick = null
                    )
                    Text(forma)
                }
            }
            Button(
                onClick = {

                    if(formaPagamentoSelecionada == "Cartão de Crédito" || formaPagamentoSelecionada == "Cartão de Débito")
                        pagamentoTotal = "Não haverá desconto! Total a pagar R$ $valor"
                    else{
                        if(valor.toDouble() > 1000){
                            pagamentoTotal = "Haverá desconto! Total a pagar R$ ${valor.toDouble() * 0.9}"
                        }else{
                            pagamentoTotal = "Não haverá desconto! Total a pagar R$ $valor"
                        }
                    }
                },
                modifier = Modifier
                    .padding(vertical = 10.dp)
            ) {
                Text("Calcular")
            }
            Text(pagamentoTotal)
        }
    }

}

















@Composable
fun Atividade1(modifier: Modifier){

    // Variáveis
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var media by remember { mutableStateOf(0.0) }
    var situacao by remember { mutableStateOf("") }
    var imagem by remember { mutableStateOf(0) }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nota1,
            onValueChange = {nota1 = it},
            label = {Text("Informe a 1ª nota")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        TextField(
            value = nota2,
            onValueChange = {nota2 = it},
            label = {Text("Informe a 2ª nota")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        TextField(
            value = nota3,
            onValueChange = {nota3 = it},
            label = {Text("Informe a 3ª nota")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        Button(
            onClick = {

                // Realizar média
                media = (nota1.toDouble() + nota2.toDouble() + nota3.toDouble()) / 3

                // Verificar a situação e a imagem
                if(media >= 7){
                    situacao = "Aprovado(a)"
                    imagem = R.drawable.aprovado
                } else if(media >= 5){
                    situacao = "Recuperação"
                    imagem = R.drawable.recuperacao
                } else {
                    situacao = "Reprovado(a)"
                    imagem = R.drawable.reprovado
                }
            }
        ) {
            Text("Verificar situação")
        }

        // Caso seja realizada a média
        if(imagem != 0){
            Text(
                text = "$situacao, com média: ${"%.2f".format(media)}",
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            Image(
                painter = painterResource(id = imagem),
                contentDescription = "Imagem da situação do aluno"
            )
        }

    }

}

















// COMPONENTES
@OptIn(ExperimentalMaterial3Api::class) // Annotation para informar que há uso de comandos consideradores experimentais
@Composable
fun Componente5(modifier: Modifier) {

    // Linguagens
    val linguagens = listOf("C#", "Java", "Kotlin", "Python")

    // Exibição do menu (dropdown)
    var menu by remember { mutableStateOf(false) }

    // Armazenar a linguagem selecionada
    var linguagemSelecionada by remember { mutableStateOf(linguagens[0]) }

    // Estrutura de coluna
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título
        Text(text = "Escolha uma linguagem:")

        // ESSENCIAL: usar ExposedDropdownMenuBox
        ExposedDropdownMenuBox(
            expanded = menu,
            onExpandedChange = { menu = !menu }
        ) {

            // Campo (ancora do menu)
            TextField(
                value = linguagemSelecionada,
                onValueChange = {},
                readOnly = true,
                label = { Text("Linguagem") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(menu)
                },

                // MUITO IMPORTANTE: isso conecta o menu ao campo
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    )
            )

            // Menu dropdown
            ExposedDropdownMenu(
                expanded = menu,
                onDismissRequest = { menu = false }
            ) {
                linguagens.forEach { linguagem ->
                    DropdownMenuItem(
                        text = { Text(linguagem) },
                        onClick = {
                            linguagemSelecionada = linguagem
                            menu = false
                        }
                    )
                }
            }
        }
    }
}


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












