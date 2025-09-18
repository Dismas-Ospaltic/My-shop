package com.diwtech.myshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "single_product")
data class SingleProductSaleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String, //DD-MM-YYYY
    val receipt: String,
    val productName: String,
    val productId: String,
    val productCode: String,
    val quantity: Int,
    val price: Float,
    val total: Float,
    val timestamp: Long = System.currentTimeMillis()
)