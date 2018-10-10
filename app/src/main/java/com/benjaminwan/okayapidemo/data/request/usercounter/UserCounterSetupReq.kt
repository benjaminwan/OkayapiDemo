package com.benjaminwan.okayapidemo.data.request.usercounter

import com.benjaminwan.okayapidemo.common.*

data class UserCounterSetupReq(
        val uuid: String,
        val token: String,
        val type: CounterTpye,
        val name: String,
        val value: String,
        val visiable: String = VISIABLE_PRIVATE,
        val s: String = APP_USER_COUNTER_SETUP,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("uuid" to uuid, "token" to token, "type" to type, "name" to name, "value" to value, "visiable" to visiable, "s" to s).sign()
}
