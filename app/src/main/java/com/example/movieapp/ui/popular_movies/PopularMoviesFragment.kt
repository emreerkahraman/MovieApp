package com.example.movieapp.ui.popular_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.utils.GridAutoFitLayoutManager
import com.example.movieapp.utils.SpacesItemDecoration
import com.example.movieapp.utils.dp
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PopularMoviesFragment : Fragment() {

    val viewModel: PopularMoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularMoviesAllRecyclerView.addItemDecoration(SpacesItemDecoration(16))

        popularMoviesAllRecyclerView.layoutManager = GridAutoFitLayoutManager(activity!!,342.dp)

        viewModel.pagedMovieList.observe(viewLifecycleOwner, Observer {
           popularMoviesAllRecyclerView.adapter =PopularMoviesPagedAdapter().apply {
                submitList(it)
            }
        })
    }


}