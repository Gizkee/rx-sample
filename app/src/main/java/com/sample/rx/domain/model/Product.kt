package com.sample.rx.domain.model

data class Product(
        val id: Long,
        val name: String,
        val price: Price,
        val vat: Int,
        val imageUrl: String,
        val categoryId: Int
) {
    data class Price(val value: Int)
}