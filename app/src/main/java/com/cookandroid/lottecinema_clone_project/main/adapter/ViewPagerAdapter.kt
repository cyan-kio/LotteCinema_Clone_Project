package com.cookandroid.lottecinema_clone_project.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.lottecinema_clone_project.R
import com.cookandroid.lottecinema_clone_project.databinding.PagerMainBannerBinding

class ViewPagerAdapter(
    private val context: Context,
    private val imageList: MutableList<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(private val binding: PagerMainBannerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Int) {
            binding.imgPager.setImageDrawable(ContextCompat.getDrawable(context, item))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = PagerMainBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        return holder.bind(imageList[position % imageList.size])
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}

//class ViewPagerAdapter(
//    private val context: Context,
//    private val imageList: MutableList<Int>
//) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
//
//    inner class PagerViewHolder(private val binding: PagerMainBannerBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(item:Int) {
//            binding.imgPager.setImageDrawable(ContextCompat.getDrawable(context, item))
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
//        val view = PagerMainBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return PagerViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
//        return holder.bind(imageList[position])
//    }
//
//    override fun getItemCount(): Int {
//        return imageList.size
//    }
//}