package com.benjaminwan.okayapidemo.utils

import com.benjaminwan.okayapidemo.common.KEY_SP_TOKEN
import com.benjaminwan.okayapidemo.common.KEY_SP_USER_NAME
import com.benjaminwan.okayapidemo.common.KEY_SP_USER_PASS
import com.benjaminwan.okayapidemo.common.KEY_SP_UUID
import com.benjaminwan.okayapidemo.data.UserInfo

object UserPrefsUtils {
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(KEY_SP_USER_NAME, userInfo?.userName ?: "")
        AppPrefsUtils.putString(KEY_SP_USER_PASS, userInfo?.userPassword ?: "")
        AppPrefsUtils.putString(KEY_SP_UUID, userInfo?.uuid ?: "")
        AppPrefsUtils.putString(KEY_SP_TOKEN, userInfo?.token ?: "")
    }

    fun getUserInfo(): UserInfo {
        val username = AppPrefsUtils.getString(KEY_SP_USER_NAME)
        val userpass = AppPrefsUtils.getString(KEY_SP_USER_PASS)
        val uuid = AppPrefsUtils.getString(KEY_SP_UUID)
        val token = AppPrefsUtils.getString(KEY_SP_TOKEN)
        val userInfo = UserInfo(username, userpass, uuid, token)
        return userInfo
    }
}