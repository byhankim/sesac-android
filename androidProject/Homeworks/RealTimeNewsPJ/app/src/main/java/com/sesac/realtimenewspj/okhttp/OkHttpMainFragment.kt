package com.sesac.realtimenewspj.okhttp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.sesac.realtimenewspj.BaseFragment
import com.sesac.realtimenewspj.FragmentCallbackable
import com.sesac.realtimenewspj.common.SELECT_NEWS
import com.sesac.realtimenewspj.common.SERVER_URL_NEWS
import com.sesac.realtimenewspj.databinding.FragmentOkHttpMainBinding
import com.sesac.realtimenewspj.model.NewsInfo
import com.sesac.realtimenewspj.model.NewsModel
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

const val TAG = "OkHttpFrag"

class OkHttpMainFragment : BaseFragment<FragmentOkHttpMainBinding>(FragmentOkHttpMainBinding::inflate) {

    companion object {
        private val TAG = "FirstFragment"

        // companion obj인데 어차피
        fun newInstance0() = OkHttpMainFragment()

        // 굳이 해야되나?
        lateinit var instance: OkHttpMainFragment
        fun newInstance(): OkHttpMainFragment {
            if (!this::instance.isInitialized) {
                instance = OkHttpMainFragment()
            }
            return instance
        }
    }

    private lateinit var owner: FragmentCallbackable


    // ??? rv 데이터를 전역으로 갖고있는게 맞나...?
    private lateinit var newsList: MutableList<NewsInfo>

    fun setOwner(owner: FragmentCallbackable) {
        this.owner = owner
    }

    // 여기서 owner.callback(anyString) 쓰면됨미다 ㅇㅇ!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG, "init init")




        /**
         * Network
         */
        selectNews()



    }

    private fun selectNews() {
        Thread {
            val toServer: OkHttpClient
            lateinit var response: Response
            try {
                val targetURL: HttpUrl = OkHttpManager.getOkHttpUrl(SELECT_NEWS)
                toServer = OkHttpManager.getOkHttpClient()

                val req: Request = Request.Builder()
                    .url(targetURL)
                    .build()

                Log.e(TAG, "b4 toServer | target URL: $targetURL")
                toServer.newCall(req).execute().also { response = it }
//                Log.e(TAG, "response: ${response.body!!.string()}")

                if (response.isSuccessful) {
                    val gson = Gson()

                    /**
                     * Expected BEGIN_OBJECT but was BEGIN_ARRAY error if NewsData::class.java is used!!
                     * com.google.gson.internal.LinkedTreeMap cannot be cast to ___ -> class.java??
                     */
                    newsList = mutableListOf()
                    JsonParser().parse(response.body!!.string()).asJsonArray.forEach {      // deprecated
                        newsList.add(gson.fromJson(it, NewsInfo::class.java))
                    }

//                    newsList = gson.fromJson(response.body!!.string(), mutableListOf<NewsInfo>()::class.java)//NewsModel::class.java)
                    Log.e(TAG, "successful! $newsList")
//                    val newsList = newsModel.news

                    (owner as OkHttpRESTActivity).runOnUiThread {
//                    activity?.runOnUiThread {
                        Toast.makeText(binding.root.context, "HTTP 통신 성공!", Toast.LENGTH_SHORT).show()
                        /**
                         * RV
                         */
                        val manager = LinearLayoutManager(
                            context, LinearLayoutManager.VERTICAL, false
                        ) // context 를 activity에서 받아오는게 맞나...?

                        with (binding.okhttpFragRV) {
                            layoutManager = manager
                            adapter = OkHttpRecyclerAdapter(newsList, this@OkHttpMainFragment)
                        }
                    }
                } else {
                    Log.e(TAG, "not successful")
                }
            } catch (e: Exception) {
                (owner as Activity).runOnUiThread {
//                activity?.runOnUiThread {
                    Toast.makeText(context, "HTTP 문제 발생: $e", Toast.LENGTH_SHORT).show()
                }
                Log.e(TAG,  "$e")
            }
        }.start()
    }
}