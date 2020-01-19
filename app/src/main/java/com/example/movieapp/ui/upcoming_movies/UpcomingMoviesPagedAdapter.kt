package com.example.movieapp.ui.upcoming_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemUpcomingAllBinding
import com.example.movieapp.model.Result
import com.example.movieapp.ui.popular_movies.ResultDiffCallBack

class UpcomingMoviesPagedAdapter : PagedListAdapter<Result, UpcomingMoviesPagedAdapter.UpcomingViewHolder>(ResultDiffCallBack()) {

    inner class UpcomingViewHolder(var binding: ItemUpcomingAllBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(listener: View.OnClickListener, movie: Result){

            ViewCompat.setTransitionName(binding.moviePoster, "imageView ${movie.id}")
            ViewCompat.setTransitionName(binding.movieTitle, "textView ${movie.id}")



            with(binding) {
                this.movie = movie
                executePendingBindings()
            }
            binding.root.setOnClickListener(listener)
        }

    }

    private fun createOnClickListener(binding: ItemUpcomingAllBinding, movieId: Int, movieTitle: String, moviePoster:String): View.OnClickListener {
        return View.OnClickListener {

            val extras = FragmentNavigatorExtras(
                binding.moviePoster to "imageView $movieId",
                binding.movieTitle to "textView $movieId"
            )


            val action = UpcomingMoviesFragmentDirections.actionUpcomingMoviesFragmentToMovieDetailFragment(movieId,movieTitle,moviePoster)


            it.findNavController().navigate(action,extras)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingMoviesPagedAdapter.UpcomingViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val  binding : ItemUpcomingAllBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_upcoming_all,parent,false)
        return UpcomingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UpcomingMoviesPagedAdapter.UpcomingViewHolder,
        position: Int
    ) {

        getItem(position).let { movie ->
            with(holder) {
                itemView.tag = movie
                bind( createOnClickListener(holder.binding,movie?.id!!,movie.title!!,movie.posterPath!!),movie)
            }
        }
    }
}