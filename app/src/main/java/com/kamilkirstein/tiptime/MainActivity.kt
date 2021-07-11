package com.kamilkirstein.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kamilkirstein.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // here use the second option of the  setOnClickListener the with Lambda transformed one
        // example https://antonioleiva.com/lambdas-kotlin-android/

        binding.calculateButton.setOnClickListener { calculateTheTip() }
    }

    fun calculateTheTip(){

        // reset last tip:
        binding.tipResult.setText(R.string.tip_amount)

        val costOfService = binding.costOfService.text.toString().toDouble()
        val rate = when(binding.tipOptions.checkedRadioButtonId){

            R.id.option_fifteen_percent -> 0.15
            R.id.option_eighteen_percent -> 0.18
            R.id.option_twenty_percent -> 0.20
            else -> 1.00
        }

        var tip = rate * costOfService

        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        // before display the tip format the number to two decimals or to the normal currency format
        // with the NumberFormat.getCurrencyInstance().format(x) method
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.setText(binding.tipResult.text.toString() + " " + formattedTip.toString())
    }

}





