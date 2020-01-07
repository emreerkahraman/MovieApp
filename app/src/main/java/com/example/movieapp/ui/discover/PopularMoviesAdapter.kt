package com.example.movieapp.ui.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMoviePopularBinding
import com.example.movieapp.model.Result

class PopularMoviesAdapter( var popularMovies: List<Result>) :RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder> (){


    inner class  PopularMoviesViewHolder(var binding:ItemMoviePopularBinding ) : RecyclerView.ViewHolder(binding.root){

        fun bind(listener: View.OnClickListener,movie: Result){
            binding.setVariable(BR.movie,movie)
            binding.root.setOnClickListener(listener)


        }

    }

    private fun createOnClickListener(movieId: Int?): View.OnClickListener {
        return View.OnClickListener {
    val action = DiscoverFragmentDirections
                .actionDiscoverFragmentToMovieDetailFragment( movieId!!)

            it.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        var layoutInflater =LayoutInflater.from(parent.context)
        val binding: ItemMoviePopularBinding =DataBindingUtil.inflate(layoutInflater, R.layout.item_movie_popular,parent,false)
        return PopularMoviesViewHolder(binding)

    }

    override fun getItemCount(): Int {
     return popularMovies.size
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(createOnClickListener(popularMovies[position].id),popularMovies[position])
    }
}