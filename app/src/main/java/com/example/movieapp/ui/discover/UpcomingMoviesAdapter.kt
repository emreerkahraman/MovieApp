package com.example.movieapp.ui.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMovieUpcomingBinding
import com.example.movieapp.model.Result

class UpcomingMoviesAdapter ( var upcomingMovies: List<Result>) : RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder> (){


    inner class  UpcomingMoviesViewHolder(var binding: ItemMovieUpcomingBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(listener: View.OnClickListener,movie: Result){

            ViewCompat.setTransitionName(binding.moviePoster, "imageView ${movie.id}")
            ViewCompat.setTransitionName(binding.movieTitle, "textView ${movie.id}")

            binding.setVariable(BR.movie,movie)
            binding.root.setOnClickListener(listener)


        }

    }

    private fun createOnClickListener(binding: ItemMovieUpcomingBinding, movieId: Int, movieTitle: String, moviePoster:String): View.OnClickListener {
        return View.OnClickListener {

            val extras = FragmentNavigatorExtras(
                binding.moviePoster to "imageView $movieId",
                binding.movieTitle to "textView $movieId"
            )

            val action = DiscoverFragmentDirections
                .actionDiscoverFragmentToMovieDetailFragment( movieId,movieTitle,moviePoster)

            it.findNavController().navigate(action, extras)
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
        holder.bind(createOnClickListener(holder.binding, upcomingMovies[position].id!!,upcomingMovies[position].title!!,upcomingMovies[position].posterPath!!),upcomingMovies[position])
    }
}