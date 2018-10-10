package com.benjaminwan.okayapidemo.data.request.mainset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_SET_GETLIST
import com.benjaminwan.okayapidemo.common.sign

data class MainSetGetListReq(
        val key: String,
        val s: String = APP_MAIN_SET_GETLIST,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("key" to key, "s" to s).sign()
}
