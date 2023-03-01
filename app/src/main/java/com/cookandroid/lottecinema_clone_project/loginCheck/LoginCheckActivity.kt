package com.cookandroid.lottecinema_clone_project.loginCheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cookandroid.lottecinema_clone_project.R
import com.cookandroid.lottecinema_clone_project.databinding.ActivityLoginCheckBinding
import com.kakao.sdk.user.UserApiClient

class LoginCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginCheckBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UserApiClient.instance.me { user, error ->
            binding.tvLoginCheck.text = "${user?.kakaoAccount?.profile?.nickname}님, 안녕하세요."
        }

        binding.btnMenuClose.setOnClickListener {
            finish()
        }

    }
}