package com.example.movieapp.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Genre
import com.example.movieapp.model.ProductionCompany
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapter ( var genreList: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder> (){


    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var chip = itemView.genre

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.item_genre,parent,false)
        return GenreViewHolder(view);
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.chip.text=genreList[position].name
    }


}