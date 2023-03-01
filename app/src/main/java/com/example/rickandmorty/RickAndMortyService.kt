package com.example.rickandmorty
import com.example.rickandmorty.RickAndMortyService.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



class RickAndMortyService {


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
    //service es el objeto de la interface
    val service = retrofit.create(RickAndMortyApiService::class.java)

}


    //define cómo Retrofit se comunica con el servidor web mediante solicitudes HTTP.
    interface RickAndMortyApiService {
        @GET("character")
        suspend fun getItems(): CharactersResponse
    }


//objeto q vas a usar en otros archivos:
//TODO
val rickAndMortyService1 = RickAndMortyService().service



