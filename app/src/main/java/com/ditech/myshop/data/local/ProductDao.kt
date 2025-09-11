package com.ditech.myshop.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.ditech.myshop.model.ProductEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface ProductDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSingleSale(singleSaleEntity: SingleSaleEntity)
//
//    @Update
//    suspend fun updateSingleSale(singleSaleEntity: SingleSaleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)


    @Query("SELECT * FROM product WHERE productState = 'Active' ORDER BY timestamp DESC")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Update
    suspend fun updateProduct(productEntity: ProductEntity)


    @Query("UPDATE product SET productCode = :productCode, productName = :productName, productQuantity = :productQuantity, buyPrice = :buyPrice, sellPrice = :sellPrice, manufactureDate = :manufactureDate, expiryDate = :expiryDate, productCategory = :productCategory WHERE productId = :productId")
    suspend fun updateProductDetailsById(productId: String, productCode: String, productName: String, productQuantity: Int, buyPrice: Float, sellPrice: Float, manufactureDate: String?, expiryDate: String?, productCategory: String): Int?


    //soft delete
   @Query("UPDATE product SET productState = 'Inactive' WHERE productId = :productId")
   suspend fun deleteProductById(productId: String): Int?

   @Query("SELECT * FROM product WHERE productState = 'Inactive' ORDER BY timestamp DESC")
   fun getInactiveProducts(): Flow<List<ProductEntity>>

   @Query("UPDATE product SET productQuantity = :newQuantity WHERE productId = :productId")
   suspend fun updateProductQuantityById(productId: String, newQuantity: Int): Int?

    @Query("SELECT * FROM product WHERE productState = 'Active' AND productQuantity < 10 ORDER BY timestamp DESC")
    fun getLowStockProducts(): Flow<List<ProductEntity>>





}