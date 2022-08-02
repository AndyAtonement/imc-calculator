package com.example.calculadoraimc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.setIcon(R.drawable.gordura)
        actionBar?.setDisplayUseLogoEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        calculate()
    }

    @SuppressLint("SetTextI18n")
    fun calculate() {
        binding.btnCalculate.setOnClickListener{
            var heightValue = 0.0
            var weightValue = 0.0

            if(binding.etHeight.text.toString().isNotEmpty()) heightValue = binding.etHeight.text.toString().toDouble()

            if(binding.etWeight.text.toString().isNotEmpty()) weightValue = binding.etWeight.text.toString().toDouble()

            if(weightValue > 0.0 && heightValue > 0.0) {
                val imcValue = String.format(" %.2f", weightValue / (heightValue * heightValue))
                val imcDouble = imcValue.toDouble()
                binding.tvResult.text = "Seu IMC é ${String.format("%.2f", imcDouble)}, você está " + "${imcResults(weightValue / (heightValue * heightValue))}"

            }
            else {
                Toast.makeText(
                    this, "Por favor, insira altura e peso maiores que 0.", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun imcResults(imc: Double) :String{
        when(imc) {
            in 0.0..18.5 -> return "abaixo do peso."
            in 18.5..24.9 -> return "normal."
            in 25.0..29.9 -> return "acima do peso."
            else -> return "obeso."
        }
    }
}