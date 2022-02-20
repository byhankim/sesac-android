package com.sesac.realtimenewspj.okhttp

import com.sesac.realtimenewspj.common.HOST
import com.sesac.realtimenewspj.common.PROTOCOL
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpManager {
    companion object {
        private lateinit var okHttpClient: OkHttpClient

        fun getOkHttpClient(): OkHttpClient {
            if (this::okHttpClient.isInitialized) {
                return okHttpClient
            } else {
                okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()
            }
            return okHttpClient
        }

        /**
         * GET 방식
         */
        fun getOkHttpUrl(targetURL: String) : HttpUrl {
            return HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .addPathSegments(targetURL)
                .build()
        }
    }
}