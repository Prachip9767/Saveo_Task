package com.app.saveo_task.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.saveo_task.di.Network
import com.app.saveo_task.remote.response.Result


class MoviePagingSource : PagingSource<Int, Result>(){

    private val apiCall= Network.getApiService()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageNumber=params.key ?: 1
        val responseDTO=apiCall.getInstance(Network.API_KEY,pageNumber)
        val resultList:List<Result> = responseDTO.results

        return try {
            LoadResult.Page(data = resultList, prevKey = null,nextKey = if (resultList.isEmpty()) null else pageNumber+1)
        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

}