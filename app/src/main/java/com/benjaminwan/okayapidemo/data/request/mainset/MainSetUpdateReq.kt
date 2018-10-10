package com.benjaminwan.okayapidemo.data.request.mainset

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_SET_UPDATE
import com.benjaminwan.okayapidemo.common.sign

data class MainSetUpdateReq(
        val id: String,
        val data: String,
        val s: String = APP_MAIN_SET_UPDATE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("id" to id, "data" to data, "s" to s).sign()
}
