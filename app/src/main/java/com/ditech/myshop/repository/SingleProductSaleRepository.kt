package com.ditech.myshop.repository



import com.ditech.myshop.data.local.GenSaleDao
import com.ditech.myshop.data.local.SingleProductSaleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SingleProductSaleRepository(private val singleProductSaleDao: SingleProductSaleDao) {

//    suspend fun insertSingleSale(singleSaleEntity: SingleSaleEntity) {
//        singleSaleDao.insertSingleSale(singleSaleEntity)
//    }
//
//    suspend fun updateSingleSale(singleSaleEntity: SingleSaleEntity) {
//        singleSaleDao.updateSingleSale(singleSaleEntity)
//    }
//
//
//    fun getAllSingleSale(): Flow<List<SingleSaleEntity>> = singleSaleDao.getAllSingleSale()
//
//
//    fun getSalesByDate(saleDate: String): Flow<List<SingleSaleEntity>> = singleSaleDao.getSalesByDate(saleDate)
//
//
//
//    fun getDailySalesReports(): Flow<List<DailySalesReport>> {
//        return singleSaleDao.getDailySalesReports()
//    }



}