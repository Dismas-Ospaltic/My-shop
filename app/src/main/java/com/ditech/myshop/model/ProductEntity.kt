package com.ditech.myshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String, //DD-MM-YYYY
    val productId: String,
    val productCode: String,
    val productName: String,
    val productQuantity: Int,
    val productCategory: String = "other",
    val buyPrice: Float,
    val sellPrice: Float,
    val manufactureDate: String? = null,
    val expiryDate: String? = null,
    val productState: String = "active",
    val timestamp: Long = System.currentTimeMillis()
)