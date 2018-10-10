package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.cdn.CDNUploadImgByBase64Msg
import com.benjaminwan.okayapidemo.data.protocol.cdn.CDNUploadImgMsg
import com.benjaminwan.okayapidemo.data.request.cdn.CDNUploadImgByBase64Req
import okhttp3.MultipartBody
import retrofit2.http.*
import rx.Observable

interface CDNApi {
    @Multipart
    @POST("/")
    fun UploadImg(@QueryMap params: Map<String, String>,
                  @Part fileBody: MultipartBody.Part): Observable<CDNUploadImgMsg>

    @POST("/")
    fun UploadImgByBase64(@Body req: CDNUploadImgByBase64Req): Observable<CDNUploadImgByBase64Msg>

}