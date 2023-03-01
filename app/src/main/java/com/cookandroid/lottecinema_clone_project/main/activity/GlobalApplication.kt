package com.cookandroid.lottecinema_clone_project.main.activity

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "98e7c215d422a2fb0f0c148839153f27")
    }
}