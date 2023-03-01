package com.example.rickandmorty

import com.squareup.moshi.Json

data class CharactersResponse (@Json(name = "results") val results: List<ItemRickAndMorty>)