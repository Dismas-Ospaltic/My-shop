package com.diwtech.myshop.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diwtech.myshop.model.ProductEntity
import com.diwtech.myshop.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {




    // Active products
    val activeProducts: StateFlow<List<ProductEntity>> =
        productRepository.getAllProducts()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Inactive (soft deleted) products
    val inactiveProducts: StateFlow<List<ProductEntity>> =
        productRepository.getInactiveProducts()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Low stock products
    val lowStockProducts: StateFlow<List<ProductEntity>> =
        productRepository.getLowStockProducts()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertProduct(product: ProductEntity) {
        viewModelScope.launch {
            productRepository.insertProduct(product)
        }
    }

    fun updateProduct(product: ProductEntity) {
        viewModelScope.launch {
            productRepository.updateProduct(product)
        }
    }

    fun updateProductDetailsById(
        productId: String,
        productCode: String,
        productName: String,
        productQuantity: Int,
        buyPrice: Float,
        sellPrice: Float,
        manufactureDate: String?,
        expiryDate: String?,
        productCategory: String
    ) {
        viewModelScope.launch {
            productRepository.updateProductDetailsById(
                productId,
                productCode,
                productName,
                productQuantity,
                buyPrice,
                sellPrice,
                manufactureDate,
                expiryDate,
                productCategory
            )
        }
    }

    fun deleteProductById(productId: String) {
        viewModelScope.launch {
            productRepository.deleteProductById(productId)
        }
    }


    fun restoreProductById(productId: String) {
        viewModelScope.launch {
            productRepository.restoreProductById(productId)
        }
    }

    fun hardDeleteProductById(productId: String) {
        viewModelScope.launch {
            productRepository.hardDeleteProductById(productId)
        }
    }

    fun updateProductQuantityById(productId: String, newQuantity: Int) {
        viewModelScope.launch {
            productRepository.updateProductQuantityById(productId, newQuantity)
        }
    }

    private val _totalNoActiveProduct = MutableStateFlow(0)
    val totalNoActiveProduct : StateFlow<Int> = _totalNoActiveProduct

    fun getAllActiveProductNumber() {
        viewModelScope.launch {
            productRepository.getAllActiveProductNumber().collectLatest { total ->
                _totalNoActiveProduct.value = total
            }
        }
    }



    private val _totalNoActiveLowStockProduct = MutableStateFlow(0)
    val totalNoActiveLowStockProduct : StateFlow<Int> = _totalNoActiveLowStockProduct

    fun getAllActiveProductLowStock() {
        viewModelScope.launch {
            productRepository.getAllActiveProductLowStock().collectLatest { total ->
                _totalNoActiveLowStockProduct.value = total
            }
        }
    }

//


}