package mykyta.titov.categorychallenge.core.providers

import com.google.gson.Gson
import mykyta.titov.categorychallenge.BuildConfig
import mykyta.titov.categorychallenge.data.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider : Provider<Retrofit>() {

    private val retrofit: Retrofit by lazy {
        val gsonConverterFactory = GsonConverterFactory.create(Gson())
        val authInterceptor = AuthInterceptor()
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().run {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(authInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
            }
            build()
        }

        Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }

    override fun get(): Retrofit = retrofit
}

private const val CONNECT_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L