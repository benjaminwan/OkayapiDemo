package com.benjaminwan.okayapidemo.data.request.mainset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_SET_COUNT
import com.benjaminwan.okayapidemo.common.sign

data class MainSetCountReq(
        val key: String,
        val s: String = APP_MAIN_SET_COUNT,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("key" to key, "s" to s).sign()
}
