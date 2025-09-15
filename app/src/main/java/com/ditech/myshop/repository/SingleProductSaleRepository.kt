package com.ditech.myshop.repository



import com.ditech.myshop.data.local.GenSaleDao
import com.ditech.myshop.data.local.SingleProductSaleDao
import com.ditech.myshop.model.Sales
import com.ditech.myshop.model.SingleProductSaleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SingleProductSaleRepository(private val singleProductSaleDao: SingleProductSaleDao) {


    suspend fun insertSoldProducts(singleProductSaleEntity: SingleProductSaleEntity) {
        singleProductSaleDao.insertSoldProducts(singleProductSaleEntity)
    }

    suspend fun updateSoldProducts(singleProductSaleEntity: SingleProductSaleEntity) {
        singleProductSaleDao.updateSoldProducts(singleProductSaleEntity)
    }

    fun getSingleSalesByDate(saleDate: String): Flow<List<SingleProductSaleEntity>> = singleProductSaleDao.getAllSingleSalesByDate(saleDate)


    fun getSingleSalesSummaryByDate(date: String): Flow<List<SingleProductSaleEntity>> {
        return singleProductSaleDao.getSingleSalesSummaryByDate(date)
    }

    fun getProductsSoldByReceipt(receipt: String): Flow<List<SingleProductSaleEntity>> {
        return singleProductSaleDao.getProductsSoldByReceipt(receipt)
    }




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