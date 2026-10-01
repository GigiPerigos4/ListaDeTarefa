package br.com.local.atv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.local.atv2.ui.theme.Atv2Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Atv2Theme {

            }
        }
    }
}

data class Tarefa(
    val id: Int,
    val titulo: String,
    val data: String,
    var concluida: Boolean = false
)

@Composable
fun Tela(){
    var tarefas by remember {mutableStateOf("")}
    var datas by remember {mutableStateOf("")}
    val listaTarefas = remember {mutableStateListOf<Tarefa>()}
    var contadorId by remember {mutableStateOf(1)}

    Column(
        modifier = Modifier

    ){
        Text(
            text = "Lista de Tarefas",
            fontSize = 24.sp
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar tarefa"
        )

        OutlinedTextField(
            value = tarefas,
            onValueChange = {tarefas = it},
            label = {Text("Nome da Tarefa")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = false
        )

        OutlinedTextField(
            value = datas,
            onValueChange = {datas = it},
            label = {Text("data da Tarefa")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                if (tarefas.isNotBlank()) {
                    listaTarefas.add(
                        Tarefa(
                            id = contadorId++,
                            titulo = tarefas,
                            data = if (datas.isBlank()) "Sem data" else datas
                        )
                    )
                    tarefas = ""
                    datas = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Tarefa")
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (listaTarefas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhuma tarefa adicionada ainda.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(listaTarefas) { tarefa ->
                        Text(
                            text = "${tarefa.titulo} - ${tarefa.data}",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}