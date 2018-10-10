package com.benjaminwan.okayapidemo.data.protocol.ip


data class IPGetInfoMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val data: Data,
            val err_msg: String
    ) {

        data class Data(
                val ip: String,
                val country: String,
                val region: String,
                val city: String,
                val isp: String
        )
    }
}