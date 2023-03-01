package com.cookandroid.lottecinema_clone_project.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.cookandroid.lottecinema_clone_project.R
import com.cookandroid.lottecinema_clone_project.databinding.FragmentLotteyMovieBinding
import com.cookandroid.lottecinema_clone_project.databinding.FragmentMenuBinding
import com.cookandroid.lottecinema_clone_project.loginCheck.LoginCheckActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient

class MenuFragment : Fragment() {

    private lateinit var binding : FragmentMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
//                Toast.makeText(requireActivity(), "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
//                Toast.makeText(requireActivity(), "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), LoginCheckActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                val fragmentManager = requireActivity().supportFragmentManager.beginTransaction()
                fragmentManager.remove(this@MenuFragment).commit()
            }
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        //                      Toast.makeText(requireActivity(), "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        //                    Toast.makeText(requireActivity(), "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        //                    Toast.makeText(requireActivity(), "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        //                     Toast.makeText(requireActivity(), "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        //                 Toast.makeText(requireActivity(), "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        //                  Toast.makeText(requireActivity(), "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        //                Toast.makeText(requireActivity(), "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        //            Toast.makeText(requireActivity(), "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        //           Toast.makeText(requireActivity(), "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(requireActivity(), "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), LoginCheckActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                val fragmentManager = requireActivity().supportFragmentManager.beginTransaction()
                fragmentManager.remove(this@MenuFragment).commit()
            }
        }


        binding.tvBtnLogin.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(requireActivity())){
                UserApiClient.instance.loginWithKakaoTalk(requireActivity(), callback = callback)


            }else{
                UserApiClient.instance.loginWithKakaoAccount(requireActivity(), callback = callback)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragmentManager = requireActivity().supportFragmentManager.beginTransaction()
                fragmentManager.setCustomAnimations(R.anim.none, R.anim.slide_out_right)
                fragmentManager.remove(this@MenuFragment).commit()
            }
        })
        binding.btnMenuClose.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager.beginTransaction()
            fragmentManager.setCustomAnimations(R.anim.none, R.anim.slide_out_right)
            fragmentManager.remove(this@MenuFragment).commit()
        }
    }
}