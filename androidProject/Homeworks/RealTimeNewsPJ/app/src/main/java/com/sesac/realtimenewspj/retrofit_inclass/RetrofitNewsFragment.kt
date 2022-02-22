package com.sesac.realtimenewspj.retrofit_inclass

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sesac.realtimenewspj.BaseFragment
import com.sesac.realtimenewspj.FragmentCallbackable
import com.sesac.realtimenewspj.R
import com.sesac.realtimenewspj.databinding.FragmentRetrofitNewsBinding
import com.sesac.realtimenewspj.model.NewsInfo
import com.sesac.realtimenewspj.okhttp.OkHttpRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitNewsFragment : BaseFragment<FragmentRetrofitNewsBinding>(
    FragmentRetrofitNewsBinding::inflate
) {
    companion object {
        private val R_TAG = "RetrofitFragment"

        fun newInstance() =
            RetrofitNewsFragment()
    }

    private lateinit var newsList: MutableList<NewsInfo>
    private lateinit var owner: FragmentCallbackable
    fun setOwner(owner: FragmentCallbackable) {
        this.owner = owner
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            newsList = mutableListOf()
            val manager = LinearLayoutManager(context)
            with(retrofitFragRv) {
                layoutManager = manager
                adapter = RetrofitRecyclerAdapter(newsList, this@RetrofitNewsFragment)
            }
            getData()

            tokenSearchBtn.setOnClickListener {
                getData()
            }
        }
    }


    private fun getData() {

        val newsService = RetrofitManager.getRetrofitNewsRESTService()
        var map = hashMapOf<String, String>().apply {
            put("token", binding.keywordEt.text.toString())
            put("page", 1.toString())
        }
        val newsCall: Call<List<NewsInfo>> = newsService.searchNewsToken(map)
        newsCall.enqueue(object : Callback<List<NewsInfo>> {
            override fun onResponse(
                call: Call<List<NewsInfo>>,
                response: Response<List<NewsInfo>>
            ) {
                if (response.isSuccessful) {
                    Log.e(R_TAG, "response 성공적으로 받음")
                    val list = response.body() as MutableList<NewsInfo>

                    refreshNews(list)
                    binding.keywordEt.clearFocus()

                    // newsList에 concat 작업
                    list.forEach {
                        newsList.add(it)
                    }
                    refreshNews(newsList)
                }
            }

            override fun onFailure(call: Call<List<NewsInfo>>, t: Throwable) {
                //
            }
        })

    }

    private fun refreshNews(list: MutableList<NewsInfo>) {
        (owner as Activity).runOnUiThread {
            with(binding.retrofitFragRv) {
                adapter = RetrofitRecyclerAdapter(list, this@RetrofitNewsFragment)
            }
        }
    }
}