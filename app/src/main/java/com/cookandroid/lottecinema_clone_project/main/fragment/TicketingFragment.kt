package com.cookandroid.lottecinema_clone_project.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.lottecinema_clone_project.MapActivity
import com.cookandroid.lottecinema_clone_project.databinding.FragmentLotteyMovieBinding
import com.cookandroid.lottecinema_clone_project.databinding.FragmentTicketingBinding

class TicketingFragment : Fragment() {
    private lateinit var binding : FragmentTicketingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTicketingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.btnChooseTheater.setOnClickListener {
            startActivity(Intent(requireActivity(), MapActivity::class.java))
        }
    }
}