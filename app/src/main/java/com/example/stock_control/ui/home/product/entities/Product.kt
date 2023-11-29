package com.example.stock_control.ui.home.product.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
class Product(
    var productNumber:String,
    var productName:String,
    var purchasePrice: Int,
    var salePrice:Int,
    var stockLow: Int,
    var supplierId: Int) {
    @PrimaryKey(autoGenerate = true) var productId:Int =0
}