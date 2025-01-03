package com.ipa.dev.gamethrones.data.remote.api

import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object ApiClient {

    private val okHttpClient = OkHttpClient.Builder()
        //.addInterceptor(::apiKeyAsQuery)
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://thronesapi.com")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<ApiService>()
}

//METODO PARA AGREGAR API KEY A CADA PETICION
private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain.request()   //capturamos request
        .newBuilder()  //creamos una nueva
        .url(           //entramos en la url
            chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", "API_KEY_AQUI") //Agregamos el API Key en el request
                .build()
        )
        .build()
)