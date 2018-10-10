package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.userset.*
import com.benjaminwan.okayapidemo.data.request.userset.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserSetApi {

    @POST("/")
    fun Add(@Body req: UserSetAddReq): Observable<UserSetAddMsg>

    @POST("/")
    fun Clear(@Body req: UserSetClearReq): Observable<UserSetClearMsg>

    @POST("/")
    fun Count(@Body req: UserSetCountReq): Observable<UserSetCountMsg>

    @POST("/")
    fun Delete(@Body req: UserSetDeleteReq): Observable<UserSetDeleteMsg>

    @POST("/")
    fun GetItem(@Body req: UserSetGetItemReq): Observable<UserSetGetItemMsg>

    @POST("/")
    fun GetList(@Body req: UserSetGetListReq): Observable<UserSetGetListMsg>

    @POST("/")
    fun Query(@Body req: UserSetQueryReq): Observable<UserSetQueryMsg>

    @POST("/")
    fun Update(@Body req: UserSetUpdateReq): Observable<UserSetUpdateMsg>

}