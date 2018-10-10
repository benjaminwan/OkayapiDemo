package com.benjaminwan.okayapidemo.data.request.user

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_USER_REGISTER
import com.benjaminwan.okayapidemo.common.sign

data class UserRegisterReq(
        val username: String,
        val password: String,
        val s: String = APP_USER_REGISTER,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("username" to username, "password" to password, "s" to s).sign()
}
