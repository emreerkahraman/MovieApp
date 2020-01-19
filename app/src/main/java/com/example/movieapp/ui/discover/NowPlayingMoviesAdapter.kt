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
import com.example.movieapp.databinding.ItemMovieNowplayingBinding
import com.example.movieapp.model.Result

class NowPlayingMoviesAdapter( var nowPlayingMovies: List<Result>) : RecyclerView.Adapter<NowPlayingMoviesAdapter.NowPlayingMoviesViewHolder> (){


    inner class  NowPlayingMoviesViewHolder(var binding: ItemMovieNowplayingBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(listener: View.OnClickListener,movie: Result){

            ViewCompat.setTransitionName(binding.moviePoster, "imageView ${movie.id}")
            ViewCompat.setTransitionName(binding.movieTitle, "textView ${movie.id}")

            binding.setVariable(BR.movie,movie)
            binding.root.setOnClickListener(listener)
        }

    }

    private fun createOnClickListener(binding: ItemMovieNowplayingBinding ,movieId: Int , movieTitle: String ,moviePoster:String): View.OnClickListener {
        return View.OnClickListener {

            val extras = FragmentNavigatorExtras(
                binding.moviePoster to "imageView $movieId",
                binding.movieTitle to "textView $movieId"


            )

            val action = DiscoverFragmentDirections
                .actionDiscoverFragmentToMovieDetailFragment( movieId,movieTitle,moviePoster)

            it.findNavController().navigate(action,extras)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieNowplayingBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_movie_nowplaying,parent,false)
        return NowPlayingMoviesViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return nowPlayingMovies.size
    }

    override fun onBindViewHolder(holder: NowPlayingMoviesViewHolder, position: Int) {
        holder.bind(createOnClickListener(holder.binding,nowPlayingMovies[position].id!!,nowPlayingMovies[position].title!!,nowPlayingMovies[position].posterPath!!),nowPlayingMovies[position])
    }
}