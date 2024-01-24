package com.gdd.data.retrofit

import com.gdd.data.model.DefaultResponse
import com.squareup.moshi.rawType
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Parameter
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory: CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        println("annotations : ${annotations.size}")
        println("returnType : $returnType")
        if (Call::class.java != getRawType(returnType) || returnType !is ParameterizedType) return null

        val responseType = getParameterUpperBound(0,returnType)
        println(responseType)

        if (Result::class.java != responseType.rawType || responseType !is ParameterizedType) return null

        val bodyType = getParameterUpperBound(0,responseType)
        println(bodyType)

        return NetworkResponseAdapter<Any>(bodyType)
    }
}