package com.example.movieapp.ui.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMovieUpcomingBinding
import com.example.movieapp.model.Result

class UpcomingMoviesAdapter ( var upcomingMovies: List<Result>) : RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder> (){


    inner class  UpcomingMoviesViewHolder(var binding: ItemMovieUpcomingBinding) : RecyclerView.ViewHolder(binding.root){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieUpcomingBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_movie_upcoming,parent,false)
        return UpcomingMoviesViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return upcomingMovies.size
    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        holder.bind(createOnClickListener(upcomingMovies[position].id),upcomingMovies[position])
    }
}