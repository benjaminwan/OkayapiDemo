package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.captcha.CaptchaCreateMsg
import com.benjaminwan.okayapidemo.data.protocol.captcha.CaptchaVerifyMsg
import com.benjaminwan.okayapidemo.data.request.captcha.CaptchaCreateReq
import com.benjaminwan.okayapidemo.data.request.captcha.CaptchaVerifyReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface CaptchaApi {

    @POST("/")
    fun Create(@Body req: CaptchaCreateReq): Observable<CaptchaCreateMsg>

    @POST("/")
    fun Verify(@Body req: CaptchaVerifyReq): Observable<CaptchaVerifyMsg>
}