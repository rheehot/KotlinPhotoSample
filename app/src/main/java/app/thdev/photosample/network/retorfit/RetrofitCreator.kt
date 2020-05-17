package app.thdev.photosample.network.retorfit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by tae-hwan on 7/26/16.
 */
fun <T> createRetrofit(cls: Class<T>, baseUrl: String): T {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(createOkHttpClient())
        .build()

    return retrofit.create(cls)
}

private fun createOkHttpClient(): OkHttpClient {
    val builder: OkHttpClient.Builder = OkHttpClient.Builder()

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    builder.addInterceptor(interceptor)
    return builder.build()
}