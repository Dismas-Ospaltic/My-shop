package com.st11.myshop.model

import com.google.gson.annotations.SerializedName

data class ProductCategories(
    @SerializedName("ProductCategories")
    val productCategories: List<Category>
)

data class Category(
    @SerializedName("category") // JSON key is "category", Kotlin property is "type"
    val type: String
)
