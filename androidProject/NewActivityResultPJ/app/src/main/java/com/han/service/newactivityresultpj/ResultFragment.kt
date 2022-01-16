package com.han.service.newactivityresultpj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.han.service.newactivityresultpj.databinding.FragmentResultBinding

class ResultFragment: Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding
        get() = _binding

    companion object {
        fun newInstance() = ResultFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        with (_binding!!) {
            resTv.text = calculate(_binding!!, requireArguments())

            finishBtn.setOnClickListener {
                val result = calculate(_binding!!, requireArguments())
                resTv.text = result

                val newFrag = CalculationFragment.newInstance().apply {
                    arguments = Bundle().apply {
                        putString("resultText", result)
                    }
                }

                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_content_fl, newFrag)
                    ?.commit()
//                activity?.supportFragmentManager?.popBackStack()
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calculate(binding: FragmentResultBinding, bundle: Bundle): String {
        var res = ""

        try {
            Log.e("RES", "${bundle.getStringArrayList("numbers")}")
            val (A, B) = bundle.getStringArrayList("numbers")!!.map { it.toInt() }
            val opr = bundle.getInt("operator", -1)
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
            Toast.makeText(activity, "숫자 변환 에러!", Toast.LENGTH_SHORT).show()
            return ""
        }
        return res
    }
}