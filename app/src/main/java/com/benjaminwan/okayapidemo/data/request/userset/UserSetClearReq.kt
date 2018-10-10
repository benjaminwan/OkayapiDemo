package com.benjaminwan.okayapidemo.data.request.userset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_SET_CLEAR
import com.benjaminwan.okayapidemo.common.sign

data class UserSetClearReq(
        val uuid: String,
        val token: String,
        val key: String,
        val s: String = APP_USER_SET_CLEAR,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "key" to key, "s" to s).sign()
}
