package com.sesac.realtimenewspj.okhttp

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sesac.realtimenewspj.databinding.NewsRvItemBinding
import com.sesac.realtimenewspj.model.NewsInfo

class OkHttpRecyclerAdapter(
    private val newsList: MutableList<NewsInfo>,
    private val owner: Fragment
) : RecyclerView.Adapter<OkHttpRecyclerAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val rootView = NewsRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Log.e("ADAPTER", newsList[0].datetime_string)
        with (holder.binding) {
            dateTimeTv.text = newsList[position].datetime_string
            paperTv.text = newsList[position].paper.name
            sectionTv.text = newsList[position].section.name
            titleTv.text = newsList[position].title
            contentTv.text = newsList[position].content
            authorTv.text = newsList[position].author
            urlTv.text = newsList[position].url

            root.setOnClickListener {
                // start anthr frag
            }
        }
    }

    override fun getItemCount() = newsList.size

}