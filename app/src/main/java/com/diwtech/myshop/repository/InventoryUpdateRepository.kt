package com.diwtech.myshop.repository



import com.diwtech.myshop.data.local.InventoryUpdateDao
import com.diwtech.myshop.model.InventoryUpdateEntity
import kotlinx.coroutines.flow.Flow

class InventoryUpdateRepository(private val inventoryUpdateDao: InventoryUpdateDao) {

    suspend fun insertInventoryUpdate(inventoryUpdateEntity: InventoryUpdateEntity) {
        inventoryUpdateDao.insertInventoryUpdate(inventoryUpdateEntity)
    }

    fun getAllInventoryUpdates(): Flow<List<InventoryUpdateEntity>> {
        return inventoryUpdateDao.getAllInventoryUpdates()
    }

    fun getInventoryUpdatesByProductId(productId: String): Flow<List<InventoryUpdateEntity>> {
        return inventoryUpdateDao.getInventoryUpdatesByProductId(productId)
    }

    fun getInventoryUpdatesByDate(date: String): Flow<List<InventoryUpdateEntity>> {
        return inventoryUpdateDao.getInventoryUpdatesByDate(date)
    }




//    suspend fun insertProduct(product: ProductEntity) {
//        productDao.insertProduct(product)
//    }
//
//    fun getAllProducts(): Flow<List<ProductEntity>> {
//        return productDao.getAllProducts()
//    }
//
//    suspend fun updateProduct(product: ProductEntity) {
//        productDao.updateProduct(product)
//    }
//
//    suspend fun updateProductDetailsById(
//        productId: String,
//        productCode: String,
//        productName: String,
//        productQuantity: Int,
//        buyPrice: Float,
//        sellPrice: Float,
//        manufactureDate: String?,
//        expiryDate: String?,
//        productCategory: String
//    ): Int? {
//        return productDao.updateProductDetailsById(
//            productId,
//            productCode,
//            productName,
//            productQuantity,
//            buyPrice,
//            sellPrice,
//            manufactureDate,
//            expiryDate,
//            productCategory
//        )
//    }
//
//    suspend fun deleteProductById(productId: String): Int? {
//        return productDao.deleteProductById(productId)
//    }
//
//    fun getInactiveProducts(): Flow<List<ProductEntity>> {
//        return productDao.getInactiveProducts()
//    }
//
//    suspend fun updateProductQuantityById(productId: String, newQuantity: Int): Int? {
//        return productDao.updateProductQuantityById(productId, newQuantity)
//    }
//
//    fun getLowStockProducts(): Flow<List<ProductEntity>> {
//        return productDao.getLowStockProducts()
//    }


}