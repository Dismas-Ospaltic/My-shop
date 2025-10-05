package com.diwtech.myshop.di


import com.diwtech.myshop.data.local.AppDatabase
import com.diwtech.myshop.repository.GenSaleRepository
import com.diwtech.myshop.repository.InventoryUpdateRepository
import com.diwtech.myshop.repository.ProductRepository
import com.diwtech.myshop.repository.SingleProductSaleRepository
import com.diwtech.myshop.viewmodel.GenSaleViewModel
import com.diwtech.myshop.viewmodel.InventoryUpdateViewModel
import com.diwtech.myshop.viewmodel.ProductViewModel
import com.diwtech.myshop.viewmodel.SingleProductSaleViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel



val appModule = module {


    single { AppDatabase.getDatabase(androidContext()).genSaleDao() }
    single { GenSaleRepository(get()) }
    viewModel { GenSaleViewModel(get()) }

    single { AppDatabase.getDatabase(androidContext()).productDao() }
     single { ProductRepository(get()) }
      viewModel { ProductViewModel(get()) }

    single { AppDatabase.getDatabase(androidContext()).singleProductSaleDao() }
     single { SingleProductSaleRepository(get()) }
     viewModel { SingleProductSaleViewModel(get()) }

    single { AppDatabase.getDatabase(androidContext()).inventoryUpdateDao() }
     single { InventoryUpdateRepository(get()) }
     viewModel { InventoryUpdateViewModel(get()) }

}