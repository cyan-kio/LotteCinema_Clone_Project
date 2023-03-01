package com.cookandroid.lottecinema_clone_project.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cookandroid.lottecinema_clone_project.R
import com.cookandroid.lottecinema_clone_project.databinding.FragmentLotteyMovieBinding
import com.cookandroid.lottecinema_clone_project.databinding.FragmentSsadaguBinding

class SsadaguFragment : Fragment() {

    private lateinit var binding : FragmentSsadaguBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSsadaguBinding.inflate(inflater, container, false)
        return binding.root
    }
}