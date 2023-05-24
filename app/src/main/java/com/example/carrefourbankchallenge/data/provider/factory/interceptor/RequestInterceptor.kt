package com.example.carrefourbankchallenge.data.provider.factory.interceptor

import com.example.carrefourbankchallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHORIZATION = "Authorization"
private const val BEARER = "Bearer "
private const val USER_AGENT = "User-Agent"

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request().newBuilder()
                .addHeader(
                    name = AUTHORIZATION,
                    value = BEARER + BuildConfig.GITHUB_TOKEN
                )
                .removeHeader(USER_AGENT)
                .build()
        )
    }
}