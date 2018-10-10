package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.ip.IPGetInfoMsg
import com.benjaminwan.okayapidemo.data.request.ip.IPGetInfoReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface IPApi {

    @POST("/")
    fun GetInfo(@Body req: IPGetInfoReq): Observable<IPGetInfoMsg>

}