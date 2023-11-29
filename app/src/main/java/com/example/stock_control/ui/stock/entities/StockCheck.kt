package com.example.stock_control.ui.stock.entities


class StockCheck (val stockDisplay: StockDisplay) {

    val stockId:Int = stockDisplay.stockId
    val productId:Int = stockDisplay.productId
    val productNumber: String = stockDisplay.productNumber
    val stockBound:Int = stockDisplay.stockBound
    val productName:String = stockDisplay.productName
    val stockDate: String = stockDisplay.stockDate
    val stockQuantity:Int = stockDisplay.stockQuantity
    var isChecked = false
}