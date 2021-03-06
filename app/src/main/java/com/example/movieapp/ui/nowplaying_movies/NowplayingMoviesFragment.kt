package com.example.movieapp.ui.nowplaying_movies

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
import kotlinx.android.synthetic.main.fragment_nowplaying_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NowplayingMoviesFragment : Fragment() {


    val viewModel: NowplayingMoviesViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {






        return inflater.inflate(R.layout.fragment_nowplaying_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingMoviesAllRecyclerView.addItemDecoration(SpacesItemDecoration(16))

        nowPlayingMoviesAllRecyclerView.layoutManager = GridAutoFitLayoutManager(activity!!,342.dp)




        viewModel.pagedMovieList.observe(viewLifecycleOwner, Observer {

            nowPlayingMoviesAllRecyclerView.adapter = NowPlayingMoviesPagerAdapter().apply {
                submitList(it)
            }
        })
    }

}