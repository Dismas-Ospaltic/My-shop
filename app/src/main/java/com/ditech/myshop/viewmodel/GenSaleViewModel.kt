package com.ditech.myshop.viewmodel



import androidx.lifecycle.ViewModel
import com.ditech.myshop.repository.GenSaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenSaleViewModel(private val genSaleRepository: GenSaleRepository)  : ViewModel(){

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
//    fun getDailySalesReports(): Flow<List<DailySalesReport>> {
//        return singleSaleDao.getDailySalesReports()
//    }


}