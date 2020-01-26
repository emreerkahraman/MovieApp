package com.example.movieapp.ui.popular_movies

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
import com.example.movieapp.databinding.ItemPopularAllBinding
import com.example.movieapp.model.Result

class PopularMoviesPagedAdapter :PagedListAdapter<Result,PopularMoviesPagedAdapter.PopularViewHolder>(ResultDiffCallBack()) {

    inner class PopularViewHolder(var binding: ItemPopularAllBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(listener: View.OnClickListener,movie: Result){

            ViewCompat.setTransitionName(binding.moviePoster, "imageView ${movie.id}")
            ViewCompat.setTransitionName(binding.movieTitle, "textView ${movie.id}")



            with(binding) {
                this.movie = movie
                executePendingBindings()
            }
            binding.root.setOnClickListener(listener)
        }

    }

        private fun createOnClickListener(binding: ItemPopularAllBinding, movieId: Int, movieTitle: String?, moviePoster:String?): View.OnClickListener {
        return View.OnClickListener {

            val extras = FragmentNavigatorExtras(
                binding.moviePoster to "imageView $movieId",
                binding.movieTitle to "textView $movieId"
            )


            val action = PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailFragment(movieId,movieTitle,moviePoster)


            it.findNavController().navigate(action ,extras)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMoviesPagedAdapter.PopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding : ItemPopularAllBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_popular_all,parent,false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PopularMoviesPagedAdapter.PopularViewHolder,
        position: Int
    ) {

        getItem(position).let { movie ->
            with(holder) {
                itemView.tag = movie
                if (movie != null) {
                    bind( createOnClickListener(holder.binding,movie.id!!,movie.title,movie.posterPath),movie)
                }

            }
        }
    }
}