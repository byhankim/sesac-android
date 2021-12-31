package com.han.currenttimepj

import android.app.ActionBar
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var currentButton: Button
    private lateinit var displayTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /**
         * 직접 코드로 간단히 UI를 구성하는 법
         */
        // this는 현재 Conext(앱이 동작하는 글로벌 메모리영역)
        // Context 에 앱이 사용하는 많은 자원을 접근 가능함
        val lineContainer = LinearLayout(this)
        // 현재 window 화면을 다 사용하겠다는 의미
        val layoutParam = LinearLayout.LayoutParams(    // 뒷받침필드 (set)
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        lineContainer.run {
            layoutParams = layoutParam
            orientation = LinearLayout.VERTICAL
        }

//         val titleMessage = R.string.title_msg
         val titleMessage = resources.getString(R.string.title_msg)
        val tv = TextView(this)
        val tFace = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        with (tv) {
            text = titleMessage // backing field
            textSize = 28f // float type 이어야함
            gravity = Gravity.CENTER_HORIZONTAL
            typeface = tFace
        }

        // Button
        if (!this::currentButton.isInitialized) {
            currentButton = Button(this)
        }

        with (currentButton) {
            width = LinearLayout.LayoutParams.WRAP_CONTENT
            height = LinearLayout.LayoutParams.MATCH_PARENT
            text = resources.getString(R.string.current_time)
            gravity = Gravity.CENTER
        }

        displayTV = TextView(this)
        displayTV.apply {
            text = "현재 시간은 여기! 에 보여짐"
            setTextColor(Color.MAGENTA)
            typeface = tFace
            gravity = Gravity.CENTER
        }

        // 레거시
        val legacyBtn = Button(this)
        with (legacyBtn) {
            width = LinearLayout.LayoutParams.WRAP_CONTENT
            height = LinearLayout.LayoutParams.MATCH_PARENT
            text = resources.getString(R.string.current_time)
            gravity = Gravity.CENTER
        }

        lineContainer.run {
            addView(tv)
            addView(currentButton)
            addView(displayTV)
            addView(legacyBtn)
        }

        // MUST: root element (layout을 activity에서 꼭 줘야한다) top-down 방식으로 그린다
        setContentView(lineContainer)

        // 이벤트처리
        currentButton.setOnClickListener {
            updateTime()
        }

    }

    private val dateFormatter = SimpleDateFormat("현재 시간은 yyyy/MM/dd HH:mm:ss 입니다", Locale.KOREA)

    private fun updateTime() {
        displayTV.text = dateFormatter.format(Date(System.currentTimeMillis()))
    }
}