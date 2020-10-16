package com.sample.rx.data.source.remote

import ProductRemoteDataSource
import com.sample.rx.data.source.remote.Api
import com.sample.rx.data.source.remote.ProductModel

import io.reactivex.Single

class SampleRemoteDataSource(private val api: Api) : ProductRemoteDataSource {

    override fun getProducts(): Single<ProductModel> = api.getProducts()

    companion object {
        const val NAME = "Sample"
    }
}