package com.han.layoutcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.han.layoutcalculator.databinding.ActivityLinearLayoutCalculatorBinding

class LinearLayoutCalculator : AppCompatActivity() {
    private lateinit var binding: ActivityLinearLayoutCalculatorBinding
    private var operand1: Double? = null
    private var operand2: Double? = null
    private var hasResult = false
    private var opr: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinearLayoutCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun operandClicked(v: View) {
        val text = binding.expressionEt.text
        when (v.id) {
            R.id.Btn00, R.id.Btn01, R.id.Btn02, R.id.Btn03, R.id.Btn04,
            R.id.Btn05, R.id.Btn06, R.id.Btn07, R.id.Btn08, R.id.Btn09 -> {
                binding.expressionEt.setText("${text}${(v as Button).text}")
            }
            // 소수점 체
            R.id.BtnPoint -> {
                if (opr != null) {
                    val parsed = text.split("+", "-", "*", "/")[1]
                    if (!parsed.contains('.')) {
                        binding.expressionEt.setText("${binding.expressionEt.text}${(v as Button).text}")
                    }

                } else if (!text.contains('.')) {
                    binding.expressionEt.setText("${binding.expressionEt.text}${(v as Button).text}")
                }
            }
            else -> {}
        }
    }

    fun operatorClicked(v: View) {
        when (v.id) {
            R.id.BtnPlus, R.id.BtnMinus, R.id.BtnMul, R.id.BtnDiv -> {
                // check op1
                try {
                    Log.e("operatorClicked))", "$operand1")
                    operand1 = if (hasResult) operand1 else binding.expressionEt.text.toString()
                        .split("+", "-", "*", "/")[0].toDouble()
                    binding.expressionEt.setText("${operand1}${(v as Button).text}")
                    opr = v.id
                } catch (e: NumberFormatException) {
                    sendToast(resources.getString(R.string.wrong_input))
                    reset()
                }
            }
            else -> {}
        }
    }

    fun utilButtonClicked(v: View) {
        when (v.id) {
            R.id.BtnResult -> {
                if (operand1 == null || opr == null)
                    return
                // gain op2
                operand2 = binding.expressionEt.text.split("+", "-", "*", "/")[1].toDouble()
                Log.e("main...", "$operand2")

                // calculation
                val res: Double = when (opr) {
                    R.id.BtnPlus -> operand1!! + operand2!!
                    R.id.BtnMinus -> operand1!! - operand2!!
                    R.id.BtnMul -> operand1!! * operand2!!
                    R.id.BtnDiv -> operand1!! / operand2!!
                    else -> Double.MIN_VALUE
                }
                Log.e("Main", "$operand1, $opr, $operand2, $res")

                // set resTv & set it op1
                binding.resTv.text = res.toString()
                operand1 = res; hasResult = true; opr = null; operand2 = null;
                Log.e("MAINACTIVIY", "$operand1")

            }
            R.id.BtnDel -> {
                reset()
            }
        }
    }

    private fun reset() {
        operand1 = null; operand2 = null; opr = null
        hasResult = false
        binding.expressionEt.setText("0")
        binding.resTv.text = "0"
    }

    private fun sendToast(text: String = "...") {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}