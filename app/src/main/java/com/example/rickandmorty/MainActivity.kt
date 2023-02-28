package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
//import androidx.activity.viewModels
import com.example.rickandmorty.RickAndMortyService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.viewModelFactory

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import okhttp3.internal.notify


class MainActivity : AppCompatActivity() {

    val viewModel: RickAndMortyViewModel by viewModels()
    val ricAndMortyAdapter: RickAndMortyAdapter = RickAndMortyAdapter(emptyList())

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initRecyclerView()
        observe()
    }


    fun initRecyclerView() {

        binding.rvRickAndMorty.layoutManager = LinearLayoutManager(this)
        //emptylist pq empieza vacía RickAndMortyViewModel
        binding.rvRickAndMorty.adapter = ricAndMortyAdapter

    }



    fun observe() {
        //observar status(this es elliveovnercicle, el it es el lambda de observer)
        //elije it o la funcion
        // fun newFunStatus(): Observer<RickAndMortyStatus> {
        //        return Observer {
        //
        //        }
        //    }
        viewModel.status.observe(this) {
            when (it) {
                RickAndMortyStatus.LOADING -> Toast.makeText(
                    this,
                    "Cargando imágenes",
                    Toast.LENGTH_SHORT
                ).show()
                RickAndMortyStatus.ERROR -> Toast.makeText(
                    this,
                    "Error, inténtalo de nuevo",
                    Toast.LENGTH_SHORT
                ).show()
                RickAndMortyStatus.DONE -> ricAndMortyAdapter.listItemsRickAndMorty =
                    viewModel.characteres.value!!
            }
            ricAndMortyAdapter.notifyDataSetChanged()
        }


    }



/*
    fun bindStatus() {
        when (status) {

            RickAndMortyStatus.LOADING -> {

                Toast.makeText(this, "Cargando imágenes", Toast.LENGTH_SHORT).show()

            }
            RickAndMortyStatus.ERROR -> {

                Toast.makeText(this, "Ha habido un problema, inténtelo de nuevo", Toast.LENGTH_SHORT).show()

            }

            RickAndMortyStatus.DONE -> {
                binding.tv.text = item.name
                Picasso.get().load(item.photo).into(binding.ivRickAndMorty)
            }
        }

    }
*/
}