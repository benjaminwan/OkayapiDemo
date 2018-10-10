package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterGetMsg
import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterSetupMsg
import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterSmartRefreshMsg
import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterUpdateMsg
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterGetReq
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterSetupReq
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterSmartRefreshReq
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterUpdateReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserCounterApi {

    @POST("/")
    fun Get(@Body req: UserCounterGetReq): Observable<UserCounterGetMsg>

    @POST("/")
    fun Setup(@Body req: UserCounterSetupReq): Observable<UserCounterSetupMsg>

    @POST("/")
    fun SmartRefresh(@Body req: UserCounterSmartRefreshReq): Observable<UserCounterSmartRefreshMsg>

    @POST("/")
    fun Update(@Body req: UserCounterUpdateReq): Observable<UserCounterUpdateMsg>


}