package com.app.saveo_task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.app.saveo_task.R
import com.app.saveo_task.databinding.FragmentHomeBinding
import com.app.saveo_task.remote.interfaces.OnItemClicked
import com.app.saveo_task.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.app.saveo_task.remote.response.Result


class HomeFragment : Fragment() ,OnItemClicked{
lateinit var homeBinding: FragmentHomeBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        setAdapter()
        viewModel.showMovie().observe(viewLifecycleOwner,{
            it?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    movieAdapter.submitData(it)
                }
            }
        })
    }
    private fun setAdapter() {
        movieAdapter = MovieAdapter(this)
        val gridLayoutManager = GridLayoutManager(requireContext(),3)
        recyclerView.apply {
            layoutManager = gridLayoutManager
            this.adapter = movieAdapter
        }
    }


    override fun onClickItem(result: Result?) {
        val bundle = Bundle()
        bundle.putSerializable("result",result)
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_movieDetailsragment, bundle)    }
}