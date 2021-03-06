package com.benjaminwan.okayapidemo.data.request.user

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_PROFILE
import com.benjaminwan.okayapidemo.common.sign

data class UserProfileReq(
        val uuid: String,
        val token: String,
        val s: String = APP_USER_PROFILE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "s" to s).sign()
}
