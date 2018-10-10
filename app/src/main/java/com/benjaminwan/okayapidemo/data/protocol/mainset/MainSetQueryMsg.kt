package com.benjaminwan.okayapidemo.data.protocol.mainset


data class MainSetQueryMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val items: List<Item>,
            val total: Int,
            val page: Int,
            val perpage: Int
    ) {

        data class Item(
                val id: Int,
                val key: String,
                val data: Map<String, Any>,
                val keyword: String,
                val weight: Int,
                val add_time: String,
                val update_time: String
        )
    }
}