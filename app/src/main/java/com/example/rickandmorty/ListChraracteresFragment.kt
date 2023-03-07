package com.example.rickandmorty

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rickandmorty.databinding.FragmentListChraracteresBinding
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import android.os.Bundle as Bundle1
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

class ListChraracteresFragment : Fragment() {

    val viewModel: RickAndMortyViewModel = RickAndMortyViewModel(rickAndMortyApiService = RickAndMortyService().service)
    val ricAndMortyAdapter: RickAndMortyAdapter = RickAndMortyAdapter(emptyList())

    private var _binding: FragmentListChraracteresBinding? = null
    private val binding get() = _binding!!

    lateinit var listItemsRickAndMorty: List<ItemRickAndMorty>

    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle1?
    ): View? {
        _binding = FragmentListChraracteresBinding.inflate(inflater, container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: android.os.Bundle?) {
        initRecyclerView()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun initRecyclerView() {

        binding.rvRickAndMorty.layoutManager = LinearLayoutManager(context)
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
        viewModel.status.observe(viewLifecycleOwner) {
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




