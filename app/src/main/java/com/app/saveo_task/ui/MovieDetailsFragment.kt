package com.app.saveo_task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.saveo_task.R
import com.app.saveo_task.databinding.FragmentMovieDetailsragmentBinding
import com.bumptech.glide.Glide
import com.app.saveo_task.remote.response.Result


class MovieDetailsragment : Fragment(R.layout.fragment_movie_detailsragment) {
lateinit var movieDetailsragmentBinding: FragmentMovieDetailsragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        movieDetailsragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detailsragment, container, false)
        return movieDetailsragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resultx: Result? = arguments?.getSerializable("result") as Result?

        if (resultx != null) {
            settingDetails(resultx)
        }
    }
    private fun settingDetails(result: Result) {
        movieDetailsragmentBinding.movieTitle.text = result.title
        movieDetailsragmentBinding.movieLanguage.text = result.originalLanguage
        movieDetailsragmentBinding.moviePopularity.text = result.popularity.toString()
        movieDetailsragmentBinding.movieReleaseDate.text = result.releaseDate
        movieDetailsragmentBinding.movieOverview.text = result.overview
        val moviePosterURL = "https://image.tmdb.org/t/p/w500/" + result.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(movieDetailsragmentBinding.ivMoviePoster);
    }
}