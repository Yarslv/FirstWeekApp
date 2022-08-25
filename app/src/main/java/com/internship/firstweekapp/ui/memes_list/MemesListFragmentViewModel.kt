package com.internship.firstweekapp.ui.memes_list

import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.paging.MemePageSource
import com.internship.firstweekapp.retrofit.RetrofitClient
import com.internship.firstweekapp.retrofit.Retrofits
import com.internship.firstweekapp.ui.memes_list.category_list.CategoriesAdapter
import com.internship.firstweekapp.ui.memes_list.category_list.CategoryClick
import com.internship.firstweekapp.ui.memes_list.recycler.CardRecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemesListFragmentViewModel(val retrofitClient: RetrofitClient) : BaseViewModel(),
    CategoryClick {

    val model = MemeListModel()
    var memePageSource = MemePageSource(retrofitClient)

    var listData = Pager(PagingConfig(pageSize = 24)) {
        memePageSource
        MemePageSource(retrofitClient).apply {
            setFilterText(model.textFilter.get().toString())
        }
    }.flow

    val scope = viewModelScope

    val cardAdapter = CardRecyclerAdapter().apply {
        addLoadStateListener {
            when (it.source.refresh) {
                is LoadState.Loading -> {
                    model.isLoad.set(true)
                    model.isError.set(false)
                }
                is LoadState.Error -> {
                    model.isError.set(true)
                    model.isLoad.set(false)
                    model.error.set((it.source.refresh as LoadState.Error).error.message.toString())
                }
                is LoadState.NotLoading -> {
                    model.isLoad.set(false)
                    model.isError.set(false)
                }
            }
        }
    }


    val categoriesAdapter = CategoriesAdapter(this)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            listData.collect {
                model.items.set(it)
            }
        }
    }

    fun useMock() {
        model.isMock.set(true)
        retrofitClient.retrofit = Retrofits.RetrofitWithMock.create()
        recreateSources()
    }

    private fun recreateSources() {
        memePageSource = MemePageSource(retrofitClient)
        model.items.set(PagingData.empty())
        refresh()
    }

    fun returnToNonMock() {
        model.isMock.set(false)
        retrofitClient.retrofit = Retrofits.RetrofitWithoutMock.create()
        recreateSources()
    }

    fun refresh() {
        listData = Pager(PagingConfig(pageSize = 24)) {
            memePageSource
        }.flow.apply {
            viewModelScope.launch(Dispatchers.IO) {
                collect {
                    model.items.set(it)
                }
            }
        }
    }

    override fun onClick(text: String) {
        model.textFilter.set(text)
    }
}
