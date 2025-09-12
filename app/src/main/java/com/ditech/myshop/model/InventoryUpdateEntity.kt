package com.ditech.myshop.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_update")
data class InventoryUpdateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String, //DD-MM-YYYY
    val productId: String,
    val updateId: String,
    val productCode: String,
    val productQuantity: Int,
    val previousProductQuantity: Int,
    val buyPrice: Float,
    val previousBuyPrice: Float,
    val timestamp: Long = System.currentTimeMillis()
)
