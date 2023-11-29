package com.example.stock_control.ui.stock

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.common.DatePick
import com.example.stock_control.common.InputProcessor
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.constants.Dialog
import com.example.stock_control.constants.ValidationMessage
import com.example.stock_control.databinding.ActivityStockUpdateBinding
import com.example.stock_control.ui.stock.constants.DialogStockIn
import com.example.stock_control.ui.stock.constants.DialogStockOut
import com.example.stock_control.ui.stock.constants.StockConstants
import com.example.stock_control.ui.stock.dao.StockViewModel

class StockUpdate : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStockUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.stockDao())
            .create(StockViewModel::class.java)
        val stockId = intent.getStringExtra("stockId")
        val productId = intent.getStringExtra("productId")
        val stockBound = intent.getStringExtra("stockBound")
        val productNumber = intent.getStringExtra("productNumber")
        val productName = intent.getStringExtra("productName")
        val stockDate = intent.getStringExtra("stockDate")
        val stockQuantity = intent.getStringExtra("stockQuantity")
        val bindingList:MutableList<EditText> = mutableListOf(
            binding.productId,
            binding.productNumber,
            binding.productName,
            binding.stockDate,
            binding.stockQuantity,
        )
        val bindingValidateList:MutableList<TextView> = mutableListOf(
            binding.productIdValidate,
            binding.productNumberValidate,
            binding.productNameValidate,
            binding.stockDateValidate,
            binding.stockQuantityValidate,
        )
        binding.productId.setText(productId)
        binding.productNumber.setText(productNumber)
        binding.productName.setText(productName)
        binding.stockDate.setText(stockDate)
        binding.stockQuantity.setText(stockQuantity)

        val inputProcessor = InputProcessor(bindingList,bindingValidateList)

        binding.productId.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                return@setOnFocusChangeListener
            }
            if(binding.productId.text.toString().isBlank()){
                return@setOnFocusChangeListener
            }
            val product =viewModel.getProduct(binding.productId.text.toString().toInt())
            if (product != null){
                binding.productNumber.setText(product.productNumber)
                binding.productName.setText(product.productName)
            }else{
                AlertDialog.Builder(this)
                    .setTitle(Dialog.PRODUCT_SEARCH_ERROR_TITLE)
                    .setMessage(Dialog.PRODUCT_SEARCH_ERROR_MESSAGE)
                    .setPositiveButton(Dialog.BTN_OK,null)
                    .setNegativeButton(Dialog.BTN_NG,null).show()
            }
        }

        binding.stockDate.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                binding.stockDate.clearFocus()
                val newFragment = DatePick(binding.stockDate)
                newFragment.show(supportFragmentManager, "datePicker")
            }
        }

        binding.stockUpdate.setOnClickListener {
            if (inputProcessor.validateEmpty(ValidationMessage.STOCK_EMPTY_MESSAGE)){
                return@setOnClickListener
            }

            if (stockBound!!.toInt() == StockConstants.STOCK_IN_BOUND){
                AlertDialog.Builder(this)
                    .setTitle(DialogStockIn.STOCK_IN_UPDATE_TITLE)
                    .setMessage(DialogStockIn.STOCK_IN_UPDATE_MSG)
                    .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                        viewModel.updateStockIn(
                            stockId!!.toInt(),
                            binding.productId.text.toString().toInt(),
                            stockBound.toInt(),
                            binding.stockDate.text.toString(),
                            binding.stockQuantity.text.toString().toInt())
                        finish()
                    }
                    .setNegativeButton(Dialog.BTN_NG,null).show()
            }else if (stockBound.toInt() == StockConstants.STOCK_OUT_BOUND){
                AlertDialog.Builder(this)
                    .setTitle(DialogStockOut.STOCK_OUT_UPDATE_TITLE)
                    .setMessage(DialogStockOut.STOCK_OUT_UPDATE_MSG)
                    .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                        if (viewModel.isUpdateRequired(binding.productId.text.toString().toInt(),
        binding.stockQuantity.text.toString().toInt()- stockQuantity!!.toInt())){
                            AlertDialog.Builder(this)
                                .setTitle(DialogStockOut.STOCK_OUT_REGISTER_ERROR_TITLE)
                                .setMessage(DialogStockOut.STOCK_OUT_REGISTER_ERROR_MSG)
                                .setPositiveButton(Dialog.BTN_OK,null).setNegativeButton(Dialog.BTN_NG,null).show()
                            return@setPositiveButton
                        }
                        viewModel.updateStockOut(
                            stockId!!.toInt(),
                            binding.productId.text.toString().toInt(),
                            stockBound.toInt(),
                            binding.stockDate.text.toString(),
                            binding.stockQuantity.text.toString().toInt())
                            finish()

                    }
                    .setNegativeButton(Dialog.BTN_NG,null).show()
            }
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}