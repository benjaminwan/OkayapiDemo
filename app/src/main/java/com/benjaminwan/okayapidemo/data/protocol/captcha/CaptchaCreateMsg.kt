package com.benjaminwan.okayapidemo.data.protocol.captcha


data class CaptchaCreateMsg(
    val ret: Int,
    val data: Data,
    val msg: String
) {

    data class Data(
        val err_code: Int,
        val err_msg: String,
        val captcha_id: String,
        val captcha_img: String
    )
}