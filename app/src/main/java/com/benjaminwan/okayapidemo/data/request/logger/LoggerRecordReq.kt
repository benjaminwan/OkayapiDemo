package com.benjaminwan.okayapidemo.data.request.logger

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_LOGGER_RECORD
import com.benjaminwan.okayapidemo.common.sign

data class LoggerRecordReq(
        val type: String,
        val message: String,
        val s: String = APP_LOGGER_RECORD,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("type" to type, "message" to message, "s" to s).sign()
}
