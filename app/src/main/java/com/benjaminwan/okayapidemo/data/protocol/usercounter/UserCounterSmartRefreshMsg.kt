package com.benjaminwan.okayapidemo.data.protocol.usercounter

data class UserCounterSmartRefreshMsg(
    val ret: Int,
    val data: Data,
    val msg: String
) {

    data class Data(
        val err_code: Int,
        val err_msg: String,
        val before_value: Int,
        val after_value: Int
    )
}