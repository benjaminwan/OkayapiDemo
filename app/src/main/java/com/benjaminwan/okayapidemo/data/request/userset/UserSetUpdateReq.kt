package com.benjaminwan.okayapidemo.data.request.userset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_SET_UPDATE
import com.benjaminwan.okayapidemo.common.sign

data class UserSetUpdateReq(
        val uuid: String,
        val token: String,
        val id: String,
        val data: String,
        val s: String = APP_USER_SET_UPDATE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "id" to id, "data" to data, "s" to s).sign()
}
