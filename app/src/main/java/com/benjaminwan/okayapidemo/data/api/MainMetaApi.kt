package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.mainmeta.*
import com.benjaminwan.okayapidemo.data.request.mainmeta.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface MainMetaApi {

    @POST("/")
    fun Create(@Body req: MainMetaCreateReq): Observable<MainMetaCreateMsg>

    @POST("/")
    fun Delete(@Body req: MainMetaDeleteReq): Observable<MainMetaDeleteMsg>

    @POST("/")
    fun Get(@Body req: MainMetaGetReq): Observable<MainMetaGetMsg>

    @POST("/")
    fun MultiGet(@Body req: MainMetaMultiGetReq): Observable<MainMetaMultiGetMsg>

    @POST("/")
    fun Update(@Body req: MainMetaUpdateReq): Observable<MainMetaUpdateMsg>

}