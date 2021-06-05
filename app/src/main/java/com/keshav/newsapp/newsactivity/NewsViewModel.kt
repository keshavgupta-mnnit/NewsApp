package com.keshav.newsapp.newsactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keshav.newsapp.repository.INewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class NewsViewModel(private val newsRepository: INewsRepository) : ViewModel() {
    private val _state: MutableLiveData<NewsStates> = MutableLiveData()
    val state: LiveData<NewsStates> = _state
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getNews() {
        _state.value = isLoading
        val observable = newsRepository.getNews().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                _state.value = NewsArticleList(it)
            }, {
                _state.value = Error(it.message.toString())
            })
        compositeDisposable.add(observable)
    }

}