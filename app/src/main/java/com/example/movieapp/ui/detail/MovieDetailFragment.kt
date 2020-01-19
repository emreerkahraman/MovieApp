package com.example.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.movieapp.BR
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieDetailFragmentBinding
import com.example.movieapp.model.Genre
import com.example.movieapp.model.NetworkResponse
import com.example.movieapp.model.ProductionCompany
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailFragment : Fragment() {
    val viewModel: MovieDetailViewModel by viewModel()

    private val safeArgs : MovieDetailFragmentArgs by navArgs()
    lateinit var binding :MovieDetailFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.movie_detail_fragment,container,false)
        binding.lifecycleOwner =this@MovieDetailFragment



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =safeArgs.movieTitle

        binding.poster=safeArgs.moviePoster
        binding.movieTitle=safeArgs.movieTitle
        ViewCompat.setTransitionName(binding.posterImage, "imageView ${safeArgs.movieId}")
        ViewCompat.setTransitionName(binding.title, "textView ${safeArgs.movieId}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMovie(safeArgs.movieId).observe(viewLifecycleOwner, Observer { response ->

            when (response) {

                is NetworkResponse.Success -> {

                    binding.setVariable(BR.movie,response.data)
                    binding.productionRecyclerView.adapter= ProductionCompanyAdapter(response.data.productionCompanies as List<ProductionCompany>)
                    binding.genreRecyclerView.adapter =GenreAdapter(response.data.genres as List<Genre>)
                }

                is NetworkResponse.Error ->
                    Toast.makeText(activity,response.message, Toast.LENGTH_SHORT).show()
            }
        })



    }



}
