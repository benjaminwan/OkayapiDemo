package com.benjaminwan.okayapidemo.data.request.userset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_SET_QUERY
import com.benjaminwan.okayapidemo.common.sign

data class UserSetQueryReq(
        val uuid: String,
        val token: String,
        val key: String,
        val keyword: String = "",
        val s: String = APP_USER_SET_QUERY,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "key" to key, "keyword" to keyword, "s" to s).sign()
}
