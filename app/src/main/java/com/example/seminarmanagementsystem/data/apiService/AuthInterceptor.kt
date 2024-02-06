package com.example.seminarmanagementsystem.data.apiService

import android.util.Log
import com.example.seminarmanagementsystem.domain.tokenPreference.TokenPreference
import com.example.seminarmanagementsystem.utils.Constants.TAG
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * if we have to add dynamic header on different routes and have to pass authToken as a header
 * then we use Interceptor, Interceptor catches our request and add header to it and then
 * again build the request.
 */
class AuthInterceptor @Inject constructor(
    private val tokenPreference: TokenPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = tokenPreference.readToken()
        Log.d(TAG, "intercept: $token")
            request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}