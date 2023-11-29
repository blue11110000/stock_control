package com.example.stock_control.ui.home.product.entities


class ProductCheck(val productDisplay: ProductDisplay) {

    val productId:Int = productDisplay.productId
    val productNumber:String = productDisplay.productNumber
    val productName:String = productDisplay.productName
    val purchasePrice:Int = productDisplay.purchasePrice
    val salePrice:Int = productDisplay.salePrice
    val stockLow: Int = productDisplay.stockLow
    val supplierId :Int = productDisplay.supplierId
    val supplierName :String = productDisplay.supplierName
    var isChecked = false

}