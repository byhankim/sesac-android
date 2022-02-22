package com.sesac.realtimenewspj.retrofit_inclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sesac.realtimenewspj.FragmentCallbackable
import com.sesac.realtimenewspj.R
import com.sesac.realtimenewspj.databinding.ActivityRetrofitRestBinding
import com.sesac.realtimenewspj.model.NewsInfo
import com.sesac.realtimenewspj.model.OkFailResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRESTActivity : AppCompatActivity(), FragmentCallbackable {

    private lateinit var binding: ActivityRetrofitRestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitRestBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        if (savedInstanceState == null) {
            with(supportFragmentManager.beginTransaction()) {
                val frag = RetrofitNewsFragment.newInstance()
                frag.setOwner(this@RetrofitRESTActivity)
                add(R.id.retrofitFragmentContainer, frag)
                commit()
            }
        }

    }

    override fun callback(msg: String) {}

}