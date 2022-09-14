package com.example.toffeelite.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.toffeelite.data.model.response.OmdbPageListResponse
import com.example.toffeelite.util.extension.onException
import com.example.toffeelite.util.extension.onFailure
import com.example.toffeelite.util.extension.onSuccess
import com.example.toffeelite.util.extension.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

abstract class BaseRepository {
    //Initiate api call for fetch list type api with pages
    //Takes 3 parameters: a retrofit call, a livedata to watch for, an errortext function to distribute errors
    protected suspend fun <Response : OmdbPageListResponse<ListType>, ListType> loadPageListCall(
        call: () -> Call<Response>,
        result: MutableLiveData<List<ListType>>,
        errorText: (String) -> Unit
    ) =
        withContext(Dispatchers.IO){
            call().request { response ->
                response.onSuccess { data?.let { result.postValue((it).search) } }
                response.onFailure { message?.let{ errorText(it)} }
                response.onException { message?.let { errorText(it)} }
            }
            result.apply { postValue(null) }
        }
}