package com.sesac.realtimenewspj

import android.app.Application

class RealTimeNewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        lateinit var appInstance: RealTimeNewsApplication
        fun getRealTimeNewsApplication(): RealTimeNewsApplication = appInstance
    }
}