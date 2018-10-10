package com.benjaminwan.okayapidemo.data.request.mainset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_SET_ADD
import com.benjaminwan.okayapidemo.common.sign

data class MainSetAddReq(
        val key: String,
        val data: String,
        val s: String = APP_MAIN_SET_ADD,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("key" to key, "data" to data, "s" to s).sign()
}
