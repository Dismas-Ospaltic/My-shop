package com.diwtech.myshop.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.diwtech.myshop.model.DailySalesReport
import com.diwtech.myshop.model.GenSaleEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface GenSaleDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSingleSale(singleSaleEntity: SingleSaleEntity)
//
//    @Update
//    suspend fun updateSingleSale(singleSaleEntity: SingleSaleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenSale(genSaleEntity: GenSaleEntity)

    @Update
    suspend fun updateGenSale(genSaleEntity: GenSaleEntity)

    @Query("SELECT * FROM gen_sale ORDER BY timestamp DESC")
    fun getAllGenSale(): Flow<List<GenSaleEntity>>

    @Query("SELECT * FROM gen_sale WHERE date = :saleDate ORDER BY timestamp DESC")
    fun getGenSalesByDate(saleDate: String): Flow<List<GenSaleEntity>>




    @Query("""
    SELECT 
        date,
        SUM(CASE WHEN saleType = 'Cash' THEN totalSale ELSE 0 END) AS cash,
        SUM(CASE WHEN saleType = 'Bank' THEN totalSale ELSE 0 END) AS bank,
        SUM(CASE WHEN saleType = 'M-pesa' THEN totalSale ELSE 0 END) AS mpesa,
        SUM(CASE WHEN saleType NOT IN ('Cash','Bank','M-pesa') THEN totalSale ELSE 0 END) AS other,
        SUM(totalSale) AS total
    FROM gen_sale
    GROUP BY date
    ORDER BY date ASC
""")
    fun getDailySalesReports(): Flow<List<DailySalesReport>>



    @Query("SELECT SUM(totalSale) FROM gen_sale WHERE date = :todayDate")
    fun getTotalSalesToday(todayDate: String): Flow<Float?>

    @Query("SELECT SUM(totalSale) FROM gen_sale WHERE date LIKE :month || '%'")
    fun getMonthlyTotalSales(month: String): Flow<Float?>


    @Query("SELECT COUNT(*) FROM gen_sale WHERE date LIKE :month || '%'")
    fun getNumberOfMonthlySales(month: String): Flow<Int?>


    @Query("SELECT COUNT(*) FROM gen_sale WHERE date = :todayDate")
    fun getNumberOfSalesToday(todayDate: String): Flow<Int?>




}