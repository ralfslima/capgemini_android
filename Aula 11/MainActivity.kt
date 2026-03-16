package com.example.aninterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aninterface.ui.theme.InterfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Estrutura3(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

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
            modifier = modifier
                .background(Color.Red)
                .weight(1f)
                .fillMaxWidth()
            //.size(50.dp)
        ){Text("1")}

        Box(
            modifier = modifier
                .background(Color.Blue)
                .weight(1f)
                .fillMaxWidth()
        ){Text("2")}

        Box(modifier = modifier
            .background(Color.Green)
            .weight(1f)
            .fillMaxWidth()
        ){Text("3")}

        Box(modifier = modifier
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












