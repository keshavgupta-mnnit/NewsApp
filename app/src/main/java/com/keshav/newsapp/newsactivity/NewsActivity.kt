package com.keshav.newsapp.newsactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.keshav.newsapp.R
import com.keshav.newsapp.newsactivity.adapter.NewsArticleAdapter
import com.keshav.newsapp.util.isNetworkAvailable
import kotlinx.android.synthetic.main.activity_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {
    val TAG = NewsActivity::class.java.canonicalName
    private val viewModel: NewsViewModel by viewModel()
    private lateinit var adapter: NewsArticleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        viewModel.state.observe(
            this,
            { render(it) }
        )
        initView()
        requestNews()
    }

    private fun requestNews() {
        if (this.isNetworkAvailable()) {
            viewModel.getNews()
        } else {
            noInternet()
        }
    }

    private fun noInternet() {
        rv_newsArticles.visibility = View.GONE
        tv_noInternet.visibility = View.VISIBLE
        btn_tryAgain.visibility = View.VISIBLE
    }

    fun loadNews(){
        rv_newsArticles.visibility = View.VISIBLE
        tv_noInternet.visibility = View.GONE
        btn_tryAgain.visibility = View.GONE
    }

    private fun initView() {
        adapter = NewsArticleAdapter()
        rv_newsArticles.adapter = adapter
        btn_tryAgain.setOnClickListener {
            requestNews()
        }
    }

    private fun render(state: NewsStates) {
        when (state) {
            is isLoading -> {
                showLoader()
            }
            is Error -> {
                hideLoader()
                Log.e(TAG, state.message)
            }
            is NewsArticleList -> {
                hideLoader()
                if (::adapter.isInitialized) {
                    adapter.setData(state.list)
                    loadNews()
                }
            }
        }
    }

    private fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        progressBar.visibility = View.GONE
    }
}