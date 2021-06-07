package com.keshav.newsapp.newsactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keshav.newsapp.R
import com.keshav.newsapp.repository.datamodels.NewsArticle
import kotlinx.android.synthetic.main.itemview_articles.view.*

class NewsArticleAdapter(private val onNewArticleSelected: (NewsArticle) -> Unit) :
    RecyclerView.Adapter<NewsArticleAdapter.NewsArticleViewHolder>() {
    private var newsArticleList: MutableList<NewsArticle> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        return NewsArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.itemview_articles, parent, false),
            onNewArticleSelected
        )
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        holder.bindModel(newsArticleList[position])
    }

    override fun getItemCount(): Int {
        return newsArticleList.size
    }

    fun setData(newsList: List<NewsArticle>) {
        newsArticleList.addAll(newsList)
        notifyDataSetChanged()
    }

    class NewsArticleViewHolder(
        itemView: View,
        private val onNewArticleSelected: (NewsArticle) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bindModel(newArticle: NewsArticle) {
            itemView.newsTitle.text = newArticle.title
            itemView.newsSource.text = newArticle.source.name
            itemView.newsDate.text = newArticle.publishedAt
            Glide.with(itemView).load(newArticle.urlToImage).into(itemView.news_image)
            itemView.setOnClickListener {
                onNewArticleSelected(newArticle)
            }
        }

    }

}