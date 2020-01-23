package com.example.movieapp.ui.upcoming_movies

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
import kotlinx.android.synthetic.main.fragment_upcoming_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UpcomingMoviesFragment : Fragment() {

    val viewModel: UpcomingMoviesViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingMoviesAllRecyclerView.addItemDecoration(SpacesItemDecoration(16))
        upcomingMoviesAllRecyclerView.layoutManager = GridAutoFitLayoutManager(activity!!,342.dp)

        viewModel.pagedMovieList.observe(viewLifecycleOwner, Observer {
            upcomingMoviesAllRecyclerView.adapter = UpcomingMoviesPagedAdapter().apply {
                submitList(it)
            }
        })
    }


}