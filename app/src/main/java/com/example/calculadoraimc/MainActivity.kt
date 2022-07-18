package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var uriUrl: Uri
    lateinit var lauchBrowser: Intent
    lateinit var answer: String

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
        val btnCalculate = binding.btnCalculate
        val height = binding.etHeight
        val weight = binding.etWeight
        val result = binding.tvResult

        btnCalculate.setOnClickListener{
            var heightValue = 0.0
            var weightValue = 0.0

            if(height.text.toString().isNotEmpty()) {
                heightValue = height.text.toString().toDouble()
            }
            if(weight.text.toString().isNotEmpty()){
                weightValue = weight.text.toString().toDouble()
            }
            if(weightValue > 0.0 && heightValue > 0.0) {
                val imcValue = String.format(" %.2f", weightValue / (heightValue * heightValue))
                val imcDouble = imcValue.toDouble()
                result.text = "Seu IMC é ${String.format("%.2f", imcDouble)}, você está " + "${imcResults(weightValue / (heightValue * heightValue))}"

            }
            else {
                Toast.makeText(
                    this, "Por favor, insira altura e peso maiores que 0", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun imcResults(imc: Double) :String{

        if(imc < 18.5){
            answer="Abaixo do peso"
        } else if(imc > 18.5 && imc < 24.9) {
            answer="Normal"
        } else if(imc > 24.9 && imc < 30) {
            answer="Acima do peso."
        } else {
            answer="Obeso"
        }
        return answer
    }

    fun goToLinkedIn(view: View) {
        toUrl("https://linkedin.com/in/andre-saantos")
    }

    fun toUrl(url: String) {
        uriUrl = Uri.parse(url)
        lauchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(lauchBrowser)
    }
}