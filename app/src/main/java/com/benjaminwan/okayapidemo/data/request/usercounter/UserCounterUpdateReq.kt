package com.benjaminwan.okayapidemo.data.request.usercounter

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_COUNTER_UPDATE
import com.benjaminwan.okayapidemo.common.CounterTpye
import com.benjaminwan.okayapidemo.common.sign

data class UserCounterUpdateReq(
        val uuid: String,
        val token: String,
        val type: CounterTpye,
        val name: String,
        val value: String,
        val s: String = APP_USER_COUNTER_UPDATE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "type" to type, "name" to name, "value" to value, "s" to s).sign()
}
