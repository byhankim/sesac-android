package com.sesac.realtimenewspj.retrofit_inclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sesac.realtimenewspj.databinding.ActivityRetrofitRestBinding
import com.sesac.realtimenewspj.model.NewsInfo
import com.sesac.realtimenewspj.model.OkFailResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitRESTActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitRestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitRestBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

//        getData()
        with (binding) {
            searchBtn.setOnClickListener {
                if (searchEt.text.toString().isNotEmpty()) {
                    getData()
                    searchEt.setText("")
                }
            }
        }
    }

    private fun getData() {

        val newsService = RetrofitManager.getRetrofitNewsRESTService()
        var map = hashMapOf<String, String>().apply {
            put("token", binding.searchEt.text.toString())
            put("page", 1.toString())
        }
        val newsCall: Call<List<NewsInfo>> = newsService.searchNewsToken(map)
        newsCall.enqueue(object : Callback<List<NewsInfo>> {
            override fun onResponse(
                call: Call<List<NewsInfo>>,
                response: Response<List<NewsInfo>>
            ) {
                if (response.isSuccessful) {
                    val list = response.body() as List<NewsInfo>

                    refreshNews(list)
                }
            }

            override fun onFailure(call: Call<List<NewsInfo>>, t: Throwable) {
                //
            }
        })

    }

    private fun refreshNews(list: List<NewsInfo>) {
        runOnUiThread {
            binding.retrofitResultTv.text = list.toString()
        }
    }
}