package com.sesac.realtimenewspj.okhttp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sesac.realtimenewspj.BaseFragment
import com.sesac.realtimenewspj.databinding.FragmentOkHttpMainBinding
import com.sesac.realtimenewspj.databinding.NewsRvItemBinding
import com.sesac.realtimenewspj.model.NewsInfo

class NewsArticleFragment(private val newsEntity: NewsInfo):
    BaseFragment<NewsRvItemBinding>(NewsRvItemBinding::inflate) {

    companion object {
//        fun newInstance(entity: NewsInfo) = NewsArticleFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            dateTimeTv.text = newsEntity.datetime_string
            paperTv.text = newsEntity.paper.name
            sectionTv.text = newsEntity.section.name
            titleTv.text = newsEntity.title
            contentTv.text = newsEntity.content
            authorTv.text = newsEntity.author
            urlTv.text = newsEntity.url
        }
    }

}