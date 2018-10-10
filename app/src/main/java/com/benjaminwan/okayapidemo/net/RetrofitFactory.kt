package com.benjaminwan.okayapidemo.net

import com.benjaminwan.okayapidemo.common.SERVER_ADDRESS
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import okio.BufferedSink
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

object RetrofitFactory {
    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {
        interceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .build()
            val sink: BufferedSink = Buffer()
            request.body().writeTo(sink)
            val bodyStr = sink.buffer().readString(Charset.forName("utf-8"))
            //mHttpRequestListener?.OnHttpRequest(bodyStr)//取得发送内容
            mHttpRequestListener?.invoke(bodyStr)
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()

    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .build()

    }

    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    interface OnHttpRequestListener {
        //获取发送的request信息，用于调试显示
        fun OnHttpRequest(msg: String)
    }

    //发送request事件
    //private var mHttpRequestListener: OnHttpRequestListener? = null

    //设置发送request事件
/*    fun setOnHttpRequestListener(onHttpRequestListener: OnHttpRequestListener) {
        mHttpRequestListener = onHttpRequestListener
    }*/

    private var mHttpRequestListener: ((String) -> Unit)? = null

    fun setOnHttpRequestListener(listener: (String) -> Unit) {
        mHttpRequestListener = listener
    }

}