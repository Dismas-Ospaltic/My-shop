package com.diwtech.myshop.repository



import com.diwtech.myshop.data.local.GenSaleDao
import com.diwtech.myshop.model.DailySalesReport
import com.diwtech.myshop.model.DailySalesReport1
import com.diwtech.myshop.model.GenSaleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenSaleRepository(private val genSaleDao: GenSaleDao) {


    suspend fun insertGenSale(genSaleEntity: GenSaleEntity) {
        genSaleDao.insertGenSale(genSaleEntity)
    }

    suspend fun updateGenSale(genSaleEntity: GenSaleEntity) {
        genSaleDao.updateGenSale(genSaleEntity)
    }


    fun getAllGenSale(): Flow<List<GenSaleEntity>> = genSaleDao.getAllGenSale()


    fun getGenSalesByDate(saleDate: String): Flow<List<GenSaleEntity>> = genSaleDao.getGenSalesByDate(saleDate)


    fun getDailySalesReports(): Flow<List<DailySalesReport1>> {
        return genSaleDao.getDailySalesReports()
    }


    fun getTotalSalesToday(todayDate: String): Flow<Float> {
        return genSaleDao.getTotalSalesToday(todayDate)
            .map { total -> total ?: 0.0f }  // Convert NULL to 0.0
    }

    fun getMonthlyTotalSales(month: String): Flow<Float> {
        return  genSaleDao.getMonthlyTotalSales(month)
            .map { total -> total ?: 0.0f }  // Convert NULL to 0.0
    }


    fun getNumberOfMonthlySales(month: String): Flow<Int> {
        return genSaleDao.getNumberOfMonthlySales(month)
            .map { total -> total ?: 0 }  // Convert NULL to 0
    }

    fun getNumberOfSalesToday(todayDate: String): Flow<Int> {
        return genSaleDao.getNumberOfSalesToday(todayDate)
            .map { total -> total ?: 0 }  // Convert NULL to 0
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