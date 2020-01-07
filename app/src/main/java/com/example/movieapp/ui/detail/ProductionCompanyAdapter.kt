package com.example.movieapp.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMoviePopularBinding
import com.example.movieapp.databinding.ItemProductioncompanyBinding
import com.example.movieapp.model.ProductionCompany
import com.example.movieapp.model.Result
import com.example.movieapp.ui.discover.DiscoverFragmentDirections

class ProductionCompanyAdapter ( var productions: List<ProductionCompany>) :
    RecyclerView.Adapter<ProductionCompanyAdapter.ProductionsViewHolder> (){


    inner class  ProductionsViewHolder(var binding: ItemProductioncompanyBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind( company: ProductionCompany){
            binding.setVariable(BR.company,company)



        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionsViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemProductioncompanyBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_productioncompany,parent,false)
        return ProductionsViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return productions.size
    }

    override fun onBindViewHolder(holder: ProductionsViewHolder, position: Int) {
        holder.bind(productions[position])
    }
}