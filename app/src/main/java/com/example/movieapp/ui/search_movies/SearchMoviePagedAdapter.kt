package com.example.movieapp.ui.search_movies

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
import com.example.movieapp.databinding.ItemMovieSearchBinding
import com.example.movieapp.model.Result
import com.example.movieapp.ui.popular_movies.ResultDiffCallBack

class SearchMoviePagedAdapter : PagedListAdapter<Result, SearchMoviePagedAdapter.SearchViewHolder>(ResultDiffCallBack()) {

    inner class SearchViewHolder(var binding:  ItemMovieSearchBinding) : RecyclerView.ViewHolder(binding.root){


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

    private fun createOnClickListener(binding: ItemMovieSearchBinding, movieId: Int, movieTitle: String?, moviePoster:String?): View.OnClickListener {
        return View.OnClickListener {

            val extras = FragmentNavigatorExtras(
                binding.moviePoster to "imageView $movieId",
                binding.movieTitle to "textView $movieId"
            )


            val action = SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieDetailFragment(movieId,movieTitle,moviePoster)



            it.findNavController().navigate(action ,extras)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchMoviePagedAdapter.SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding : ItemMovieSearchBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_movie_search,parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchMoviePagedAdapter.SearchViewHolder,
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