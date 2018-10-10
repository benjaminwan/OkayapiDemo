package com.benjaminwan.okayapidemo.data.request.captcha

import com.benjaminwan.okayapidemo.common.APP_CAPTCHA_VERIFY
import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.sign

data class CaptchaVerifyReq(
        val captcha_id: String,
        val captcha_code: String,
        val s: String = APP_CAPTCHA_VERIFY,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("captcha_id" to captcha_id, "captcha_code" to captcha_code, "s" to s).sign()
}
