package com.example.movieapp.ui.popular_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemPopularAllBinding
import com.example.movieapp.model.Result

class PopularMoviesPagedAdapter :PagedListAdapter<Result,PopularMoviesPagedAdapter.PopularViewHolder>(ResultDiffCallBack()) {

    inner class PopularViewHolder(var binding: ItemPopularAllBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(listener: View.OnClickListener,movie: Result){



            with(binding) {
                this.movie = movie
                executePendingBindings()
            }
            binding.root.setOnClickListener(listener)
        }

    }

        private fun createOnClickListener(movieId: Int): View.OnClickListener {
        return View.OnClickListener {


            val action = PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailFragment(movieId)


            it.findNavController().navigate(action)
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
                bind( createOnClickListener(movie?.id!!),movie)
            }
        }
    }
}