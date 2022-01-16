package com.han.service.newactivityresultpj

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.han.service.newactivityresultpj.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        // setvalue
        binding.resTv.text = calculate(intent)

        binding.finishBtn.setOnClickListener {
            calculate(intent)
            var resMsg = binding.resTv.text.toString()
            val target = Intent()
            if (resMsg.isNotEmpty()) {
                target.putExtra("res_msg", resMsg)
                setResult(Activity.RESULT_OK, target)
            } else {
                resMsg = "결과 에러"
                target.putExtra("res_msg", resMsg)
                setResult(Activity.RESULT_CANCELED, intent)
            }
            finish()
        }
    }


    private fun calculate(intent: Intent): String {
        var res = ""

        try {
            Log.e("RES", "${intent.getStringArrayListExtra("numbers")}")
            val (A, B) = intent.getStringArrayListExtra("numbers")!!.map { it.toInt() }
            val opr = intent.getIntExtra("operator", -1)
            when (opr) {
                0 -> {
                    res = (A + B).toString()
                }
                1 -> {
                    res = (A - B).toString()
                }
                2 -> {
                    res = (A * B).toString()
                }
                3 -> {
                    res = (A / B).toString()
                }
                else -> {
                    res = ""
                }
            }

        } catch (e: NumberFormatException) {
            Toast.makeText(applicationContext, "숫자 변환 에러!", Toast.LENGTH_SHORT).show()
            return ""
        }
        return res
    }
}