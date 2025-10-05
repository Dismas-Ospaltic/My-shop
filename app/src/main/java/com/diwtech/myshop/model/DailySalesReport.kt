package com.diwtech.myshop.model

data class DailySalesReport(
    val date: String,
    val cash: Float,
    val bank: Float,
    val mpesa: Float,
    val other: Float,
    val total: Float
)

data class DailySalesReport1(
    val date: String,
    val saleType: String,
    val total: Double
)
