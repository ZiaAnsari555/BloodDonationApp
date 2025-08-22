package com.yodeck.network

import com.yodeck.models.GetDeviceResponse
import com.yodeck.models.SessionResponse
import com.yodeck.models.base_response.BaseResponse
import com.yodeck.models.getscreen.GetScreenResponse
import com.yodeck.models.movies.MoviesResponse


class BaseRepository {

    private val networkServices = RetrofitInstance.networkServices


    suspend fun login(hashMap: HashMap<String, Any>): SessionResponse {
        return networkServices.login(hashMap)
    }

    suspend fun searchMulti(hashMap: HashMap<String, Any>): MoviesResponse {
        return networkServices.searchMulti(hashMap)
    }

    suspend fun getScreen(hashMap: HashMap<String, Any>): GetScreenResponse {
        return networkServices.getScreen(hashMap)
    }

    suspend fun getLastUpdate(hashMap: HashMap<String, Any>): BaseResponse {
        return networkServices.getLastUpdate(hashMap)
    }

    suspend fun changeUpdateStatus(hashMap: HashMap<String, Any>): BaseResponse {
        return networkServices.changeUpdateStatus(hashMap)
    }

}