package com.example.agetominutes

import android.R.attr.button
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.agetominutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener {
            datePicker()
        }

        binding.info.setOnClickListener {
            val i=Intent(
                "android.intent.action.VIEW",
                Uri.parse("https://developer.android.com/reference/java/text/SimpleDateFormat")
            )
            startActivity(i)
        }

    }

    fun datePicker() {

        val calender=Calendar.getInstance()
        val year=calender.get(Calendar.YEAR)
        val month=calender.get(Calendar.MONTH)
        val day=calender.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog (this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            val selectDate = "$dayOfMonth/${month+1}/$year"
            binding.btnDatePicker.text = selectDate

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            var theDate = sdf.parse(selectDate)
            theDate?.let {
                var selectedmin = theDate.time/60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentMin = currentDate.time/60000
                    val diffInMin = currentMin - selectedmin
                    binding.tvSelectedDateInMinutes.text = diffInMin.toString()
                }
            }
        },year,month,day)
        dialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dialog.show()
    }
}