package com.benjaminwan.okayapidemo.data.protocol.user

data class UserProfileMsg(
        val ret: Int,
        val data: Data,
        val msg: String
) {

    data class Data(
            val err_code: Int,
            val err_msg: String,
            val info: Info
    ) {

        data class Info(
                val uuid: String,
                val username: String,
                val role: String,
                val rolename: String,
                val register_time: String,
                val register_ip: String,
                val ext_info: Map<String, String>
        )
    }
}