package com.sample.rx.data.source.local

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single

class ProductCache(
        private val productsDao: ProductDao,
        private val sharedPreferences: SharedPreferences
) : ProductLocalDataSource {

    override fun getProducts(): Single<List<ProductEntity>> {
        return productsDao.getProducts()
    }

    override fun getProductById(productId: Long): Single<ProductEntity> {
        return productsDao.getProductById(productId)
    }

    override fun getProductsByCategoryId(categoryId: Int): Single<List<ProductEntity>> {
        return productsDao.getProductsByCategoryId(categoryId)
    }

    override fun getCategories(): Single<List<ProductCategoryEntity>> {
        return productsDao.getProductCategories()
    }

    override fun getCategoryById(id: Int): Single<ProductCategoryEntity> {
        return productsDao.getProductCategoryById(id)
    }

    override fun getProductsModifiedTimestamp(): Single<Long> {
        return Single.fromCallable {
            sharedPreferences.getLong(KEY_PRODUCTS_MODIFIED, -1L)
        }
    }

    override fun saveProducts(products: List<ProductEntity>): Completable {
        return productsDao.saveProducts(products)
    }

    override fun saveCategories(categories: List<ProductCategoryEntity>): Completable {
        return productsDao.saveCategories(categories)
    }

    override fun saveProductsModifiedTimestamp(timestamp: Long): Completable {
        return Completable.fromCallable {
            sharedPreferences.edit()
                    .putLong(KEY_PRODUCTS_MODIFIED, timestamp)
                    .apply()
        }
    }

    companion object {
        const val KEY_PRODUCTS_MODIFIED = "com.sample.products.modified"
    }
}