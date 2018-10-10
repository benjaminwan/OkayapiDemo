package com.benjaminwan.okayapidemo.data.request.email

import com.benjaminwan.okayapidemo.common.APP_EMAIL_SEND
import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.sign

data class EmailSendReq(
        val address: String,
        val title: String,
        val content: String,
        val s: String = APP_EMAIL_SEND,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("address" to address, "title" to title, "content" to content, "s" to s).sign()
}
