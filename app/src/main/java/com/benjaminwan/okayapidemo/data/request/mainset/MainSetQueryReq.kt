package com.benjaminwan.okayapidemo.data.request.mainset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_SET_QUERY
import com.benjaminwan.okayapidemo.common.sign

data class MainSetQueryReq(
        val key: String,
        val keyword: String = "",
        val s: String = APP_MAIN_SET_QUERY,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("key" to key, "keyword" to keyword, "s" to s).sign()
}
