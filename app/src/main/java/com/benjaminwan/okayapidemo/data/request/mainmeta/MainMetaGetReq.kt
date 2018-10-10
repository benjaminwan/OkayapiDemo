package com.benjaminwan.okayapidemo.data.request.mainmeta

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_META_GET
import com.benjaminwan.okayapidemo.common.sign

data class MainMetaGetReq(
        val key: String,
        val s: String = APP_MAIN_META_GET,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("key" to key, "s" to s).sign()
}
