package com.benjaminwan.okayapidemo.data.request.mainmeta

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_META_MULTIGET
import com.benjaminwan.okayapidemo.common.sign

data class MainMetaMultiGetReq(
        val keys: String,
        val s: String = APP_MAIN_META_MULTIGET,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("keys" to keys, "s" to s).sign()
}
