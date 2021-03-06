package com.example.movieapp.ui.nowplaying_movies

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
import com.example.movieapp.databinding.ItemNowplayingAllBinding
import com.example.movieapp.model.Result
import com.example.movieapp.ui.popular_movies.ResultDiffCallBack

class NowPlayingMoviesPagerAdapter : PagedListAdapter<Result, NowPlayingMoviesPagerAdapter.NowPlayingViewHolder>(ResultDiffCallBack()) {

    inner class NowPlayingViewHolder(var binding: ItemNowplayingAllBinding) : RecyclerView.ViewHolder(binding.root){


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

    private fun createOnClickListener(binding: ItemNowplayingAllBinding ,movieId: Int , movieTitle: String? ,moviePoster:String?): View.OnClickListener {
        return View.OnClickListener {
            val extras = FragmentNavigatorExtras(
                binding.moviePoster to "imageView $movieId",
                binding.movieTitle to "textView $movieId"
            )


            val action = NowplayingMoviesFragmentDirections.actionNowplayingMoviesFragmentToMovieDetailFragment(movieId,movieTitle,moviePoster)


            it.findNavController().navigate(action,extras)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingMoviesPagerAdapter.NowPlayingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding : ItemNowplayingAllBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_nowplaying_all,parent,false)
        return NowPlayingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NowPlayingMoviesPagerAdapter.NowPlayingViewHolder,
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