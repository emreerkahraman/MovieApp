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
import com.example.movieapp.databinding.ItemMoviePopularBinding
import com.example.movieapp.model.Result

class PopularMoviesAdapter( var popularMovies: List<Result>) :RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder> (){


    inner class  PopularMoviesViewHolder(var binding:ItemMoviePopularBinding ) : RecyclerView.ViewHolder(binding.root){

        fun bind(listener: View.OnClickListener,movie: Result){
            ViewCompat.setTransitionName(binding.moviePoster, "imageView ${movie.id}")
            ViewCompat.setTransitionName(binding.movieTitle, "textView ${movie.id}")

            binding.setVariable(BR.movie,movie)
            binding.root.setOnClickListener(listener)


        }

    }

    private fun createOnClickListener(binding: ItemMoviePopularBinding, movieId: Int, movieTitle: String, moviePoster:String): View.OnClickListener {

        return View.OnClickListener {

            val extras = FragmentNavigatorExtras(
                binding.moviePoster to "imageView $movieId",
                binding.movieTitle to "textView $movieId"
            )

            val action = DiscoverFragmentDirections
                .actionDiscoverFragmentToMovieDetailFragment( movieId,movieTitle,moviePoster )

            it.findNavController().navigate(action,extras)
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
        holder.bind(createOnClickListener(holder.binding,popularMovies[position].id!!,popularMovies[position].title!! ,popularMovies[position].posterPath!!),popularMovies[position])
    }
}