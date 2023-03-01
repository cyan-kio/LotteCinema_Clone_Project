package com.cookandroid.lottecinema_clone_project.main.fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.cookandroid.lottecinema_clone_project.databinding.FragmentDetailBinding
import com.cookandroid.lottecinema_clone_project.databinding.ItemRecyclerReviewBinding
import com.cookandroid.lottecinema_clone_project.main.activity.MainActivity
import com.cookandroid.lottecinema_clone_project.main.data.Review
import com.cookandroid.lottecinema_clone_project.main.response.GetMovieDetailsResponse
import com.cookandroid.lottecinema_clone_project.main.service.RetrofitService
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailFragment : Fragment() {
    private var movie_id: Long? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var reviewList = ArrayList<Review>()
    private var stringReviewPref : String? = null
    private lateinit var binding: FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { movie_id = it.getString("movie_id")?.toLong() }

        settingPrefs()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getMovieDetails(
            movie_id = movie_id!!,
            api_key = "a467687960854d8db7484bdd0b8e2fd8",
        ).enqueue(object : Callback<GetMovieDetailsResponse> {
                override fun onResponse(
                    call: Call<GetMovieDetailsResponse>,
                    response: Response<GetMovieDetailsResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieDetails = response.body()
                        if (movieDetails != null) {
                            binding.apply {
                                tvTitleDetailToolbar.text = movieDetails.title
                                tvTitleDetail.text = movieDetails.title
                                tvRunningTimeDetail.text = movieDetails.runtime.toString()
                                tvReleasedDateDetail.text = movieDetails.release_date
                                tvVoteAvgVal.text = movieDetails.vote_average.toString()
                                tvLikeWithCount.text = movieDetails.vote_count.toString()
                            }
                            Glide.with(requireContext())
                                .load("https://image.tmdb.org/t/p/w154/"+movieDetails.poster_path)
                                .transform(CenterCrop())
                                .into(binding.imgDetailPoster)
                        } else {
                            Log.d("TestResult", "popularMovies is Null")
                        }
                    } else {
                        Log.d("TestResult", "onResponse 실패")
                    }
                }

                override fun onFailure(call: Call<GetMovieDetailsResponse>, t: Throwable) {
                    Log.d("TestDetailResult", "onFailure 에러: " + t.message.toString())
                }
            })


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.ivBackBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reviewRc.apply {
            adapter = ReviewAdapter(
                requireContext(), reviewList
            )
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                true
            )
        }
    }

    override fun onResume() {
        super.onResume()

        binding.btnReviewWrite.setOnClickListener {
            if(binding.edtReviewName.text != null && binding.edtReviewName.text.toString() != ""
                && binding.edtReviewContent.text != null && binding.edtReviewContent.text.toString() != "") {
                savePrefs(binding.edtReviewName.text.toString(), binding.edtReviewContent.text.toString())
                binding.edtReviewName.text = null
                binding.edtReviewContent.text = null
            } else {
                Toast.makeText(requireContext(), "빈 칸을 모두 채워주세요", Toast.LENGTH_SHORT).show()
            }
        }


    }



    private fun settingPrefs() {
        sharedPreferences = requireContext().getSharedPreferences("rv_file", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        stringReviewPref = sharedPreferences.getString("review_data", null)
        if(stringReviewPref != null && stringReviewPref != "[]"){
            reviewList = GsonBuilder().create().fromJson(
                stringReviewPref, object: TypeToken<ArrayList<Review>>(){}.type
            )
        }
    }

    private fun savePrefs(inputName: String, inputContent: String) {
        reviewList.add(
            0,
            Review(
                reviewList.size,
                inputName,
                inputContent
            )
        )
        stringReviewPref = GsonBuilder().create().toJson(
            reviewList,
            object : TypeToken<ArrayList<Review>>(){}.type
        )
        editor.putString("review_data", stringReviewPref)
        editor.apply()
    }

    class ReviewAdapter(
        var context: Context,
        var reviews: ArrayList<Review>
    ) : RecyclerView.Adapter<ReviewAdapter.ReviewHolder>() {

        inner class ReviewHolder(val binding: ItemRecyclerReviewBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(review: Review) {
                binding.tvReviewName.text = review.name
                binding.tvReviewContent.text = review.content
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ReviewHolder {
            val view = ItemRecyclerReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReviewHolder(view)
        }

        override fun onBindViewHolder(holder: ReviewAdapter.ReviewHolder, position: Int) {
            return holder.bind(reviews[position])
        }

        override fun getItemCount(): Int {
            return reviews.size
        }
    }

}