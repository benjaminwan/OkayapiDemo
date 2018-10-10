package com.benjaminwan.okayapidemo.data.request.user

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_UPDATEEXTINFO
import com.benjaminwan.okayapidemo.common.sign

data class UserUpdateExtInfoReq(
        val uuid: String,
        val token: String,
        val ext_info: String,
        val s: String = APP_USER_UPDATEEXTINFO,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "ext_info" to ext_info, "s" to s).sign()
}
