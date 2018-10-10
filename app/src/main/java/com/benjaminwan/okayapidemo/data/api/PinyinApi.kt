package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinAbbrMsg
import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinConvertMsg
import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinNameMsg
import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinSentenceMsg
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinAbbrReq
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinConvertReq
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinNameReq
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinSentenceReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface PinyinApi {

    @POST("/")
    fun Abbr(@Body req: PinyinAbbrReq): Observable<PinyinAbbrMsg>

    @POST("/")
    fun Convert(@Body req: PinyinConvertReq): Observable<PinyinConvertMsg>

    @POST("/")
    fun Name(@Body req: PinyinNameReq): Observable<PinyinNameMsg>

    @POST("/")
    fun Sentence(@Body req: PinyinSentenceReq): Observable<PinyinSentenceMsg>
}