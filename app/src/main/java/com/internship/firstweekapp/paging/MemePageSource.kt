package com.internship.firstweekapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.internship.firstweekapp.retrofit.Meme
import com.internship.firstweekapp.retrofit.RetrofitClientImpl
import retrofit2.HttpException
import java.io.IOException

class MemePageSource(private val retrofitClient: RetrofitClientImpl) : PagingSource<Int, Meme>() {

    var filter = ""

    override fun getRefreshKey(state: PagingState<Int, Meme>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meme> {
        return try {

            val position = params.key ?: 1
            val memeList = retrofitClient.getAllMemeses(position)

            val sorted = sortArr(memeList)

            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(
                data = sorted.toList(), prevKey, position + 1
            )
        } catch (exception: IOException) {

            return LoadResult.Error(exception)
        } catch (exception: HttpException) {

            return LoadResult.Error(exception)
        }
    }

    private fun sortArr(arr: Array<Meme>): Array<Meme> {
        val newArr = arrayListOf<Meme>()

        arr.forEach {
            if (it.tags.contains(filter))
                newArr.add(it)
        }
        return newArr.toTypedArray()
    }

    fun setFilterText(text: String) {
        filter = text
    }
}