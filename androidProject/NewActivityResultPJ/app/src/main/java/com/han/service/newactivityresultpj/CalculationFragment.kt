package com.han.service.newactivityresultpj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.han.service.newactivityresultpj.databinding.FragmentCalculationBinding

class CalculationFragment : Fragment() {

    private var _binding: FragmentCalculationBinding? = null
    private val binding
        get() = _binding

    companion object {
        fun newInstance() = CalculationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculationBinding.inflate(inflater, container, false).apply {
            firstValueEt.setText("0")
            secondValueEt.setText("0")
        }

        with(_binding!!) {
//            val btnArray = arrayOf(addBtn, subBtn, mulBtn, divBtn)
            addBtn.setOnClickListener { startResultFragment(_binding!!, 0)}
            subBtn.setOnClickListener { startResultFragment(_binding!!, 1) }
            mulBtn.setOnClickListener { startResultFragment(_binding!!, 2) }
            divBtn.setOnClickListener { startResultFragment(_binding!!, 3) }



            arrayOf (firstValueEt, secondValueEt).forEach {
                it.setOnFocusChangeListener { _, focused ->
                    if (focused && it.text.toString() == "0") {
                        it.setText("")
                    } else if (!focused && it.text.toString().isEmpty()) {
                        it.setText("0")
                    }
                }
            }

            /*
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

             */
        }

        // check if returned from another fragment
        Log.e("CALC_FRAG", arguments.toString())
            _binding?.resultTv?.text = arguments?.getString("resultText", "아직 계산 안했어용!")
//            .apply {
//            Log.e("CALC_FRAG", "backstack frag 수: ${activity?.supportFragmentManager?.backStackEntryCount}")
//        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkedValidInput(): Boolean {
        if (binding!!.firstValueEt.text.toString()
                .isEmpty() || binding!!.secondValueEt.text.toString().isEmpty()
        )
            return false
        return true
    }

    private fun startResultFragment(binding: FragmentCalculationBinding, code: Int) {
        if (!checkedValidInput())
            return
        val newFrag = ResultFragment.newInstance().apply {
            arguments = Bundle().apply {
                putInt("operator", code)
                putStringArrayList(
                    "numbers",
                    arrayListOf<String>(
                        binding.firstValueEt.text.toString(),
                        binding.secondValueEt.text.toString()
                    )
                )
            }
        }
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.fragment_content_fl, newFrag)?.commit()
    }

}