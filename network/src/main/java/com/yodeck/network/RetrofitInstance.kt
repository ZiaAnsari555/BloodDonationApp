package com.yodeck.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(createOkHttpClient()).build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val httpClient = OkHttpClient.Builder()

        httpClient.readTimeout(40, TimeUnit.SECONDS)
        httpClient.connectTimeout(20, TimeUnit.SECONDS)
        if (Debugger.IS_DEVELOPMENT_MODE) {
            httpClient.addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        httpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYzUxNzg4ZjA0ZGUxNmMyYjY1NjM1OGQ3NGZlMmJiNiIsIm5iZiI6MTc0ODM2NzYxMi40MzUwMDAyLCJzdWIiOiI2ODM1ZjhmYzE1NWJmOWQ5MzU4Mzg1ZWEiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.pcX0vDCqn0SAoYFSazbE7ZNxUtdOYbDb-EkCMmmCrjI"
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                val response = chain.proceed(request);
                response.code//status code
//                val res = JSONObject(response.body?.string()!!)
//                if (res.has("isLoggedIn") && res.get("isLoggedIn") == false) {
//                    EventBus.getDefault().post(SessionExpired)
//                }
//                if (response.code == 401){
//                    EventBus.getDefault().post(SessionExpired)
//                }
                return response
            }

        })
        return httpClient.build()
    }

    val networkServices: NetworkServices by lazy {
        retrofit.create(NetworkServices::class.java)
    }
}

object SessionExpired
object RefreshApp