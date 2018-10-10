package com.benjaminwan.okayapidemo.data.protocol.user


data class UserRegisterMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val uuid: String
    )
}