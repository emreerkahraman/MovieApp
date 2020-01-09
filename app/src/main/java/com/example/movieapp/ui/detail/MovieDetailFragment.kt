package com.example.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieapp.BR
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieDetailFragmentBinding
import com.example.movieapp.model.Genre
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMovie(safeArgs.movieId).observe(viewLifecycleOwner, Observer {

            (activity as AppCompatActivity).supportActionBar?.title =it.title
            binding.setVariable(BR.movie,it)
            binding.productionRecyclerView.adapter= ProductionCompanyAdapter(it.productionCompanies as List<ProductionCompany>)
            binding.genreRecyclerView.adapter =GenreAdapter(it.genres as List<Genre>)


        })

    }



}
