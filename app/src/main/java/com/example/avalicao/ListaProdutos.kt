package com.example.avalicao

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaProdutos(navController: NavHostController) {
    val produtos = Estoque.getProducts()
    val gson = Gson()

    LazyColumn {
        items(produtos) { produto ->
            ListItem(
                modifier = Modifier.fillMaxWidth(),
                headlineText = { Text("${produto.nome} (${produto.quantidade} unidades)") },
                trailingContent = {
                    Button(onClick = {
                        val produtoJson = gson.toJson(produto)
                        navController.navigate("detalhes/$produtoJson")
                    }) {
                        Text("Detalhes")
                    }
                }
            )
        }
    }
}