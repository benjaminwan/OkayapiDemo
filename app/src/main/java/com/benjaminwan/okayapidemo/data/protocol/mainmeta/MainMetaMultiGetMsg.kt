package com.benjaminwan.okayapidemo.data.protocol.mainmeta


data class MainMetaMultiGetMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val items: List<Item>
    ) {

        data class Item(
                val key: String,
                val data: Map<String, String>,
                val add_time: String,
                val update_time: String
        )
    }
}