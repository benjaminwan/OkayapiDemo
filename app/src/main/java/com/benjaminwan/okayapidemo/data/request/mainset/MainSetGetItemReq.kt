package com.benjaminwan.okayapidemo.data.request.mainset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_SET_GETITEM
import com.benjaminwan.okayapidemo.common.sign

data class MainSetGetItemReq(
        val id: String,
        val s: String = APP_MAIN_SET_GETITEM,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("id" to id, "s" to s).sign()
}
