package com.example.rickandmorty


import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.databinding.ListItemBinding
import com.squareup.picasso.Picasso



class RickAndMortyAdapter(var listItemsRickAndMorty: List<ItemRickAndMorty>): RecyclerView.Adapter<RickAndMortyAdapter.RickAndMortyHolder>() {

    class RickAndMortyHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)

        fun render(item: ItemRickAndMorty) {
            binding.tvNameRickAndMorty.text = item.name
            Picasso.get().load(item.photo).into(binding.ivRickAndMorty)
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





