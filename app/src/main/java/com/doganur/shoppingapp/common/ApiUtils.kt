package com.doganur.shoppingapp.common

import com.doganur.shoppingapp.common.Constants.BASE_URL
import com.doganur.shoppingapp.data.source.remote.ProductApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val productApiService: ProductApiService by lazy { retrofit.create(ProductApiService::class.java) }

}