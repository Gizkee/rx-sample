package com.sample.rx.domain.repository

import com.sample.rx.domain.model.Product
import com.sample.rx.domain.model.ProductCategory
import io.reactivex.Completable
import io.reactivex.Single
import org.threeten.bp.Instant

interface ProductRepository {

    fun getProducts(): Single<List<Product>>

    fun getProductById(productId: Long): Single<Product>

    fun getProductsByCategoryId(categoryId: Int): Single<List<Product>>

    fun getCategories(): Single<List<ProductCategory>>

    fun getCategoryById(id: Int): Single<ProductCategory>

    fun updateProducts(): Completable

    fun getProductsModifiedTimestamp(): Single<Instant>

}