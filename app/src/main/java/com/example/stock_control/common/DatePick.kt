package com.example.stock_control.common

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.stock_control.R
import java.util.Calendar

class DatePick(private val binding: EditText) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(this.context as Context, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val str: String = getString(R.string.string_format, year, month+1, day)
        binding.setText(str)

    }
}