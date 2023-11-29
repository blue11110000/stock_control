package com.example.stock_control.ui.home.supplier.entities


class SupplierCheck(val supplier: Supplier) {

    val supplierId:Int = supplier.supplierId
    val supplierName:String = supplier.supplierName
    val phoneNumber:Int = supplier.phoneNumber
    val email:String = supplier.email
    var isChecked = false

}