package com.app.saveo_task.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.saveo_task.R
import com.app.saveo_task.databinding.ItemLayoutBinding
import com.app.saveo_task.remote.interfaces.OnItemClicked
import com.bumptech.glide.Glide
import com.app.saveo_task.remote.response.Result


class MovieAdapter(    private val onItemClicked: OnItemClicked
): PagingDataAdapter<Result, MovieAdapter.MovieViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem:Result): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
        return MovieViewHolder(view,onItemClicked)
    }


    class MovieViewHolder(
        private val itemLayoutBinding: ItemLayoutBinding,
        private val onItemClicked: OnItemClicked
    ) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun onBind(resultModel: Result?) {
            itemLayoutBinding.data = resultModel
            itemLayoutBinding.onItemClicked = onItemClicked

            //used for setting up the image prachi
            Glide.with(itemLayoutBinding.moviepost)
                .load("https://image.tmdb.org/t/p/w500/" + resultModel?.posterPath)
                .into(itemLayoutBinding.moviepost)
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        (holder).onBind(getItem(position))

    }
}