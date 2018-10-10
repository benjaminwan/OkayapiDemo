package com.benjaminwan.okayapidemo.data.protocol.mainset

data class MainSetGetItemMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val id: Int,
            val key: String,
            val data: Map<String, Any>,
            val keyword: String,
            val weight: String,
            val add_time: String,
            val update_time: String
    )
}