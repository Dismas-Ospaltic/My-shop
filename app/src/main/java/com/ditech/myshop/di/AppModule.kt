package com.ditech.myshop.di


import com.ditech.myshop.data.local.AppDatabase
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


    single { AppDatabase.getDatabase(androidContext()).productDao() }


    single { AppDatabase.getDatabase(androidContext()).singleProductSaleDao() }

}