package com.sesac.realtimenewspj.common

import com.sesac.realtimenewspj.model.NewsInfo
import com.sesac.realtimenewspj.model.OkFailResult
import retrofit2.Call
import retrofit2.http.*

interface NewsRESTService {
//    @FormUrlEncoded
//    @POST("/api/search")
//    fun searchKeyword(
//        @Field("token") token: String?
//    ): Call<OkFailResult>

    @GET("/api/search")
    fun searchNewsToken(@QueryMap queryMap: MutableMap<String, String>) :
            Call<List<NewsInfo>>
}