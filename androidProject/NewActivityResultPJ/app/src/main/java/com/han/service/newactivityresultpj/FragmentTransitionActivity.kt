package com.han.service.newactivityresultpj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.han.service.newactivityresultpj.databinding.ActivityFragmentTransitionBinding

class FragmentTransitionActivity : AppCompatActivity() {

    // 계산기 fragment 적용
    private lateinit var binding: ActivityFragmentTransitionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentTransitionBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        if (savedInstanceState == null) {
            with (supportFragmentManager.beginTransaction()) {
                add(R.id.fragment_content_fl, CalculationFragment.newInstance())
                    .commit()
            }
        }

    }
}