package com.diwtech.myshop.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.diwtech.myshop.model.Sales
import com.diwtech.myshop.model.SingleProductSaleEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface SingleProductSaleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSoldProducts(singleProductSaleEntity: SingleProductSaleEntity)

    @Update
    suspend fun updateSoldProducts(singleProductSaleEntity: SingleProductSaleEntity)


    @Query("SELECT * FROM single_product WHERE date = :saleDate")
    fun getAllSingleSalesByDate(saleDate: String): Flow<List<SingleProductSaleEntity>>


    // Get all grouped sales by receipt for a given date
    @Query("""
        SELECT 
            receipt, 
            date,
            SUM(total) AS totalSales 
        FROM single_product 
        WHERE date = :saleDate
        GROUP BY receipt, date
        ORDER BY receipt DESC
    """)
    fun getSingleSalesSummaryByDate(saleDate: String): Flow<List<Sales>>



    // Get all sold items for a specific receipt
    @Query("SELECT * FROM single_product WHERE receipt = :receiptNumber")
    fun getProductsSoldByReceipt(receiptNumber: String): Flow<List<SingleProductSaleEntity>>



//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSingleSale(singleSaleEntity: SingleSaleEntity)
//
//    @Update
//    suspend fun updateSingleSale(singleSaleEntity: SingleSaleEntity)

}