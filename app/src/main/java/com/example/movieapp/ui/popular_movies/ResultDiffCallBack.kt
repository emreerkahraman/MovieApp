package com.example.movieapp.ui.popular_movies

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.model.Result

class ResultDiffCallBack : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}