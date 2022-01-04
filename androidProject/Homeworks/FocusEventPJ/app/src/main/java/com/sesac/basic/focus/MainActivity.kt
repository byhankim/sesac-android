package com.sesac.basic.focus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.sesac.basic.focus.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    /**
     * FocusEvent 를 연결해 자동으로 평균을 구하는 프로그램 완성하기
     */
    private lateinit var binding: ActivityMainBinding
    private val inputMap = mutableMapOf<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val etArray = listOf(et1, et2, et3)
            etArray.forEach {
                it.setOnFocusChangeListener { _, focused ->
                    if (!focused && isValidInput(it)) {
                        inputMap[it.id] = it.text.toString().toInt()
                        calculateAvg()
                    } else {
                        inputMap[it.id] = Int.MIN_VALUE
                    }
                }
            }
        }
    }

    private fun isValidInput(it: EditText): Boolean {
        if (it.text.toString().isEmpty()) {
            Toast.makeText(this, resources.getString(R.string.empty_et), Toast.LENGTH_SHORT).show()
            return false
        }
        try {
            it.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, resources.getString(R.string.invalid_value), Toast.LENGTH_SHORT).show()
            it.text = null
            return false
        }
        return true
    }

    private fun calculateAvg() {
        if (inputMap.size < 3 || inputMap.filter { it.value != Int.MIN_VALUE }.count() < 3)
            return

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_UP
        val out = df.format(inputMap.filter { it.value != Int.MIN_VALUE }.values.sum() / 3.0)
        binding.tvAvg.text = "Avg: $out"
    }
}