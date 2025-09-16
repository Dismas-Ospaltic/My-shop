package com.ditech.myshop.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ditech.myshop.model.Sales
import com.ditech.myshop.model.SingleProductSaleEntity
import com.ditech.myshop.repository.SingleProductSaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SingleProductSaleViewModel(private val singleProductSaleRepository: SingleProductSaleRepository) : ViewModel() {


    //hold list of single product
    private val _singleProduct = MutableStateFlow<List<SingleProductSaleEntity>>(emptyList())
    val singleProduct: StateFlow<List<SingleProductSaleEntity>> = _singleProduct
    fun insertSingleProducts(singleProductSaleEntity: SingleProductSaleEntity) {
        viewModelScope.launch {
            singleProductSaleRepository.insertSoldProducts(singleProductSaleEntity)
        }
    }

    fun updateSingleProducts(singleProductSaleEntity: SingleProductSaleEntity) {
        viewModelScope.launch {
            singleProductSaleRepository.updateSoldProducts(singleProductSaleEntity)
        }
    }




//    init {
//        getSingleSalesByDate()
//    }

    /**
     * Fetch all watchlist
     */
    private fun getSingleSalesByDate(saleDate: String) {
        viewModelScope.launch {
            singleProductSaleRepository.getSingleSalesByDate(saleDate).collectLatest { products ->
                _singleProduct.value = products
            }
        }
    }



    private val _salesSummary = MutableStateFlow<List<Sales>>(emptyList())
    val salesSummary: StateFlow<List<Sales>> = _salesSummary.asStateFlow()

    private val _productsForReceipt = MutableStateFlow<List<SingleProductSaleEntity>>(emptyList())
    val productsForReceipt: StateFlow<List<SingleProductSaleEntity>> = _productsForReceipt.asStateFlow()

    fun loadSalesByDate(date: String) {
        viewModelScope.launch {
            singleProductSaleRepository.getSingleSalesSummaryByDate(date).collect {
                _salesSummary.value = it
            }
        }
    }

    fun loadProductsByReceipt(receipt: String) {
        viewModelScope.launch {
            singleProductSaleRepository.getProductsSoldByReceipt(receipt).collect {
                _productsForReceipt.value = it
            }
        }
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
//    fun getDailySalesReports(): Flow<List<DailySalesReport>> {
//        return singleSaleDao.getDailySalesReports()
//    }


}