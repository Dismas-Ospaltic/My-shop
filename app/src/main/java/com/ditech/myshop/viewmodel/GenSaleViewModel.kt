package com.ditech.myshop.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ditech.myshop.model.DailySalesReport
import com.ditech.myshop.model.GenSaleEntity
import com.ditech.myshop.repository.GenSaleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GenSaleViewModel(private val genSaleRepository: GenSaleRepository)  : ViewModel(){

    private val _genSale = MutableStateFlow<List<GenSaleEntity>>(emptyList())
    val genSale: StateFlow<List<GenSaleEntity>> = _genSale


    //hold list of single product
    private val _allGenSale = MutableStateFlow<List<GenSaleEntity>>(emptyList())
    val allGenSale: StateFlow<List<GenSaleEntity>> = _allGenSale



    fun insertGenSale(genSaleEntity: GenSaleEntity) {
        viewModelScope.launch {
//            singleProductRepository.insertSoldProduct(singleProductEntity)
            genSaleRepository.insertGenSale(genSaleEntity)
        }
    }

    fun genSaleEntity(genSaleEntity: GenSaleEntity) {
        viewModelScope.launch {
//            singleProductRepository.updateSoldProduct(singleProductEntity)
            genSaleRepository.updateGenSale(genSaleEntity)
        }
    }


//    init {
//        getSingleSalesByDate()
//    }

    /**
     * Fetch all watchlist
     */
    private fun getAllGenSale() {
        viewModelScope.launch {
            genSaleRepository.getAllGenSale().collectLatest { products ->
                _allGenSale.value = products
            }
        }
    }




    fun getGenSalesByDate(saleDate: String) {
        viewModelScope.launch {
            genSaleRepository.getGenSalesByDate(saleDate).collectLatest { products ->
                _genSale.value = products
            }
        }
    }




    // StateFlow to expose daily sales reports
    private val _dailySalesReports = MutableStateFlow<List<DailySalesReport>>(emptyList())
    val dailySalesReports: StateFlow<List<DailySalesReport>> = _dailySalesReports.asStateFlow()

    init {
        // Start collecting reports immediately when ViewModel is created
        viewModelScope.launch {
            genSaleRepository.getDailySalesReports()
                .collect { reports ->
                    _dailySalesReports.value = reports
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