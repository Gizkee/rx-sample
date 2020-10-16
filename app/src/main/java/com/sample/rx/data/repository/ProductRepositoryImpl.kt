package com.sample.rx.data.repository

import com.sample.rx.data.source.local.ProductCategoryMapper
import com.sample.rx.data.source.local.ProductLocalDataSource
import com.sample.rx.data.source.local.ProductMapper
import com.sample.rx.data.source.remote.ProductRemoteDataSourceFactory
import com.sample.rx.domain.model.Product
import com.sample.rx.domain.model.ProductCategory
import com.sample.rx.domain.repository.ProductRepository
import io.reactivex.Completable
import io.reactivex.Single
import org.threeten.bp.Instant

class ProductRepositoryImpl(
    private val remoteDataSourceFactory: ProductRemoteDataSourceFactory,
    private val localDataSource: ProductLocalDataSource,
    private val productMapper: ProductMapper,
    private val productCategoryMapper: ProductCategoryMapper
) : ProductRepository {

    override fun getProducts(): Single<List<Product>> {
        return localDataSource.getProducts()
                .map { productEntities ->
                    productEntities.map { listItem -> productMapper.mapFromEntity(listItem) }
                }
    }

    override fun getProductById(productId: Long): Single<Product> {
        return localDataSource.getProductById(productId)
                .map { productEntity -> productMapper.mapFromEntity(productEntity) }
    }

    override fun getProductsByCategoryId(categoryId: Int): Single<List<Product>> {
        return localDataSource.getProductsByCategoryId(categoryId)
                .map { productEntities ->
                    productEntities.map { listItem -> productMapper.mapFromEntity(listItem) }
                }
    }

    override fun getCategories(): Single<List<ProductCategory>> {
        return localDataSource.getCategories()
                .map { productCategoryEntities ->
                    productCategoryEntities.map { listItem ->
                        productCategoryMapper.mapFromEntity(listItem)
                    }
                }
    }

    override fun getCategoryById(id: Int): Single<ProductCategory> {
        return localDataSource.getCategoryById(id)
                .map { productCategoryEntity ->
                    productCategoryMapper.mapFromEntity(productCategoryEntity)
                }
    }

    override fun getProductsModifiedTimestamp(): Single<Instant> {
        return localDataSource.getProductsModifiedTimestamp()
                .map { millis ->
                    if (millis > 0) {
                        Instant.ofEpochMilli(millis)
                    } else {
                        Instant.MIN
                    }
                }
    }

    override fun updateProducts(): Completable {
        return remoteDataSourceFactory.create()
                .getProducts()
                .flatMapCompletable { productsModel ->
                    Completable.mergeArray(
                            localDataSource.saveCategories(productsModel.categories),
                            localDataSource.saveProducts(productsModel.products),
                            localDataSource.saveProductsModifiedTimestamp(
                                    DateTimeTransformer.fromTicksToEpochMillis(productsModel.modifiedTimestamp)
                            )
                    )
                }
    }
}