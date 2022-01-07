package com.app.saveo_task.viewmodel

import androidx.lifecycle.ViewModel
import com.app.saveo_task.repository.Repository


class MovieViewModel(): ViewModel() {
    private val repository= Repository()
    fun showMovie()=repository.getPageList()

}