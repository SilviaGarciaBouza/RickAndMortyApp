package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.rickandmorty.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.rickandmorty.RickAndMortyAdapter.RickAndMortyHolder



class MainActivity : AppCompatActivity() {

        private lateinit var navController: NavController

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            setupActionBarWithNavController(this, navController)
        }

        override fun onSupportNavigateUp(): Boolean {
            return navController.navigateUp() || super.onSupportNavigateUp()
        }
    }






























/*
val viewModel: RickAndMortyViewModel = RickAndMortyViewModel(rickAndMortyApiService = RickAndMortyService().service)
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
    binding.lLRecargar.visibility = View.GONE
}
private fun error(){
   // Toast.makeText(this, "Error, inténtalo de nuevo", Toast.LENGTH_SHORT).show()
    binding.progressBar.visibility = View.GONE
    binding.lLRecargar.visibility = View.VISIBLE
    val butonRecharge: Button = binding.bRecargar
    butonRecharge.setOnClickListener{ viewModel.getCharacteres() }
}
private fun done() {
    ricAndMortyAdapter.listItemsRickAndMorty = viewModel.characteres.value!!
    binding.progressBar.visibility = View.GONE
    ricAndMortyAdapter.notifyDataSetChanged()
    binding.lLRecargar.visibility = View.GONE
}
}
*/
