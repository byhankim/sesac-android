package com.han.simplecalculatorpj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.han.simplecalculatorpj.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    /**
     * 1. click event 이용하여 구현
     * 2. 나머지값 구하는 버튼 추가
     * 3. ET 창에 값 입력 않을 시 오류메시지 Toast로 띄울것
     * 4. 0으로 나눠도 오류메세지 Toast 로 띄울 것
     * 5. viewbinding 사용
     * 6. 문자열 및 크기를 나타내는 값을 리소스파일로 전개하여 코드 구성하기
     */

    private lateinit var binding: ActivityMainBinding
    private var numOne: Double = 0.0
    private var numTwo: Double = 0.0
    private var mercyCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
        }

        with (binding) {
            addBtn.setOnClickListener {
                if (isValidInput())
                    resultTV.text = (numOne + numTwo).toInt().toString()
            }
            subBtn.setOnClickListener {
                if (isValidInput())
                    resultTV.text = (numOne - numTwo).toInt().toString()
            }
            mulBtn.setOnClickListener {
                if (isValidInput())
                    resultTV.text = (numOne * numTwo).toInt().toString()
            }
            divBtn.setOnClickListener {
                if (!isValidInput())  {
                    return@setOnClickListener
                }
                if (numTwo == 0.0) {
                    Toast.makeText(this@MainActivity, "0으로 나눌 수 없습니다!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                resultTV.text = (numOne / numTwo).toString()
            }
            modBtn.setOnClickListener {
                if (!isValidInput())  {
                    return@setOnClickListener
                }
                if (numTwo == 0.0) {
                    Toast.makeText(this@MainActivity, "0으로 나머지연산을 할 수 없습니다!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                resultTV.text = (numOne % numTwo).toString()
            }

        }

    }

    private fun isValidInput(): Boolean {
        if (binding.inputOne.text.isEmpty() || binding.inputTwo.text.isEmpty()) {
            Toast.makeText(this, "숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
            return false
        }
        try {
            numOne = binding.inputOne.text.toString().toDouble()
            numTwo = binding.inputTwo.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            if (mercyCount < 5) {
                mercyCount++
                Toast.makeText(this, "숫자만 입력해 주세요!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "당신은 도를 넘었습니다!!", Toast.LENGTH_SHORT).show()
            }
            return false
        }
        return true
    }
}