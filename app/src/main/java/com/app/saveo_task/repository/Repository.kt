package com.app.saveo_task.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.app.saveo_task.pagingSource.MoviePagingSource

class Repository {
    fun getPageList()= Pager(config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MoviePagingSource() }).liveData
}