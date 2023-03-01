package com.cookandroid.lottecinema_clone_project.main.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookandroid.lottecinema_clone_project.R
import com.cookandroid.lottecinema_clone_project.databinding.FragmentDetailBinding
import com.cookandroid.lottecinema_clone_project.databinding.FragmentMovieChartBinding
import com.cookandroid.lottecinema_clone_project.main.adapter.MovieChartAdapter
import com.cookandroid.lottecinema_clone_project.main.response.GetMoviesResponse
import com.cookandroid.lottecinema_clone_project.main.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieChartFragment : Fragment() {
    //    private lateinit var retrofit: Retrofit
//    private lateinit var retrofitService: RetrofitService
//    private lateinit var getPopularMoviesResponse: GetPopularMoviesResponse
//    private lateinit var getNowPlayingMoviesResponse: GetNowPlayingMoviesResponse
    private lateinit var binding: FragmentMovieChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val fragmentManager = requireActivity().supportFragmentManager
        val detailFragment = DetailFragment()

        retrofitService.getPopularMovies(api_key = "a467687960854d8db7484bdd0b8e2fd8", page = 1)
            .enqueue(object :
                Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val popularMovies = response.body()
                        if (popularMovies != null) {
                            binding.ticketingRc.apply {
                                adapter = MovieChartAdapter(
                                    context, popularMovies.results, fragmentManager, detailFragment
                                )
                                layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            }
                        } else {
                            Log.d("TestResult", "popularMovies is Null")
                        }
                    } else {
                        Log.d("TestResult", "onResponse 실패")
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d("TestResult", "onFailure 에러: " + t.message.toString())
                }
            })

        retrofitService.getNowPlayingMovies(api_key = "a467687960854d8db7484bdd0b8e2fd8", page = 1)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val nowPlayingMovies = response.body()
                        if (nowPlayingMovies != null) {
                            binding.nowPlayingRc.apply {
                                adapter = MovieChartAdapter(
                                    context, nowPlayingMovies.results, fragmentManager, detailFragment
                                )
                                layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            }
                        } else {
                            Log.d("TestResult", "popularMovies is Null")
                        }
                    } else {
                        Log.d("TestResult", "onResponse 실패")
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d("TestResult", "onFailure 에러: " + t.message.toString())
                }
            })

        retrofitService.getUpComingMovies(api_key = "a467687960854d8db7484bdd0b8e2fd8", page = 1)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val upComingMovies = response.body()
                        if (upComingMovies != null) {
                            binding.upcomingRc.apply {
                                adapter = MovieChartAdapter(
                                    context, upComingMovies.results, fragmentManager, detailFragment
                                )
                                layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            }
                        } else {
                            Log.d("TestResult", "popularMovies is Null")
                        }
                    } else {
                        Log.d("TestResult", "onResponse 실패")
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    Log.d("TestResult", "onFailure 에러: " + t.message.toString())
                }
            })



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieChartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.radiogroupMovieChart.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radiobtn_ticketing_sequence -> {
                    binding.ticketingRc.visibility = View.VISIBLE
                    binding.nowPlayingRc.visibility = View.INVISIBLE
                    binding.upcomingRc.visibility = View.INVISIBLE
                }
                R.id.radiobtn_now_playing -> {
                    binding.ticketingRc.visibility = View.INVISIBLE
                    binding.nowPlayingRc.visibility = View.VISIBLE
                    binding.upcomingRc.visibility = View.INVISIBLE
                }
                R.id.radiobtn_upcoming -> {
                    binding.ticketingRc.visibility = View.INVISIBLE
                    binding.nowPlayingRc.visibility = View.INVISIBLE
                    binding.upcomingRc.visibility = View.VISIBLE
                }
            }
        }
    }

}

