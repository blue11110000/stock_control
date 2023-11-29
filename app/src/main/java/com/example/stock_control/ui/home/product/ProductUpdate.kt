package com.example.stock_control.ui.home.product

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.common.InputProcessor
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.constants.Dialog
import com.example.stock_control.constants.ValidationMessage
import com.example.stock_control.databinding.ActivityProductUpdateBinding
import com.example.stock_control.ui.home.product.constants.DialogProduct
import com.example.stock_control.ui.home.product.dao.ProductViewModel

class ProductUpdate : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.productDao())
            .create(ProductViewModel::class.java)
        val productId = intent.getStringExtra("productId")
        val productNumber = intent.getStringExtra("productNumber")
        val productName = intent.getStringExtra("productName")
        val purchasePrice = intent.getStringExtra("purchasePrice")
        val salePrice = intent.getStringExtra("salePrice")
        val stockLow = intent.getStringExtra("stockLow")
        val supplierId = intent.getStringExtra("supplierId")
        var supplierName = intent.getStringExtra("supplierName")
        val bindingList:MutableList<EditText> = mutableListOf(
            binding.productNumber,
            binding.productName,
            binding.supplierId,
            binding.supplierName,
            binding.purchasePrice,
            binding.salePrice,
            binding.stockLow
        )
        val bindingValidateList:MutableList<TextView> = mutableListOf(
            binding.productNumberValidate,
            binding.productNameValidate,
            binding.supplierIdValidate,
            binding.supplierNameValidate,
            binding.purchasePriceValidate,
            binding.salePriceValidate,
            binding.stockLowValidate
        )
        val inputProcessor = InputProcessor(bindingList,bindingValidateList)

        binding.productId.setText(productId)
        binding.productNumber.setText(productNumber)
        binding.productName.setText(productName)
        binding.purchasePrice.setText(purchasePrice)
        binding.salePrice.setText(salePrice)
        binding.stockLow.setText(stockLow)
        binding.supplierId.setText(supplierId)
        binding.supplierName.setText(supplierName)

        binding.supplierId.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                return@setOnFocusChangeListener
            }
            if(binding.supplierId.text.toString().isBlank()){
                return@setOnFocusChangeListener
            }

            supplierName =viewModel.getSupplierName(binding.supplierId.text.toString().toInt())

            if (supplierName != null){
                binding.supplierName.setText(supplierName)
            }else{
                AlertDialog.Builder(this)
                    .setTitle(Dialog.SUPPLIER_SEARCH_ERROR_TITLE)
                    .setMessage(Dialog.SUPPLIER_SEARCH_ERROR_MESSAGE)
                    .setPositiveButton(Dialog.BTN_OK,null)
                    .setNegativeButton(Dialog.BTN_NG,null).show()
            }
        }

        binding.productUpdate.setOnClickListener {
            if (inputProcessor.validateEmpty(ValidationMessage.PRODUCT_EMPTY_MESSAGE)){
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle(DialogProduct.UPDATE_TITLE)
                .setMessage(DialogProduct.UPDATE_MSG)
                .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                    viewModel.updateProduct(
                        productId!!.toInt(),
                        binding.productNumber.text.toString(),
                        binding.productName.text.toString(),
                        binding.purchasePrice.text.toString().toInt(),
                        binding.salePrice.text.toString().toInt(),
                        binding.stockLow.text.toString().toInt(),
                        binding.supplierId.text.toString().toInt())

                    finish()
                }.setNegativeButton(Dialog.BTN_NG,null).show()
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}