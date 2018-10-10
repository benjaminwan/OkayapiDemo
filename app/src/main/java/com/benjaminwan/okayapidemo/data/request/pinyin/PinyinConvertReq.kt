package com.benjaminwan.okayapidemo.data.request.pinyin

import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.EXT_PINYIN_CONVERT
import com.benjaminwan.okayapidemo.common.sign

data class PinyinConvertReq(
        val text: String,
        val s: String = EXT_PINYIN_CONVERT,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("text" to text, "s" to s).sign()
}
