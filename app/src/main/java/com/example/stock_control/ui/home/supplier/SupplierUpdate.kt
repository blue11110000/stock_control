package com.example.stock_control.ui.home.supplier

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
import com.example.stock_control.databinding.ActivitySupplierUpdateBinding
import com.example.stock_control.ui.home.supplier.constants.DialogSupplier
import com.example.stock_control.ui.home.supplier.dao.SupplierViewModel


class SupplierUpdate : AppCompatActivity(){
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySupplierUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.supplierDao())
            .create(SupplierViewModel::class.java)
        val supplierId = intent.getStringExtra("supplierId")
        val supplierName = intent.getStringExtra("supplierName")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val email = intent.getStringExtra("email")
        val bindingList:MutableList<EditText> = mutableListOf(
            binding.supplierName,
            binding.phoneNumber,
            binding.email
        )
        val bindingValidateList:MutableList<TextView> = mutableListOf(
            binding.supplierNameValidate,
            binding.phoneNumberValidate,
            binding.emailValidate
        )
        val inputProcessor = InputProcessor(bindingList,bindingValidateList)
        binding.supplierId.setText(supplierId)
        binding.supplierName.setText(supplierName)
        binding.phoneNumber.setText(phoneNumber)
        binding.email.setText(email)

        binding.supplierUpdate.setOnClickListener {
            if (inputProcessor.validateEmpty(ValidationMessage.SUPPLIER_EMPTY_MESSAGE)){
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle(DialogSupplier.UPDATE_TITLE)
                .setMessage(DialogSupplier.UPDATE_MSG)
                .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                    viewModel.updateSupplier(
                        supplierId!!.toInt(),
                        binding.supplierName.text.toString(),
                        binding.phoneNumber.text.toString().toInt(),
                        binding.email.text.toString()
                    )

                    finish()
                }
                .setNegativeButton(Dialog.BTN_NG, null).show()
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}