package com.benjaminwan.okayapidemo.data.request.usermeta

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_META_DELETE
import com.benjaminwan.okayapidemo.common.sign

data class UserMetaDeleteReq(
        val uuid: String,
        val token: String,
        val key: String,
        val s: String = APP_USER_META_DELETE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "key" to key, "s" to s).sign()
}
