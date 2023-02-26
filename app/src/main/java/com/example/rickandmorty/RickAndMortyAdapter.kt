package com.example.rickandmorty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ListItemBinding


class RickAndMortyAdapter(val listItemsRickAndMorty: List<ItemRickAndMorty>): RecyclerView.Adapter<RickAndMortyAdapter.RickAndMortyHolder>() {

    class RickAndMortyHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)

        fun render(item: ItemRickAndMorty) {
            binding.tvNameRickAndMorty.text = item.name

        }
    }

    override fun getItemCount(): Int = listItemsRickAndMorty.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RickAndMortyHolder(layoutInflater.inflate(R.layout.list_item, parent, false))

    }

    override fun onBindViewHolder(holder: RickAndMortyHolder, position: Int) {
        holder.render(listItemsRickAndMorty[position])
    }

}



