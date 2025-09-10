package com.ditech.myshop.viewmodel


import androidx.lifecycle.ViewModel
import com.ditech.myshop.repository.SingleProductSaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SingleProductSaleViewModel(private val singleProductSaleRepository: SingleProductSaleRepository) : ViewModel() {

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