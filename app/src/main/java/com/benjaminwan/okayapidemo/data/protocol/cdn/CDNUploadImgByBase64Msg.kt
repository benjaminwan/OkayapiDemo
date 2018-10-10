package com.benjaminwan.okayapidemo.data.protocol.cdn


data class CDNUploadImgByBase64Msg(
    val ret: Int,
    val data: Data,
    val msg: String
) {

    data class Data(
        val err_code: Int,
        val err_msg: String,
        val url: String
    )
}