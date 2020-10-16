package com.sample.rx.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_categories")
data class ProductCategoryEntity(
        @PrimaryKey
        val id: Int,
        val name: String,
        val imageUrl: String
)