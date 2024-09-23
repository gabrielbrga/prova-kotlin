package com.example.avalicao

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesProduto(produtoJson: String?, navController: NavHostController) {
    val gson = Gson()
    val produto = gson.fromJson(produtoJson, Product::class.java)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Nome: ${produto.nome}")
        Text(text = "Categoria: ${produto.categoria}")
        Text(text = "Preço: R$ ${produto.preco}")
        Text(text = "Quantidade: ${produto.quantidade}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
