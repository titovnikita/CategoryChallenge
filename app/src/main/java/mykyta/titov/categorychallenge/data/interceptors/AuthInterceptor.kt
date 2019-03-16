package mykyta.titov.categorychallenge.data.interceptors

import mykyta.titov.categorychallenge.BuildConfig.ACCESS_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
                .header(HEADER_NAME, HEADER_VALUE).build()
        return chain.proceed(authenticatedRequest)
    }

}

private const val HEADER_NAME = "Authorization"
private const val HEADER_VALUE = "Client-ID $ACCESS_KEY"