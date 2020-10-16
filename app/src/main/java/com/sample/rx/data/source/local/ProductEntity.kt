package com.sample.rx.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
        @PrimaryKey
        val id: Long,
        val name: String,
        val price: Int,
        val vat: Int,
        val imageUrl: String,
        val categoryId: Int
)