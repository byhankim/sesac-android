package com.sesac.realtimenewspj.retrofit_inclass

import com.google.gson.Gson
import com.sesac.realtimenewspj.common.HOST
import com.sesac.realtimenewspj.common.NEWS_SEARCH_SELECT_URL
import com.sesac.realtimenewspj.common.NewsRESTService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitManager {
    companion object {
        private var _newsService: NewsRESTService? = null
        private val newsService: NewsRESTService get() = _newsService!!

        fun getRetrofitNewsRESTService(): NewsRESTService {
            if (_newsService != null) {
                return newsService
            }
            _newsService = Retrofit.Builder()
                .baseUrl(NEWS_SEARCH_SELECT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsRESTService::class.java)
            return newsService
        }
    }
}