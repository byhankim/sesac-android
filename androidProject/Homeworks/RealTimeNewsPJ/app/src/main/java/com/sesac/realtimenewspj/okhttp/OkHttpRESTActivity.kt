package com.sesac.realtimenewspj.okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sesac.realtimenewspj.FragmentCallbackable
import com.sesac.realtimenewspj.R
import com.sesac.realtimenewspj.databinding.ActivityOkHttpRestBinding

class OkHttpRESTActivity : AppCompatActivity(), FragmentCallbackable {

    private lateinit var binding: ActivityOkHttpRestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOkHttpRestBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        if (savedInstanceState == null) {
            with (supportFragmentManager.beginTransaction()) {
                val mainFrag = OkHttpMainFragment.newInstance0()
                mainFrag.setOwner(this@OkHttpRESTActivity)
                add(R.id.okhttpFragmentContainer, mainFrag)
                commit()
            }
        }
    }

    override fun callback(msg: String) {
        //
    }
}