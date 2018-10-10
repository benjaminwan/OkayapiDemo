package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.usermeta.*
import com.benjaminwan.okayapidemo.data.request.usermeta.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserMetaApi {

    @POST("/")
    fun Create(@Body req: UserMetaCreateReq): Observable<UserMetaCreateMsg>

    @POST("/")
    fun Delete(@Body req: UserMetaDeleteReq): Observable<UserMetaDeleteMsg>

    @POST("/")
    fun Get(@Body req: UserMetaGetReq): Observable<UserMetaGetMsg>

    @POST("/")
    fun MultiGet(@Body req: UserMetaMultiGetReq): Observable<UserMetaMultiGetMsg>

    @POST("/")
    fun Update(@Body req: UserMetaUpdateReq): Observable<UserMetaUpdateMsg>

}