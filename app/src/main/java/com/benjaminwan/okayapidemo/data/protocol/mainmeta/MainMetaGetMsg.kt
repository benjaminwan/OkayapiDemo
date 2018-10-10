package com.benjaminwan.okayapidemo.data.protocol.mainmeta


data class MainMetaGetMsg(
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