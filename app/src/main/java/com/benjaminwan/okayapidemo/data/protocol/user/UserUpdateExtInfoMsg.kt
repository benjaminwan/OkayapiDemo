package com.benjaminwan.okayapidemo.data.protocol.user


data class UserUpdateExtInfoMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val ext_info: Map<String, String>
    )
}