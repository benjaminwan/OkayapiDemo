package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.email.EmailSendCaptchaMsg
import com.benjaminwan.okayapidemo.data.protocol.email.EmailSendMsg
import com.benjaminwan.okayapidemo.data.protocol.email.EmailVerifyCaptchaMsg
import com.benjaminwan.okayapidemo.data.request.email.EmailSendCaptchaReq
import com.benjaminwan.okayapidemo.data.request.email.EmailSendReq
import com.benjaminwan.okayapidemo.data.request.email.EmailVerifyCaptchaReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface EmailApi {

    @POST("/")
    fun Send(@Body req: EmailSendReq): Observable<EmailSendMsg>

    @POST("/")
    fun SendCaptcha(@Body req: EmailSendCaptchaReq): Observable<EmailSendCaptchaMsg>

    @POST("/")
    fun VerifyCaptcha(@Body req: EmailVerifyCaptchaReq): Observable<EmailVerifyCaptchaMsg>
}