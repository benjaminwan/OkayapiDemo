package com.benjaminwan.okayapidemo.data.request.email

import com.benjaminwan.okayapidemo.common.APP_EMAIL_SENDCAPTCHA
import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.sign

data class EmailSendCaptchaReq(
        val address: String,
        val s: String = APP_EMAIL_SENDCAPTCHA,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("address" to address, "s" to s).sign()
}
