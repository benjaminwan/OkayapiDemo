package com.benjaminwan.okayapidemo.data.protocol.user


data class UserLoginMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val uuid: String,
            val token: String
    )
}