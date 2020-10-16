package com.sample.rx.data.source.local

import com.sample.rx.domain.model.Product

class ProductMapper {

    fun mapFromEntity(productEntity: ProductEntity): Product {
            return Product(
                    id = productEntity.id,
                    name = productEntity.name,
                    price = Product.Price(productEntity.price),
                    vat = productEntity.vat,
                    imageUrl = productEntity.imageUrl,
                    categoryId = productEntity.categoryId
            )
    }
}
