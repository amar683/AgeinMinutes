package com.example.ageminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvselecteddate : TextView ? = null
    private var minutestime :TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvselecteddate= findViewById(R.id.tvselecteddate)
        minutestime = findViewById(R.id.minutestime)
        val btnDate : Button = findViewById(R.id.btnDate)
        btnDate.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar= Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {_,selectedyear,selectedmonth,selecteddayOfMonth->
                Toast.makeText(this,"it works",Toast.LENGTH_LONG).show()

                val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                tvselecteddate?.text=selectedDate


                val sdf= java.text.SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val thedate = sdf.parse(selectedDate)
                thedate?.let {
                    val selectedDateinminutes=thedate.time/60000
                    val currentdate= sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentdate?.let {

                        val currentDateinMinutes= currentdate.time/60000
                        val differenceinMinutes= currentDateinMinutes-selectedDateinminutes
                        minutestime?.text = differenceinMinutes.toString()
                    }
                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}