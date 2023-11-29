package com.example.stock_control.ui.home.supplier.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.stock_control.ui.home.supplier.entities.Supplier
import io.reactivex.Flowable
@Dao
interface SupplierDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSupplier(supplier: Supplier)

    @Query("SELECT * FROM supplier where supplierName like '%' || :supplierName || '%'")
    fun selectSupplierNameFlowable(supplierName:String): Flowable<MutableList<Supplier>>

    @Query("SELECT * FROM supplier")
    fun selectAllSupplierFlowable(): Flowable<MutableList<Supplier>>

    @Query("SELECT * FROM supplier where supplierId = :supplierId")
    fun getSupplierById(supplierId:Int): Supplier

    @Update
    fun updateSupplier(supplier: Supplier)

    @Delete
    fun deleteSupplier(supplierList: MutableList<Supplier>)
}