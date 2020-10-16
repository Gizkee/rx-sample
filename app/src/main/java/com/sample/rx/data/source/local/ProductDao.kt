package com.sample.rx.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getProducts(): Single<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id == :productId")
    fun getProductById(productId: Long): Single<ProductEntity>

    @Query("SELECT * FROM products WHERE categoryId == :categoryId ORDER BY name")
    fun getProductsByCategoryId(categoryId: Int): Single<List<ProductEntity>>

    @Query("SELECT * FROM product_categories")
    fun getProductCategories(): Single<List<ProductCategoryEntity>>

    @Query("SELECT * FROM product_categories WHERE id == :id")
    fun getProductCategoryById(id: Int): Single<ProductCategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProducts(products: List<ProductEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategories(categories: List<ProductCategoryEntity>): Completable
}