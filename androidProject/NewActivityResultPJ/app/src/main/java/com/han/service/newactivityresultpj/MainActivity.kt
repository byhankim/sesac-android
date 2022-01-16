package com.han.service.newactivityresultpj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.han.service.newactivityresultpj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            firstValueEt.setText("0")
            secondValueEt.setText("0")
        }

        with(binding) {
            addBtn.setOnClickListener { startNewActivity(0) }
            subBtn.setOnClickListener { startNewActivity(1) }
            mulBtn.setOnClickListener { startNewActivity(2) }
            divBtn.setOnClickListener { startNewActivity(3) }



            with(firstValueEt) {
                setOnFocusChangeListener { _, focused ->
                    if (focused && text.toString() == "0") {
                        firstValueEt.setText("")
                    } else if (!focused && text.toString().isEmpty()) {
                        firstValueEt.setText("0")
                    }
                }
            }
            with(secondValueEt) {
                setOnFocusChangeListener { _, focused ->
                    if (focused && text.toString() == "0") {
                        secondValueEt.setText("")
                    } else if (!focused && text.toString().isEmpty()) {
                        secondValueEt.setText("0")
                    }
                }
            }
        }
    }

    private val startActivityResultText =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult> { result ->
                if (result.resultCode == RESULT_OK) {
                    binding.resultTv.text = result.data?.getStringExtra("res_msg")
                } else {
                    binding.resultTv.text = "결과가 제대로 안 왔습니다"
                }
            })

    private fun checkedValidInput(): Boolean {
        if (binding.firstValueEt.text.toString().isEmpty() || binding.secondValueEt.text.toString().isEmpty())
            return false
        return true
    }

    private fun startNewActivity(code: Int) {
        if (!checkedValidInput())
            return
        val target = Intent(this@MainActivity, ResultActivity::class.java)
        target.putExtra("operator", code)
        target.putStringArrayListExtra(
            "numbers",
            arrayListOf<String>(binding.firstValueEt.text.toString(), binding.secondValueEt.text.toString()))
        startActivityResultText.launch(target)
    }
}