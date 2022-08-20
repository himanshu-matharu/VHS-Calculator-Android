package com.himanshumatharu.casiocalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import com.himanshumatharu.casiocalculator.databinding.ActivityMainBinding
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    /** Variables and Properties */
    private lateinit var binding: ActivityMainBinding
    private var displayLabel: TextView? = null
    private var calculationLabel: TextView? = null

    private var isFinishedTypingNumber: Boolean = true

    private var displayValue: Double
        get() {
            return displayLabel?.text.toString().toDouble()
        }
        set(value) {
            displayLabel?.text = StringUtils.trimTrailingZero(String.format("%.2f",value))
        }

    private var calculationValue: String
        get() {
            return calculationLabel?.text.toString()
        }
        set(value) {
            calculationLabel?.text = value
        }

    private var calculator = CalculatorLogic()

    /** Class Methods */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayLabel = binding.displayTv
        calculationLabel = binding.calculationTv

        displayLabel?.text = "0"
        calculationLabel?.text = ""
    }

    @SuppressLint("SetTextI18n")
    fun numBtnPressed(view:View) {
        val bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce)
        view.startAnimation(bounceAnim)
        val numValue = (view as Button).text
        if (isFinishedTypingNumber) {
            displayLabel?.text = numValue
            isFinishedTypingNumber = false
        }else{
            if (numValue == ".") {
                val isInt = floor(displayValue) == displayValue
                if (!isInt) {
                    return
                }
            }
            displayLabel?.text = displayLabel?.text.toString() + numValue
        }
    }

    fun calcBtnPressed(view:View) {
        val bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce)
        view.startAnimation(bounceAnim)
        isFinishedTypingNumber = true
        this.calculator.setNumber(displayValue)
        val calcMethod = (view as Button).text
        val result = this.calculator.calculate(calcMethod as String)
        result.first?.let { displayValue = it }
        result.second?.let { calculationValue = it }
    }

    fun backBtnPressed(view:View) {
        val bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce)
        view.startAnimation(bounceAnim)
        if (!isFinishedTypingNumber) {
            displayLabel?.text.let {
                var newNumber = it.toString().dropLast(1)
                if (newNumber.isEmpty()) {
                    newNumber = "0"
                    isFinishedTypingNumber = true
                }
                displayLabel?.text = newNumber
            }
        }
    }
}