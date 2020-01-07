package com.example.movieapp.ui.upcoming_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemUpcomingAllBinding
import com.example.movieapp.model.Result

import com.example.movieapp.ui.popular_movies.ResultDiffCallBack

class UpcomingMoviesPagedAdapter : PagedListAdapter<Result, UpcomingMoviesPagedAdapter.UpcomingViewHolder>(ResultDiffCallBack()) {

    inner class UpcomingViewHolder(var binding: ItemUpcomingAllBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(listener: View.OnClickListener, movie: Result){



            with(binding) {
                this.movie = movie
                executePendingBindings()
            }
            binding.root.setOnClickListener(listener)
        }

    }

    private fun createOnClickListener(movieId: Int): View.OnClickListener {
        return View.OnClickListener {


            val action = UpcomingMoviesFragmentDirections.actionUpcomingMoviesFragmentToMovieDetailFragment(movieId)


            it.findNavController().navigate(action)
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
                bind( createOnClickListener(movie?.id!!),movie)
            }
        }
    }
}