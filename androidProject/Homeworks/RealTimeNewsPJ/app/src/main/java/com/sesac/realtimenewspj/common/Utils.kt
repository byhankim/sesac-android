package com.sesac.realtimenewspj.common

import android.widget.Toast
import com.sesac.realtimenewspj.RealTimeNewsApplication

fun commonToast(message: String = "") {
    Toast.makeText(RealTimeNewsApplication.getRealTimeNewsApplication()
    , "application toast msg: $message"
    , Toast.LENGTH_SHORT).show()
}