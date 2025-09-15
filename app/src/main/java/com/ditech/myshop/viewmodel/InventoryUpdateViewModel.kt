package com.ditech.myshop.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ditech.myshop.model.InventoryUpdateEntity
import com.ditech.myshop.model.ProductEntity
import com.ditech.myshop.repository.InventoryUpdateRepository
import com.ditech.myshop.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InventoryUpdateViewModel(private val inventoryUpdateRepository: InventoryUpdateRepository) : ViewModel() {

    val allInventoryUpdates: StateFlow<List<InventoryUpdateEntity>> =
        inventoryUpdateRepository.getAllInventoryUpdates()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //hold list of single product
    private val _allInventoryUpdatesByProductId = MutableStateFlow<List<InventoryUpdateEntity>>(emptyList())
    val allInventoryUpdatesByProductId: StateFlow<List<InventoryUpdateEntity>> = _allInventoryUpdatesByProductId

    fun insertInventoryUpdate(inventoryUpdateEntity: InventoryUpdateEntity) {
        viewModelScope.launch {
            inventoryUpdateRepository.insertInventoryUpdate(inventoryUpdateEntity)
        }
    }

//    fun getInventoryUpdatesByProductId(productId: String): Flow<List<InventoryUpdateEntity>> {
//        return inventoryUpdateRepository.getInventoryUpdatesByProductId(productId)
//            .map { inventoryUpdates ->
//                _allInventoryUpdatesByProductId.value = inventoryUpdates
//                inventoryUpdates
//            }
//    }

    fun getInventoryUpdatesByProductId(productId: String) {
        viewModelScope.launch {
            inventoryUpdateRepository.getInventoryUpdatesByProductId(productId).collectLatest { productsUpdate ->
                _allInventoryUpdatesByProductId.value = productsUpdate
            }
        }
    }

    fun getInventoryUpdatesByDate(date: String): Flow<List<InventoryUpdateEntity>> {
        return inventoryUpdateRepository.getInventoryUpdatesByDate(date)
    }







//    // Active products
//    val activeProducts: StateFlow<List<ProductEntity>> =
//        productRepository.getAllProducts()
//            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
//
//    // Inactive (soft deleted) products
//    val inactiveProducts: StateFlow<List<ProductEntity>> =
//        productRepository.getInactiveProducts()
//            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
//
//    // Low stock products
//    val lowStockProducts: StateFlow<List<ProductEntity>> =
//        productRepository.getLowStockProducts()
//            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
//
//    fun insertProduct(product: ProductEntity) {
//        viewModelScope.launch {
//            productRepository.insertProduct(product)
//        }
//    }
//
//    fun updateProduct(product: ProductEntity) {
//        viewModelScope.launch {
//            productRepository.updateProduct(product)
//        }
//    }
//
//    fun updateProductDetailsById(
//        productId: String,
//        productCode: String,
//        productName: String,
//        productQuantity: Int,
//        buyPrice: Float,
//        sellPrice: Float,
//        manufactureDate: String?,
//        expiryDate: String?,
//        productCategory: String
//    ) {
//        viewModelScope.launch {
//            productRepository.updateProductDetailsById(
//                productId,
//                productCode,
//                productName,
//                productQuantity,
//                buyPrice,
//                sellPrice,
//                manufactureDate,
//                expiryDate,
//                productCategory
//            )
//        }
//    }
//
//    fun deleteProductById(productId: String) {
//        viewModelScope.launch {
//            productRepository.deleteProductById(productId)
//        }
//    }
//
//    fun updateProductQuantityById(productId: String, newQuantity: Int) {
//        viewModelScope.launch {
//            productRepository.updateProductQuantityById(productId, newQuantity)
//        }
//    }


}