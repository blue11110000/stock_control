package com.example.stock_control.ui.stock.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock")
class Stock (
    var productId: Int,
    var stockBound:Int,
    var stockDate: String,
    var stockQuantity:Int) {
    @PrimaryKey(autoGenerate = true) var stockId:Int =0
}