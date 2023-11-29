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
import com.example.stock_control.databinding.ActivitySupplierRegisterBinding
import com.example.stock_control.ui.home.supplier.constants.DialogSupplier
import com.example.stock_control.ui.home.supplier.dao.SupplierViewModel

class SupplierRegister : AppCompatActivity(){
    private lateinit var binding: ActivitySupplierRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.supplierDao())
            .create(SupplierViewModel::class.java)

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

        binding.supplierRegister.setOnClickListener {
            if (inputProcessor.validateEmpty(ValidationMessage.SUPPLIER_EMPTY_MESSAGE)){
                return@setOnClickListener
            }
            AlertDialog.Builder(this)
                .setTitle(DialogSupplier.REGISTER_TITLE)
                .setMessage(DialogSupplier.REGISTER_MSG)
                .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                    viewModel.addSupplier(
                        binding.supplierName.text.toString(),
                        binding.phoneNumber.text.toString().toInt(),
                        binding.email.text.toString()
                    )
                    inputProcessor.resetRegister()
                }.setNegativeButton(Dialog.BTN_NG,null).show()
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}