package com.keshav.newsapp.newsactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keshav.newsapp.R
import com.keshav.newsapp.newsactivity.fragment.NewsDetailsFragment
import com.keshav.newsapp.newsactivity.fragment.NewsListFragment
import com.keshav.newsapp.repository.datamodels.NewsArticle

class NewsActivity : AppCompatActivity(), NewsListFragment.NewsArticleCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        launchFragment(NewsListFragment())
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is NewsListFragment) {
            fragment.callback = this
        }
    }

    override fun displaySelectedNews(newsArticle: NewsArticle) {
        val bundle = Bundle()
        bundle.putParcelable("news_article", newsArticle)
        val fragment = NewsDetailsFragment()
        fragment.arguments = bundle
        launchFragment(fragment)
    }

    private fun launchFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(fragment.tag).commit()
    }
}