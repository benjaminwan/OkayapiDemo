package com.benjaminwan.okayapidemo.data.request.ip

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.EXT_IP_GETINFO
import com.benjaminwan.okayapidemo.common.sign

data class IPGetInfoReq(
        val ip: String,
        val s: String = EXT_IP_GETINFO,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("ip" to ip, "s" to s).sign()
}
