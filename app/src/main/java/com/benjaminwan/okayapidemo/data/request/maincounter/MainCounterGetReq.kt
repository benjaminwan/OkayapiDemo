package com.benjaminwan.okayapidemo.data.request.maincounter

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_COUNTER_GET
import com.benjaminwan.okayapidemo.common.CounterTpye
import com.benjaminwan.okayapidemo.common.sign

data class MainCounterGetReq(
        val type: CounterTpye,
        val name: String,
        val s: String = APP_MAIN_COUNTER_GET,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("type" to type, "name" to name, "s" to s).sign()
}
