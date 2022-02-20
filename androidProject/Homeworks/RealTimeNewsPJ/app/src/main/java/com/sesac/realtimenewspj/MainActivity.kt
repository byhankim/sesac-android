package com.sesac.realtimenewspj

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.sesac.realtimenewspj.common.commonToast
import com.sesac.realtimenewspj.databinding.ActivityMainBinding
import com.sesac.realtimenewspj.okhttp.OkHttpRESTActivity
import java.util.*

class MainActivity : ListActivity() {

    private val actions = TreeMap<String, Intent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        commonToast("야 여기 메인인데 왜안나오냐")
        addActionMap("1. OkHttp", OkHttpRESTActivity::class.java)

        val keys: Set<String> = actions.keys
        val keyNames = keys.toTypedArray()
        listAdapter = ArrayAdapter(this, R.layout.activity_main, keyNames)
        /**
         *
        0. manifest/build.gradle 환경설정 (okhttp, gson)
        1. json model mapping
        2. background: conn-req-res-close
        3. res-res 시 ONLY OKHTTP -> tutorial ref (form tag etc.)
        4. paper -> 선택 ㄱ
        5. XX뉴스 가운데 상단에 박고 Rv로...SwipeRefreshLayout (opt)
        6. rv 아이템 클릭시 상세화면
        7. 우측상단 paiging 처리 카테고리 선택에서 keyword를 section으로 하기
        8. 될수있음 fragment에 viewbinding 으로 해볼것
        9. 다음주 금요일까지 제안서ㅠㅡㅠ
        */
    }
    private fun addActionMap(keyName: String, className: Class<*>) {
        actions[keyName] = Intent(this, className)
    }

    override fun onListItemClick(l: ListView?, v: View?, pos: Int, id: Long) {
        val intentName = listView.getItemAtPosition(pos) as String
        startActivity(actions[intentName])
    }
}