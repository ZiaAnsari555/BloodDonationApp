package com.yodeck.network

import com.yodeck.models.AddDonorResponse
import com.yodeck.models.GetDeviceResponse
import com.yodeck.models.SessionResponse
import com.yodeck.models.base_response.BaseResponse
import com.yodeck.models.donors_response.DonorsResponse
import com.yodeck.models.getscreen.GetScreenResponse
import com.yodeck.models.movies.MoviesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.http.*

interface NetworkServices {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@FieldMap hashMap: HashMap<String, Any>): SessionResponse

    @FormUrlEncoded
    @POST("find/donors")
    suspend fun findDonors(@FieldMap hashMap: HashMap<String, Any>): DonorsResponse

    @Multipart
    @POST("add/donor")
    suspend fun addDonor(
        @Part image: MultipartBody.Part,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): retrofit2.Response<AddDonorResponse>

    @Multipart
    @POST("add/donor")
    suspend fun addDonor(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): retrofit2.Response<AddDonorResponse>


    @FormUrlEncoded
    @POST("last-update")
    suspend fun getLastUpdate(@FieldMap hashMap: HashMap<String, Any>): BaseResponse

    @FormUrlEncoded
    @POST("change-update-status")
    suspend fun changeUpdateStatus(@FieldMap hashMap: HashMap<String, Any>): BaseResponse


}