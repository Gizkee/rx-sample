package com.sample.rx.data.source.remote

import com.sample.rx.data.source.local.ProductCategoryEntity
import com.sample.rx.data.source.local.ProductEntity

data class ProductModel(
        val products: List<ProductEntity>,
        val categories: List<ProductCategoryEntity>,
        val modifiedTimestamp: Long,
        val shopId: Int
)