package com.st11.myshop.utils


import android.content.Context
import com.google.gson.Gson
import com.st11.myshop.model.ProductCategories
import java.io.InputStreamReader

fun loadProductCategoriesFromAssets(context: Context): ProductCategories {
    val inputStream = context.assets.open("categories.json")
    val reader = InputStreamReader(inputStream)
    return Gson().fromJson(reader, ProductCategories::class.java)
}