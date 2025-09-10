package com.ditech.myshop.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface GenSaleDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertSingleSale(singleSaleEntity: SingleSaleEntity)
//
//    @Update
//    suspend fun updateSingleSale(singleSaleEntity: SingleSaleEntity)

}