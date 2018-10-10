package com.benjaminwan.okayapidemo.data.protocol.usermeta

data class UserMetaCreateMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String
    )
}