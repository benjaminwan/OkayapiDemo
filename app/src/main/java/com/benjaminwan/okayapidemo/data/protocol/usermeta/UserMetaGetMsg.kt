package com.benjaminwan.okayapidemo.data.protocol.usermeta


data class UserMetaGetMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val key: String,
            val data: Map<String, Any>,
            val add_time: String,
            val update_time: String
    )
}