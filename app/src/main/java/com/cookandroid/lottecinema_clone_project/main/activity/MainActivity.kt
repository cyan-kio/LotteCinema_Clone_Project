package com.cookandroid.lottecinema_clone_project.main.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cookandroid.lottecinema_clone_project.R
import com.cookandroid.lottecinema_clone_project.databinding.ActivityMainBinding
import com.cookandroid.lottecinema_clone_project.main.adapter.ViewPagerAdapter
import com.cookandroid.lottecinema_clone_project.main.fragment.*
import com.kakao.sdk.common.util.Utility

class MainActivity: AppCompatActivity() {

    private val imageList = mutableListOf<Int>().apply{
        add(R.drawable.img_pager_banner_one)
        add(R.drawable.img_pager_banner_two)
        add(R.drawable.img_pager_banner_three)
        add(R.drawable.img_pager_banner_four)
        add(R.drawable.img_pager_banner_five)
    }
    var key = 2
    private lateinit var movieChartFragment: MovieChartFragment
    private lateinit var ssadaguFragment: SsadaguFragment
    private lateinit var lotteyMovieFragment: LotteyMovieFragment
    private lateinit var TicketingFragment: TicketingFragment
    private lateinit var MenuFragment: MenuFragment
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavi.apply {
            background = null
            itemIconTintList = null
            itemTextColor = null
        }

        movieChartFragment = MovieChartFragment()
        ssadaguFragment = SsadaguFragment()
        lotteyMovieFragment = LotteyMovieFragment()
        TicketingFragment = TicketingFragment()
        MenuFragment = MenuFragment()

        initViewPager()
        changeFragment(movieChartFragment)

        val keyHash = Utility.getKeyHash(this)//onCreate 안에 입력해주자
        Log.d("Hash", keyHash)


    }

    override fun onResume() {
        super.onResume()

        binding.fabBottomSheet.setOnClickListener {
            val fragmentManager = supportFragmentManager.beginTransaction()
            if (key%2 == 0) {
                fragmentManager.setCustomAnimations(R.anim.vertical_enter, R.anim.none)
                fragmentManager.replace(R.id.fragmentView, TicketingFragment)
                fragmentManager.commit()
                key++
            } else {
                fragmentManager.setCustomAnimations(R.anim.none, R.anim.vertical_exit)
                fragmentManager.remove(TicketingFragment).commit()
                key++
            }
        }

        binding.btmNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btm_navi_menu -> {
                    val fragmentManager = supportFragmentManager.beginTransaction()
                    fragmentManager.setCustomAnimations(R.anim.slide_in_right, R.anim.none)
                    fragmentManager.replace(R.id.fragment_menu, MenuFragment)
                    fragmentManager.commit()
                    return@setOnItemSelectedListener true
                } else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }

        changeFragmentInChart()
    }



    private fun uncheckedRadioBtn(applyRBtn: AppCompatRadioButton) {
        applyRBtn.apply{
            setTextColor(Color.parseColor("#B1B1B1"))
            setTextSize(Dimension.SP, 15f)
        }
    }
    private fun checkedRadioBtn(applyRBtn: AppCompatRadioButton) {
        applyRBtn.apply{
            setTextColor(Color.BLACK)
            setTextSize(Dimension.SP, 21f)
        }
    }

    private fun changeFragmentInChart() {
        binding.radiogroupMovieChart.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radiobtn_movie_chart -> {
                    checkedRadioBtn(binding.radiobtnMovieChart)
                    uncheckedRadioBtn(binding.radiobtnMovieSsadagu)
                    uncheckedRadioBtn(binding.radiobtnLotteyMovie)
                    changeFragment(movieChartFragment)
                }
                R.id.radiobtn_movie_ssadagu -> {
                    checkedRadioBtn(binding.radiobtnMovieSsadagu)
                    uncheckedRadioBtn(binding.radiobtnMovieChart)
                    uncheckedRadioBtn(binding.radiobtnLotteyMovie)
                    changeFragment(ssadaguFragment)
                }
                R.id.radiobtn_lottey_movie -> {
                    checkedRadioBtn(binding.radiobtnLotteyMovie)
                    uncheckedRadioBtn(binding.radiobtnMovieChart)
                    uncheckedRadioBtn(binding.radiobtnMovieSsadagu)
                    changeFragment(lotteyMovieFragment)
                }
            }
        }
    }
    private fun initViewPager(){
        binding.viewPager.apply{
            adapter = ViewPagerAdapter(this@MainActivity, imageList)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            currentItem = 1000
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_movie_chart, fragment).commit()
    }


}