package com.keshav.newsapp.newsactivity.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keshav.newsapp.R
import com.keshav.newsapp.newsactivity.*
import com.keshav.newsapp.newsactivity.adapter.NewsArticleAdapter
import com.keshav.newsapp.repository.datamodels.NewsArticle
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment() {
    val TAG = NewsListFragment::class.java.canonicalName
    private val viewModel: NewsViewModel by viewModel()
    private lateinit var adapter: NewsArticleAdapter
    lateinit var callback: NewsArticleCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context)
            .inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(
            viewLifecycleOwner,
            { render(it) }
        )
        initView()
        requestNews()
    }

    private fun requestNews() {
        viewModel.getNews()
    }

    private fun noInternet() {
        rv_newsArticles.visibility = View.GONE
        tv_noInternet.visibility = View.VISIBLE
        btn_tryAgain.visibility = View.VISIBLE
    }

    private fun loadNews() {
        rv_newsArticles.visibility = View.VISIBLE
        tv_noInternet.visibility = View.GONE
        btn_tryAgain.visibility = View.GONE
    }

    private fun initView() {
        adapter = NewsArticleAdapter(::newsSelected)
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

    fun newsSelected(newsArticle: NewsArticle) {
        callback.displaySelectedNews(newsArticle)
    }

    interface NewsArticleCallback {
        fun displaySelectedNews(newsArticle: NewsArticle)
    }

}