package com.yodeck.network

import com.yodeck.models.GetDeviceResponse
import com.yodeck.models.SessionResponse
import com.yodeck.models.base_response.BaseResponse
import com.yodeck.models.getscreen.GetScreenResponse
import com.yodeck.models.movies.MoviesResponse
import retrofit2.http.*

interface NetworkServices {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@FieldMap hashMap: HashMap<String, Any>): SessionResponse

    @GET("search/multi")
    suspend fun searchMulti(@QueryMap hashMap: HashMap<String, Any>): MoviesResponse

    @FormUrlEncoded
    @POST("get-screen")
    suspend fun getScreen(@FieldMap hashMap: HashMap<String, Any>): GetScreenResponse

    @FormUrlEncoded
    @POST("last-update")
    suspend fun getLastUpdate(@FieldMap hashMap: HashMap<String, Any>): BaseResponse

    @FormUrlEncoded
    @POST("change-update-status")
    suspend fun changeUpdateStatus(@FieldMap hashMap: HashMap<String, Any>): BaseResponse


}