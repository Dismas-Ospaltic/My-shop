package com.ditech.myshop.di


import com.ditech.myshop.data.local.AppDatabase
import com.ditech.myshop.repository.GenSaleRepository
import com.ditech.myshop.repository.InventoryUpdateRepository
import com.ditech.myshop.repository.ProductRepository
import com.ditech.myshop.repository.SingleProductSaleRepository
import com.ditech.myshop.viewmodel.GenSaleViewModel
import com.ditech.myshop.viewmodel.InventoryUpdateViewModel
import com.ditech.myshop.viewmodel.ProductViewModel
import com.ditech.myshop.viewmodel.SingleProductSaleViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {

//    single{ AppDatabase.getDatabase(get()).singleSaleProductDao() }
//    single { SingleProductRepository(get()) }
//    viewModel { SingleProductSaleViewModel(get()) }
//
//
//    single { AppDatabase.getDatabase(get()).singleSaleDao() }
//    single { SingleSaleRepository(get()) }
//    viewModel { SingleSaleViewModel(get()) }

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