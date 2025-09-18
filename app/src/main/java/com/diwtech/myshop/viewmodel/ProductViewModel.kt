package com.diwtech.myshop.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diwtech.myshop.model.ProductEntity
import com.diwtech.myshop.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    fun updateProductQuantityById(productId: String, newQuantity: Int) {
        viewModelScope.launch {
            productRepository.updateProductQuantityById(productId, newQuantity)
        }
    }


}