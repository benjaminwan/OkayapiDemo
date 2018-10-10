package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.mainset.*
import com.benjaminwan.okayapidemo.data.request.mainset.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface MainSetApi {

    @POST("/")
    fun Add(@Body req: MainSetAddReq): Observable<MainSetAddMsg>

    @POST("/")
    fun Clear(@Body req: MainSetClearReq): Observable<MainSetClearMsg>

    @POST("/")
    fun Count(@Body req: MainSetCountReq): Observable<MainSetCountMsg>

    @POST("/")
    fun Delete(@Body req: MainSetDeleteReq): Observable<MainSetDeleteMsg>

    @POST("/")
    fun GetItem(@Body req: MainSetGetItemReq): Observable<MainSetGetItemMsg>

    @POST("/")
    fun GetList(@Body req: MainSetGetListReq): Observable<MainSetGetListMsg>

    @POST("/")
    fun Query(@Body req: MainSetQueryReq): Observable<MainSetQueryMsg>

    @POST("/")
    fun Update(@Body req: MainSetUpdateReq): Observable<MainSetUpdateMsg>

}