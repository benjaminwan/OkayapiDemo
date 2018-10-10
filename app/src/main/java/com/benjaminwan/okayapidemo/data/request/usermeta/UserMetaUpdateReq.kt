package com.benjaminwan.okayapidemo.data.request.usermeta

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_META_UPDATE
import com.benjaminwan.okayapidemo.common.sign

data class UserMetaUpdateReq(
        val uuid: String,
        val token: String,
        val key: String,
        val data: String,
        val s: String = APP_USER_META_UPDATE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "key" to key, "data" to data, "s" to s).sign()
}
