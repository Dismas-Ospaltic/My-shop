package com.ditech.myshop.repository



import com.ditech.myshop.data.local.GenSaleDao
import com.ditech.myshop.model.DailySalesReport
import com.ditech.myshop.model.GenSaleEntity
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


    fun getDailySalesReports(): Flow<List<DailySalesReport>> {
        return genSaleDao.getDailySalesReports()
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