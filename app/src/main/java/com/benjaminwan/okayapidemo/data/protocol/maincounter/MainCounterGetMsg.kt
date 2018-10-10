package com.benjaminwan.okayapidemo.data.protocol.maincounter

data class MainCounterGetMsg(
    val ret: Int,
    val data: Data,
    val msg: String
) {

    data class Data(
        val err_code: Int,
        val err_msg: String,
        val value: Int
    )
}