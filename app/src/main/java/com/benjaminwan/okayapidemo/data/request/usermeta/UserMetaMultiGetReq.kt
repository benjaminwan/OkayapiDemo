package com.benjaminwan.okayapidemo.data.request.usermeta

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_META_MULTIGET
import com.benjaminwan.okayapidemo.common.sign

data class UserMetaMultiGetReq(
        val uuid: String,
        val token: String,
        val keys: String,
        val s: String = APP_USER_META_MULTIGET,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "keys" to keys, "s" to s).sign()
}
