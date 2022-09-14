package com.example.toffeelite.util.extension

import com.example.toffeelite.data.model.response.Results
import retrofit2.Call
import retrofit2.Response

inline fun <T> Call<T>.request(crossinline onResult: (response: Results<T>) -> Unit) {
    enqueue(object : retrofit2.Callback<T>{
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful){
                onResult(Results.Success(response))
            }else{
                onResult(Results.Failure(response))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onResult(Results.Exception(t))
        }
    })
}