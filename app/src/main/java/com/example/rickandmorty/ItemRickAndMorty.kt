package com.example.rickandmorty

import com.squareup.moshi.Json

//data class ItemRickAndMorty (val foto: String, val name: String)
data class ItemRickAndMorty (@Json(name = "image") val photo: String, @Json(name = "name") val name: String)