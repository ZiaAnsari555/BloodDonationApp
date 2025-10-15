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
import retrofit2.Response


class BaseRepository {

    private val networkServices = RetrofitInstance.networkServices


    suspend fun login(hashMap: HashMap<String, Any>): SessionResponse {
        return networkServices.login(hashMap)
    }

    suspend fun getDonors(hashMap: HashMap<String, Any>): DonorsResponse {
        return networkServices.findDonors(hashMap)
    }

    suspend fun  addDonor(multipartBody: MultipartBody.Part, requestBody: Map<String, RequestBody>): Response<AddDonorResponse> {
        return networkServices.addDonor(multipartBody, requestBody)
    }


    suspend fun  addDonor(requestBody: Map<String, RequestBody>): Response<AddDonorResponse> {
        return networkServices.addDonor(requestBody)
    }

    suspend fun getLastUpdate(hashMap: HashMap<String, Any>): BaseResponse {
        return networkServices.getLastUpdate(hashMap)
    }

    suspend fun changeUpdateStatus(hashMap: HashMap<String, Any>): BaseResponse {
        return networkServices.changeUpdateStatus(hashMap)
    }

}