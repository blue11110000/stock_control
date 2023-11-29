package com.example.stock_control.common

import android.widget.EditText
import android.widget.TextView
import com.example.stock_control.constants.ValidationMessage


class InputProcessor(private val bindingList: MutableList<EditText>, private val bindingValidateList: MutableList<TextView>){

    fun validateEmpty(validateMessageList:List<String>):Boolean{
        var saveFlag = false
        bindingList.forEachIndexed { index, binding ->
            if (binding.text.isEmpty() || binding.text.isBlank()){
                bindingValidateList[index].text = validateMessageList[index]
                saveFlag = true
                return@forEachIndexed
            }
            bindingValidateList[index].text = ValidationMessage.EMPTY
        }
        return saveFlag
    }

    fun resetRegister(){
        bindingList.forEach{ binding ->
            binding.setText(ValidationMessage.EMPTY)
        }
    }
}
