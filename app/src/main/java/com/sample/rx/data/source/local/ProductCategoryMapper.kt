package com.sample.rx.data.source.local

import com.sample.rx.domain.model.ProductCategory

class ProductCategoryMapper {

    fun mapFromEntity(productCategoryEntity: ProductCategoryEntity): ProductCategory {
        return ProductCategory(
                id = productCategoryEntity.id,
                name = productCategoryEntity.name,
                imageUrl = productCategoryEntity.imageUrl)
    }

    fun mapToEntity(productCategory: ProductCategory): ProductCategoryEntity {
        return ProductCategoryEntity(
                id = productCategory.id,
                name = productCategory.name,
                imageUrl = productCategory.imageUrl)
    }
}