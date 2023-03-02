package com.example.rickandmorty

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.rickandmorty.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
//import androidx.activity.viewModels
import com.example.rickandmorty.RickAndMortyService
import androidx.lifecycle.viewmodel.viewModelFactory

import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.*
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
                RickAndMortyStatus.LOADING -> load()
                    //Toast.makeText(this, "Cargando imágenes", Toast.LENGTH_SHORT).show()
                RickAndMortyStatus.ERROR -> error()
                RickAndMortyStatus.DONE -> done()
            }


        }
}
    private fun load() {
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun error(){
        Toast.makeText(this, "Error, inténtalo de nuevo", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility = View.GONE
    }
    private fun done() {
        ricAndMortyAdapter.listItemsRickAndMorty = viewModel.characteres.value!!
        binding.progressBar.visibility = View.GONE
        ricAndMortyAdapter.notifyDataSetChanged()
    }
}