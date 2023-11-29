package com.example.stock_control.ui.home.supplier.dao


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock_control.ui.home.supplier.entities.Supplier
import com.example.stock_control.ui.home.supplier.entities.SupplierCheck
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class SupplierViewModel(private val dao: SupplierDao) : ViewModel() {
    companion object{
        val supplierList = MutableLiveData<MutableList<Supplier>>()
        val supplierCheckList = MutableLiveData<MutableList<SupplierCheck>>()
    }

    fun findSupplierNameFlowable(supplierNameFlowable:String): Disposable {
        val disposable =dao.selectSupplierNameFlowable(supplierNameFlowable)
            .subscribeOn(Schedulers.io())
            .subscribe { suppliers ->
                viewModelScope.launch {
                    supplierList.value = suppliers
                }
            }
        return disposable
    }

    fun findSupplierDeleteFlowable(supplierNameFlowable:String): Disposable {
        val disposable =dao.selectSupplierNameFlowable(supplierNameFlowable)
            .subscribeOn(Schedulers.io())
            .subscribe { suppliers ->
                val supplierCheckListTemp = mutableListOf<SupplierCheck>()
                suppliers.forEach {
                    supplierCheckListTemp.add(SupplierCheck(it))
                }
                viewModelScope.launch {
                    supplierCheckList.value = supplierCheckListTemp
                }
            }
        return disposable
    }

    fun selectAllSupplierCheck(): Disposable{
        val disposable = dao.selectAllSupplierFlowable()
            .subscribeOn(Schedulers.io())
            .subscribe { suppliers ->
                val supplierCheckListTemp = mutableListOf<SupplierCheck>()
                suppliers.forEach {
                    supplierCheckListTemp.add(SupplierCheck(it))
                }
                viewModelScope.launch {
                    supplierCheckList.value = supplierCheckListTemp
                }
            }
        return disposable
    }

    fun selectAllSupplier(): Disposable{
        val disposable =dao.selectAllSupplierFlowable()
            .subscribeOn(Schedulers.io())
            .subscribe { suppliers ->
                viewModelScope.launch {
                    supplierList.value = suppliers
                }
            }
        return disposable
    }


    fun addSupplier(supplierName:String,phoneNumber:Int,email:String) {
        val supplier = Supplier(supplierName, phoneNumber,email)
        dao.insertSupplier(supplier)
    }

    fun updateSupplier(supplierId:Int, supplierName:String,phoneNumber:Int,email:String){
        val supplier = dao.getSupplierById(supplierId)
        supplier.supplierName = supplierName
        supplier.phoneNumber = phoneNumber
        supplier.email = email

        dao.updateSupplier(supplier)
    }

    fun deleteSupplierList(){
        val deletedSupplierList = mutableListOf<Supplier>()
        val updateSupplierList = mutableListOf<Supplier>()
        val checkedSuppliers = supplierCheckList.value?.filter { it.isChecked } ?: return
        val uncheckedSuppliers = supplierCheckList.value?.filterNot { it.isChecked } ?: return

        for (supplierCheck in checkedSuppliers){
            deletedSupplierList.add(supplierCheck.supplier)
        }
        dao.deleteSupplier(deletedSupplierList)

        for (uncheckedSupplier in uncheckedSuppliers){
            updateSupplierList.add(uncheckedSupplier.supplier)
        }
        supplierList.value = updateSupplierList
    }
}