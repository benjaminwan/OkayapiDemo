package com.benjaminwan.okayapidemo.data.request.email

import com.benjaminwan.okayapidemo.common.APP_EMAIL_VERIFYCAPTCHA
import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.sign

data class EmailVerifyCaptchaReq(
        val address: String,
        val captcha: String,
        val s: String = APP_EMAIL_VERIFYCAPTCHA,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("address" to address, "captcha" to captcha, "s" to s).sign()
}
