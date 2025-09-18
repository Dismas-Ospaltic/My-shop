package com.ditech.myshop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ditech.myshop.model.GenSaleEntity
import com.ditech.myshop.model.ProductEntity
import com.ditech.myshop.model.SingleProductSaleEntity
import com.ditech.myshop.model.InventoryUpdateEntity


@Database(entities = [GenSaleEntity::class, SingleProductSaleEntity::class, ProductEntity::class, InventoryUpdateEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genSaleDao(): GenSaleDao
    abstract fun productDao(): ProductDao
    abstract fun singleProductSaleDao(): SingleProductSaleDao
    abstract fun inventoryUpdateDao(): InventoryUpdateDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_shop_sales_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}