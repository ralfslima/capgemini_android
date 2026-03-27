package com.example.aninterface

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.aninterface.ui.theme.InterfaceTheme
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Atividade6(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}






// COMPONENTES
@Composable
fun Componente12(modifier: Modifier){

    var temaClaro by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(if(temaClaro) Color.White else Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Switch(
            checked = temaClaro,
            onCheckedChange = { temaClaro = it }
        )
    }
}

@Composable
fun Componente11(modifier: Modifier){

    /*
        LazyVerticalGrid   -> Coluna
        LazyHorizontalGrid -> Linha

        Adaptative -> Definem um tamanho mínimo
        Fixed      -> Define a quantidade de colunas
        FixedSize  -> Definem um tamanho único
    */

    // Lista com 30 números
    val numeros = List(30) {it}

    // Variável para armazenar o número selecionado
    var numeroSelecionado by remember { mutableStateOf("Nenhum número selecionado.") }

    // Estrutura
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ){
            Text(
                text = numeroSelecionado,
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
        }

        items(numeros){numero ->
            GerarCard(
                numero,
                funcaoSelecionarNumero = { valor -> numeroSelecionado = "O número selecionado é: $valor" }
            )
        }
    }
}

@Composable
fun GerarCard(numero: Int, funcaoSelecionarNumero: (Int) -> Unit){
    Card(
        modifier = Modifier
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF4287f5),
                            Color(0xFF0019d4)
                        )
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = numero.toString(),
                fontSize = 25.sp,
                color = Color.White
            )
            Button(
                onClick = { funcaoSelecionarNumero(numero) }
            ) {
                Text("Clique aqui")
            }
        }
    }
}

@Composable
fun Componente10(modifier: Modifier){

    /*
    BOX    -> Caixa/Agrupador
    Row    -> Trabalhar com uma estrutura de linha
    Column -> Trabalhar com uma estrutura de coluna
        forEach -> Utilizado para percorrer uma lista/coleção

    LazyRow    -> Trabalhar com uma estrutura de linha
    LazyColumn -> Trabalhar com uma estrutura de coluna
        items  -> Utilizado para percorrer uma lista/coleção
    */

    // Lista contendo 300 registros
    val lista = List(300) { "Item da lista ${it+1}" }

    // Estrutura
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        // Laço de repetição
        items(lista) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .height(80.dp),
                colors = CardDefaults.cardColors(
                    contentColor = Color.LightGray
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(item)
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Componente9(modifier: Modifier){

    var exibir by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Botão para exibir o modal
        Button(onClick = {exibir = true}) {
            Text("Exibir modal")
        }

        // Componente Dialog (modal/janela)
        if(exibir){

            AlertDialog(
                onDismissRequest = {exibir = false},
                title = { Text("Meu primeiro modal") },
                text = { Text("Hello World!") },
                confirmButton = {
                    Button(
                        onClick = {exibir = false}
                    ) { Text("Ok") }
                },
                dismissButton = {
                    Button(
                        onClick = {exibir = false}
                    ) { Text("Fechar") }
                }
            )



        }

    }

}

@Composable
fun Componente8(modifier: Modifier){

    var email by remember { mutableStateOf("") }
    var emailValido = email.length >= 5 && email.contains("@")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Ícone de e-mail"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            //isError = false, <- Deixa a borda vermelha em caso de erro/falha
            trailingIcon = {
                if(email.isNotEmpty()){

                    if(emailValido) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "E-mail válido",
                            tint = Color.Green
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "E-mail inválido",
                            tint = Color.Yellow
                        )
                    }

                }
            }
        )

    }

}










@Composable
fun Componente7(modifier: Modifier){

    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // CARD
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Blue),
            modifier = Modifier
                .size(150.dp)
        ) { }

        // Espaçamento
        Spacer(modifier = Modifier.height(30.dp))

        // CARD COM SOMBRA
        ElevatedCard(
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            modifier = Modifier
                .size(150.dp)
                .shadow(
                    elevation = 20.dp,
                    spotColor = Color.Red
                )
        ) { }


        // Espaçamento
        Spacer(modifier = Modifier.height(30.dp))

        // CARD COM BORDA
        OutlinedCard(
            border = BorderStroke(1.dp, Color.Black),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .size(150.dp)
        ) { }
    }

}

@Composable
fun Componente6(modifier: Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .size(60.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Icon(
            painter = painterResource(id = R.drawable.baseline_diamond_24),
            contentDescription = "Ícone de diamante",
            tint = Color.Blue,
            modifier = Modifier
                .size(60.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Icon(
            painter = painterResource(id = R.drawable.estrela),
            contentDescription = "Ícone de diamante",
            tint = Color.Yellow,
            modifier = Modifier
                .size(60.dp)
        )
    }
}

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

// EXERCÍCIO - LOJA VIRTUAL (25/03)
@Composable
fun Atividade6(modifier: Modifier = Modifier) {
    // Criar objetos da classe Produto
    var p1 = Produto("Notebook Acer Nitro V15", 6400.76, "img1")
    var p2 = Produto("Notebook Dell Inspiron I15", 6119.09, "img2")
    var p3 = Produto("Notebook Dell Vostro I12", 5279.29, "img3")
    var p4 = Produto("Notebook ASUS Vivobook", 3699.00, "img4")
    var p5 = Produto("Notebook Acer Aspire 5", 3590.28, "img5")
    var p6 = Produto("Notebook VAIO FE16", 4699.00, "img6")
    var p7 = Produto("Notebook Gamer Predator", 10900.00, "img7")
    var p8 = Produto("Notebook Lenovo Slim", 4599.08, "img8")
    var p9 = Produto("Notebook Ultra", 1349.10, "img9")
    var p10 = Produto("Notebook ASUS Intel", 2061.24, "img10")
    var p11 = Produto("Samsung Galaxy Book4", 4499.00, "img11")
    var p12 = Produto("Notebook Yoga Slim 7i ", 5436.35, "img12")

    // Lista de produtos
    var produtos = mutableListOf<Produto>()

    // Adicionar produtos
    produtos.add(p1)
    produtos.add(p2)
    produtos.add(p3)
    produtos.add(p4)
    produtos.add(p5)
    produtos.add(p6)
    produtos.add(p7)
    produtos.add(p8)
    produtos.add(p9)
    produtos.add(p10)
    produtos.add(p11)
    produtos.add(p12)

    // Vetor para armazenar objetos do tipo ItemCarrinho (produto e quantidade)
    val carrinho = remember { mutableStateListOf<ItemCarrinho>() }

    // Variável lógica para exibir ou ocultar o AlertDialog
    var mostrarDialog by remember { mutableStateOf(false) }

    // Somar a quantidade de itens adicionados no carrinho
    val totalItens = carrinho.sumOf { it.quantidade }

    // Modal
    if (mostrarDialog) {
        CarrinhoDialog(carrinho) {
            mostrarDialog = false
        }
    }

    // Estrutura
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ){
            Text(
                text = if (totalItens == 0) "Nenhum produto selecionado"
                else "Itens no carrinho: $totalItens",
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        item(
            span = { GridItemSpan(maxLineSpan) }
        ){
            Button(
                onClick = { mostrarDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7125db),
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Produtos adicionados")
            }
        }

        items(produtos){ produto ->
            CardProduto(produto){
                val index = carrinho.indexOfFirst { it.produto.nome == produto.nome }

                if (index != -1) {
                    val itemAtual = carrinho[index]
                    carrinho[index] = ItemCarrinho(itemAtual.produto, itemAtual.quantidade + 1)
                } else {
                    carrinho.add(ItemCarrinho(produto, 1))
                }
            }
        }
    }
}

@Composable
fun CardProduto(produto: Produto, adicionarItem: () -> Unit){

    // Obter o contexto atual
    val context = LocalContext.current

    // Converter a String "nome_da_imagem" para o ID (R.drawable.nome_da_imagem)
    val resourceId = context.resources.getIdentifier(
        produto.imagem,
        "drawable",
        context.packageName
    )

    Card(
        modifier = Modifier
            .height(320.dp)
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = resourceId),
                contentDescription = produto.nome,
                modifier = Modifier
                    .height(160.dp)
            )
            Text(
                text = produto.nome,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "R$ %.2f".format(produto.valor),
                fontSize = 14.sp,
                color = Color.Black
            )
            Button(
                onClick = { adicionarItem() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7125db),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrinho de compras"
                )
                Text("Adicionar")
            }
        }
    }
}

@Composable
fun CarrinhoDialog(
    carrinho: List<ItemCarrinho>,
    onClose: () -> Unit
) {

    val context = LocalContext.current

    val totalCompra = carrinho.sumOf {
        it.produto.valor * it.quantidade
    }

    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = {
            Button(onClick = onClose) {
                Text("Fechar")
            }
        },
        title = {
            Text("Carrinho")
        },
        text = {
            Column {
                carrinho.forEach { item ->

                    val resourceId = context.resources.getIdentifier(
                        item.produto.imagem,
                        "drawable",
                        context.packageName
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painterResource(resourceId),
                            contentDescription = "",
                            modifier = Modifier.size(60.dp)
                        )

                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(item.produto.nome)
                            Text("R$ %.2f".format(item.produto.valor))
                            Text("Qtd: ${item.quantidade}")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Total: R$ %.2f".format(totalCompra),
                    fontSize = 18.sp
                )
            }
        }
    )
}

class Produto{
    var nome: String = ""
    var valor: Double = 0.0
    var imagem: String = ""

    constructor(nome:String, valor:Double, imagem:String){
        this.nome = nome
        this.valor = valor
        this.imagem = imagem
    }
}

class ItemCarrinho(
    var produto: Produto,
    var quantidade: Int = 0
)











// EXERCÍCIO - MÉDIA (24/03)
@Composable
fun Atividade5(modifier: Modifier = Modifier) {

    // Variáveis
    var nome by remember { mutableStateOf("") }
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var abrirModal by remember { mutableStateOf(false) }
    var media by remember { mutableStateOf(0.0) }
    var situacao by remember { mutableStateOf("") }

    // Função para validar as notas
    fun validarNota(note: String): Boolean {
        val n = note.toDoubleOrNull()
        return n != null && n in 0.0..10.0
    }

    // Estrutura
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            trailingIcon = {
                if (nome.isNotEmpty()) {
                    Icon(
                        imageVector = if (nome.length >= 3) Icons.Default.Check else Icons.Default.Warning,
                        contentDescription = null,
                        tint = if (nome.length >= 3) Color.Green else Color.Red
                    )
                }
            }
        )

        // Nota 1
        OutlinedTextField(
            value = nota1,
            onValueChange = { nota1 = it },
            label = { Text("Nota 1") },
            trailingIcon = {
                if (nota1.isNotEmpty()) {
                    Icon(
                        imageVector = if (validarNota(nota1)) Icons.Default.Check else Icons.Default.Warning,
                        contentDescription = null,
                        tint = if (validarNota(nota1)) Color.Green else Color.Red
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Nota 2
        OutlinedTextField(
            value = nota2,
            onValueChange = { nota2 = it },
            label = { Text("Nota 2") },
            trailingIcon = {
                if (nota2.isNotEmpty()) {
                    Icon(
                        imageVector = if (validarNota(nota2)) Icons.Default.Check else Icons.Default.Warning,
                        contentDescription = null,
                        tint = if (validarNota(nota2)) Color.Green else Color.Red
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Nota 3
        OutlinedTextField(
            value = nota3,
            onValueChange = { nota3 = it },
            label = { Text("Nota 3") },
            trailingIcon = {
                if (nota3.isNotEmpty()) {
                    Icon(
                        imageVector = if (validarNota(nota3)) Icons.Default.Check else Icons.Default.Warning,
                        contentDescription = null,
                        tint = if (validarNota(nota3)) Color.Green else Color.Red
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Botão calcular média
        Button(
            onClick = {
                if (nome.length >= 3 && validarNota(nota1) && validarNota(nota2) && validarNota(nota3)) {
                    val n1 = nota1.toDouble()
                    val n2 = nota2.toDouble()
                    val n3 = nota3.toDouble()
                    media = (n1 + n2 + n3) / 3.0
                    situacao = if (media >= 7.0) "Aprovado" else "Reprovado"
                    abrirModal = true
                }
            },
            enabled = nome.length >= 3 && validarNota(nota1) && validarNota(nota2) && validarNota(nota3)
        ) {
            Text("Calcular Média")
        }

        // Modal com resultado
        if (abrirModal) {
            AlertDialog(
                onDismissRequest = { abrirModal = false },
                title = { Text("Resultado") },
                text = { Text("$nome, obteve média: %.2f\nSituação: %s".format(media, situacao)) },
                confirmButton = {
                    Button(onClick = { abrirModal = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}


// EXERCÍCIO - MENU (23/03)
@Composable
fun Atividade4(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row() {
            Text(
                text = "Menu",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
        // --- LINHA 1 ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Card 1
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Person, null, Modifier.size(40.dp))
                    Text("Perfil")
                }
            }
            // Card 2
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Settings, null, Modifier.size(40.dp))
                    Text("Config")
                }
            }
            // Card 3
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Email, null, Modifier.size(40.dp))
                    Text("E-mail")
                }
            }
        }

        // --- LINHA 2 ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Card 4
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.ShoppingCart, null, Modifier.size(40.dp))
                    Text("Loja")
                }
            }
            // Card 5
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Favorite, null, Modifier.size(40.dp))
                    Text("Favoritos")
                }
            }
            // Card 6
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Place, null, Modifier.size(40.dp))
                    Text("Mapa")
                }
            }
        }

        // --- LINHA 3 ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Card 7
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Face, null, Modifier.size(40.dp))
                    Text("Fotos")
                }
            }
            // Card 8
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.DateRange, null, Modifier.size(40.dp))
                    Text("Agenda")
                }
            }
            // Card 9
            ElevatedCard(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Close, null, Modifier.size(40.dp))
                    Text("Sair")
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
                val diferenca = abs(palpite.toInt() - numeroAleatorio)

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












