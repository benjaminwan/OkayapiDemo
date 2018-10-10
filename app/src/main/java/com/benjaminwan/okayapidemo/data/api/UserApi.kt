package com.benjaminwan.okayapidemo.data.api

import com.benjaminwan.okayapidemo.data.protocol.user.*
import com.benjaminwan.okayapidemo.data.request.user.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {
    @POST("/")
    fun Check(@Body req: UserCheckReq): Observable<UserCheckMsg>

    @POST("/")
    fun Login(@Body req: UserLoginReq): Observable<UserLoginMsg>

    @POST("/")
    fun Profile(@Body req: UserProfileReq): Observable<UserProfileMsg>

    @POST("/")
    fun Register(@Body req: UserRegisterReq): Observable<UserRegisterMsg>

    @POST("/")
    fun UpdateExtInfo(@Body req: UserUpdateExtInfoReq): Observable<UserUpdateExtInfoMsg>
}