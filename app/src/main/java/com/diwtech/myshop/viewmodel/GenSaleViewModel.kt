package com.diwtech.myshop.viewmodel



import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diwtech.myshop.model.DailySalesReport
import com.diwtech.myshop.model.GenSaleEntity
import com.diwtech.myshop.repository.GenSaleRepository
import com.diwtech.myshop.utils.dateFormated
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class GenSaleViewModel(private val genSaleRepository: GenSaleRepository)  : ViewModel(){

    val currentYearMonth =YearMonth.now().toString() // "2025-05"
    val currentDate =  System.currentTimeMillis()
    val todayDate = dateFormated(currentDate)
    private val _genSale = MutableStateFlow<List<GenSaleEntity>>(emptyList())
    val genSale: StateFlow<List<GenSaleEntity>> = _genSale


    //hold list of single product
    private val _allGenSale = MutableStateFlow<List<GenSaleEntity>>(emptyList())
    val allGenSale: StateFlow<List<GenSaleEntity>> = _allGenSale



    init{
        getTotalSalesToday()
           getMonthlyTotalSales(currentYearMonth)
            getNumberOfMonthlySales(currentYearMonth)
        getNumberOfSalesToday()
    }

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




//    val currentYearMonth = YearMonth.now().toString() // "2025-05"
//private val currentYearMonth: String = YearMonth.now()
//    .format(DateTimeFormatter.ofPattern("yyyy-MM"))

    private val _totalMonthSales = MutableStateFlow(0.0f)
    val totalMonthSales: StateFlow<Float> = _totalMonthSales

    fun getMonthlyTotalSales(currentYearMonth : String) {
        viewModelScope.launch {
            Log.d("GenSaleViewModel", "Fetching monthly sales for: $currentYearMonth")
            genSaleRepository.getMonthlyTotalSales(currentYearMonth).collectLatest { total ->
                _totalMonthSales.value = total
            }
        }
    }




    private val _totalTodaySales = MutableStateFlow(0.0f)
    val totalTodaySales: StateFlow<Float> = _totalTodaySales

    fun getTotalSalesToday() {
        viewModelScope.launch {
            genSaleRepository.getTotalSalesToday(todayDate).collectLatest { total ->
                _totalTodaySales.value = total
            }
        }
    }





    private val _totalNoOfSaleToday = MutableStateFlow(0)
    val totalNoOfSaleToday : StateFlow<Int> = _totalNoOfSaleToday

    fun getNumberOfSalesToday() {
        viewModelScope.launch {
            genSaleRepository.getNumberOfSalesToday(todayDate).collectLatest { total ->
                _totalNoOfSaleToday.value = total
            }
        }
    }


    private val _totalNoOfSaleThisMonth = MutableStateFlow(0)
    val totalNoOfSaleThisMonth : StateFlow<Int> = _totalNoOfSaleThisMonth

    fun getNumberOfMonthlySales(currentYearMonth : String) {
        viewModelScope.launch {
            genSaleRepository.getNumberOfMonthlySales(currentYearMonth).collectLatest { total ->
                _totalNoOfSaleThisMonth.value = total
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