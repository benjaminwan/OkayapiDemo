package com.benjaminwan.okayapidemo.data.request.captcha

import com.benjaminwan.okayapidemo.common.APP_CAPTCHA_CREATE
import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.CAPTCHA_DATA
import com.benjaminwan.okayapidemo.common.sign

data class CaptchaCreateReq(
        val return_format: String = CAPTCHA_DATA,
        val s: String = APP_CAPTCHA_CREATE,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("return_format" to return_format, "s" to s).sign()
}
