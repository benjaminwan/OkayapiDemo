package com.benjaminwan.okayapidemo.data.request.mainmeta

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_META_UPDATE
import com.benjaminwan.okayapidemo.common.sign

data class MainMetaUpdateReq(
        val key: String,
        val data: String,
        val s: String = APP_MAIN_META_UPDATE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("key" to key, "data" to data, "s" to s).sign()
}
