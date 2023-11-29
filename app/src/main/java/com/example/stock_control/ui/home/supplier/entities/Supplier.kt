package com.example.stock_control.ui.home.supplier.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supplier")
class Supplier(
    var supplierName:String,
    var phoneNumber: Int,
    var email:String) {
    @PrimaryKey(autoGenerate = true) var supplierId:Int =0
}