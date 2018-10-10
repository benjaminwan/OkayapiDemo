package com.benjaminwan.okayapidemo.data.request.userset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_SET_ADD
import com.benjaminwan.okayapidemo.common.VISIABLE_PRIVATE
import com.benjaminwan.okayapidemo.common.sign

data class UserSetAddReq(
        val uuid: String,
        val token: String,
        val key: String,
        val data: String,
        val visiable: String = VISIABLE_PRIVATE,
        val s: String = APP_USER_SET_ADD,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "key" to key, "data" to data, "visiable" to visiable, "s" to s).sign()
}
