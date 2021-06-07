package com.keshav.newsapp.newsactivity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.keshav.newsapp.R
import com.keshav.newsapp.repository.datamodels.NewsArticle
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment() {
    val TAG = NewsDetailsFragment::class.java.canonicalName
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return LayoutInflater.from(context)
            .inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val details: NewsArticle? = arguments?.getParcelable("news_article")
        details?.run {
            loadDetails(this)
        }
    }

    private fun loadDetails(newsArticle: NewsArticle) {
        tv_title.text = newsArticle.title
        tv_source.text = newsArticle.source.name
        tv_description.text = newsArticle.description
        activity?.let { Glide.with(it).load(newsArticle.urlToImage).into(iv_newsImage) }
    }
}