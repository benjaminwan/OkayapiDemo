package com.benjaminwan.okayapidemo.data.request.maincounter

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_MAIN_COUNTER_SMARTREFRESH
import com.benjaminwan.okayapidemo.common.CounterTpye
import com.benjaminwan.okayapidemo.common.sign

data class MainCounterSmartRefreshReq(
        val type: CounterTpye,
        val name: String,
        val value: String,
        val s: String = APP_MAIN_COUNTER_SMARTREFRESH,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("type" to type, "name" to name, "value" to value, "s" to s).sign()
}
