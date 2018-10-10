package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.logger.LoggerRecordMsg
import com.benjaminwan.okayapidemo.data.request.logger.LoggerRecordReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface LoggerApi {

    @POST("/")
    fun Record(@Body req: LoggerRecordReq): Observable<LoggerRecordMsg>
}