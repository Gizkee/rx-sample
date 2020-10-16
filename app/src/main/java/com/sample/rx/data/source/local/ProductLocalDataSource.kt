package com.sample.rx.data.source.local

import io.reactivex.Completable
import io.reactivex.Single

interface ProductLocalDataSource {

    fun getProducts(): Single<List<ProductEntity>>

    fun getProductById(productId: Long): Single<ProductEntity>

    fun getProductsByCategoryId(categoryId: Int): Single<List<ProductEntity>>

    fun getCategories(): Single<List<ProductCategoryEntity>>

    fun getCategoryById(id: Int): Single<ProductCategoryEntity>

    fun getProductsModifiedTimestamp(): Single<Long>

    fun saveProducts(products: List<ProductEntity>): Completable

    fun saveCategories(categories: List<ProductCategoryEntity>): Completable

    fun saveProductsModifiedTimestamp(timestamp: Long): Completable
}