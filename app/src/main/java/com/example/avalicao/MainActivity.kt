package com.example.avalicao

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

data class Product(val nome: String, val categoria: String, val preco: Double, val quantidade: Int)

object ProductList {
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun getProducts(): List<Product> {
        return products.toList()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Teste()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

    fun Teste() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "cadastro") {
            composable("cadastro") { TelaCadastro(navController) }
            composable("lista") { ListaProdutos(navController) }
            composable("detalhes/{produtoJson}") { backStackEntry ->
                DetalhesProduto(produtoJson = backStackEntry.arguments?.getString("produtoJson"), navController = navController)
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(navController: NavHostController) {
    var produto by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = produto,
            onValueChange = { produto = it },
            label = { Text("Nome do Produto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoria") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = preco,
            onValueChange = { preco = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade em Estoque") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val precoValue = preco.toDoubleOrNull()
            val quantidadeValue = quantidade.toIntOrNull()

            if (produto.isEmpty() || categoria.isEmpty() || preco.isEmpty() || quantidade.isEmpty()) {
                Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
            } else if (precoValue == null || precoValue < 0) {
                Toast.makeText(context, "Preço deve ser maior ou igual a 0", Toast.LENGTH_SHORT).show()
            } else if (quantidadeValue == null || quantidadeValue < 1) {
                Toast.makeText(context, "Quantidade deve ser maior ou igual a 1", Toast.LENGTH_SHORT).show()
            } else {
                // Armazenar o produto na lista usando Estoque
                Estoque.adicionarProduto(Product(produto, categoria, precoValue, quantidadeValue))
                produto = ""
                categoria = ""
                preco = ""
                quantidade = ""
                Toast.makeText(context, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                // Navegar para a tela de lista
                navController.navigate("lista")
            }
        }) {
            Text("Cadastrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Teste()
}