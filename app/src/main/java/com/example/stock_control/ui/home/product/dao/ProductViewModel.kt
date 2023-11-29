package com.example.stock_control.ui.home.product.dao



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock_control.ui.home.product.entities.Product
import com.example.stock_control.ui.home.product.entities.ProductCheck
import com.example.stock_control.ui.home.product.entities.ProductDisplay
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class ProductViewModel(private val dao: ProductDao) : ViewModel() {
    companion object{

        val productList = MutableLiveData<MutableList<ProductDisplay>>()
        val productCheckList = MutableLiveData<MutableList<ProductCheck>>()
    }

    fun findProductNameFlowable(productNameFlowable:String): Disposable {
        val disposable =dao.selectProductNameFlowable(productNameFlowable)
            .subscribeOn(Schedulers.io())
            .subscribe { products ->
                viewModelScope.launch {
                    productList.value = products
                }
            }
        return disposable
    }

    fun findProductDeleteFlowable(productNameFlowable:String): Disposable {
        val disposable =dao.selectProductNameFlowable(productNameFlowable)
            .subscribeOn(Schedulers.io())
            .subscribe { products ->
                val productCheckListTemp = mutableListOf<ProductCheck>()
                products.forEach {
                    productCheckListTemp.add(ProductCheck(it))
                }
                viewModelScope.launch {
                    productCheckList.value = productCheckListTemp
                }
            }
        return disposable
    }

    fun selectAllProductCheck(): Disposable{
        val disposable = dao.selectAllProductFlowable()
            .subscribeOn(Schedulers.io())
            .subscribe { products ->
                val productCheckListTemp = mutableListOf<ProductCheck>()
                products.forEach {
                    productCheckListTemp.add(ProductCheck(it))
                }
                viewModelScope.launch {
                    productCheckList.value = productCheckListTemp
                }
            }
        return disposable
    }

    fun selectAllProduct(): Disposable{
        val disposable =dao.selectAllProductFlowable()
            .subscribeOn(Schedulers.io())
            .subscribe { products ->
                viewModelScope.launch {
                    productList.value = products
                }
            }
        return disposable
    }

    fun getSupplierName(supplierId:Int):String?{
        return dao.getSupplierNameById(supplierId)
    }

    fun addProduct(productNumber:String,productName:String,purchasePrice:Int,salePrice:Int,stockLow:Int,supplierId:Int) {
        val product = Product(productNumber, productName, purchasePrice,salePrice,stockLow,supplierId)
        dao.insertProduct(product)
    }

    fun updateProduct(productId:Int, productNumber:String,
                      productName:String,purchasePrice:Int,salePrice:Int,stockLow: Int,supplierId:Int){
        val product = dao.getProductById(productId)
        product!!.productNumber = productNumber
        product.productName = productName
        product.purchasePrice = purchasePrice
        product.salePrice = salePrice
        product.stockLow = stockLow
        product.supplierId = supplierId

        dao.updateProduct(product)
    }

    fun deleteProductList(){
        val deletedProductList = mutableListOf<Product>()
        val updateProductList = mutableListOf<ProductDisplay>()
        val checkedProducts = productCheckList.value?.filter { it.isChecked } ?: return
        val uncheckedProducts = productCheckList.value?.filterNot { it.isChecked } ?: return

        for (productCheck in checkedProducts){
            val product = Product(productCheck.productNumber,productCheck.productName,
                productCheck.purchasePrice,productCheck.salePrice,productCheck.stockLow,0)
            product.productId = productCheck.productId
            deletedProductList.add(product)
        }
        dao.deleteProduct(deletedProductList)

        for (uncheckedProduct in uncheckedProducts){
            updateProductList.add(uncheckedProduct.productDisplay)
        }
        productList.value = updateProductList
    }
}