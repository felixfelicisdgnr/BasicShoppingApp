package com.doganur.shoppingapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.doganur.shoppingapp.common.ApiUtils
import com.doganur.shoppingapp.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {

    private val service = ApiUtils.productApiService

    val productList = MutableLiveData<List<Product>?>()

    fun getProducts() {

        service.getProducts().enqueue(object : Callback<List<Product>> {

            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.body().isNullOrEmpty()) {
                    productList.value = null

                } else {
                    productList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                //internette vs bir sıkıntı var ise bu zamanda başarısız olmuş olur. Buraya girer. Veritabanı ile bağlantı sağlanmamış ise falan
                Log.d("Failure", t.message.orEmpty())
            }
        })
    }

    fun searchProduct(query : String) {

        service.searchProduct(query).enqueue(object : Callback<List<Product>>{
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.body().isNullOrEmpty()) {
                    productList.value = null
                } else {
                    productList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d("Failure", t.message.orEmpty())
            }
        })
    }
}