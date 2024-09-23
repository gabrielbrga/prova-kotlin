package com.example.avalicao

class Estoque {
    companion object {
        private val products = mutableListOf<Product>()

        fun adicionarProduto(product: Product) {
            products.add(product)
        }

        fun calcularValorTotalEstoque(): Double {
            return products.sumOf { it.preco * it.quantidade }
        }

        fun getProducts(): List<Product> {
            return products.toList()
        }
    }

}