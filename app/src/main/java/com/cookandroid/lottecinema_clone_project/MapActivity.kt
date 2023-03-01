package com.cookandroid.lottecinema_clone_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.cookandroid.lottecinema_clone_project.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapView

class MapActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapView = MapView(this)

        val mapViewContainer = findViewById(R.id.map_layout) as ViewGroup
        mapViewContainer.addView(mapView)
    }
}