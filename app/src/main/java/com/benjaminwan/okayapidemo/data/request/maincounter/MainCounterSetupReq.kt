package com.benjaminwan.okayapidemo.data.request.maincounter

import com.benjaminwan.okayapidemo.common.*

data class MainCounterSetupReq(
        val type: CounterTpye,
        val name: String,
        val value: String,
        val visiable: String = VISIABLE_PRIVATE,
        val s: String = APP_MAIN_COUNTER_SETUP,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("type" to type, "name" to name, "value" to value, "visiable" to visiable, "s" to s).sign()
}
