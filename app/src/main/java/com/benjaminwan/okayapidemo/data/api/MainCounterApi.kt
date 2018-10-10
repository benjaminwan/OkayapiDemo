package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterGetMsg
import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterSetupMsg
import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterSmartRefreshMsg
import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterUpdateMsg
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterGetReq
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterSetupReq
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterSmartRefreshReq
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterUpdateReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface MainCounterApi {

    @POST("/")
    fun Get(@Body req: MainCounterGetReq): Observable<MainCounterGetMsg>

    @POST("/")
    fun Setup(@Body req: MainCounterSetupReq): Observable<MainCounterSetupMsg>

    @POST("/")
    fun SmartRefresh(@Body req: MainCounterSmartRefreshReq): Observable<MainCounterSmartRefreshMsg>

    @POST("/")
    fun Update(@Body req: MainCounterUpdateReq): Observable<MainCounterUpdateMsg>


}