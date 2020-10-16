package com.sample.rx.data.source.remote

import io.reactivex.Single

interface Api {

    @GET("api/shop/productsXml")
    @XML
    fun getProducts(): Single<ProductUvenco>

}