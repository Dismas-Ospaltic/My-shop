package com.ditech.myshop.repository





import com.ditech.myshop.data.local.ProductDao
import com.ditech.myshop.model.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(private val productDao: ProductDao) {


    suspend fun insertProduct(product: ProductEntity) {
        productDao.insertProduct(product)
    }

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    suspend fun updateProductDetailsById(
        productId: String,
        productCode: String,
        productName: String,
        productQuantity: Int,
        buyPrice: Float,
        sellPrice: Float,
        manufactureDate: String?,
        expiryDate: String?,
        productCategory: String
    ): Int? {
        return productDao.updateProductDetailsById(
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

    suspend fun deleteProductById(productId: String): Int? {
        return productDao.deleteProductById(productId)
    }

    fun getInactiveProducts(): Flow<List<ProductEntity>> {
        return productDao.getInactiveProducts()
    }

    suspend fun updateProductQuantityById(productId: String, newQuantity: Int): Int? {
        return productDao.updateProductQuantityById(productId, newQuantity)
    }

    fun getLowStockProducts(): Flow<List<ProductEntity>> {
        return productDao.getLowStockProducts()
    }


}