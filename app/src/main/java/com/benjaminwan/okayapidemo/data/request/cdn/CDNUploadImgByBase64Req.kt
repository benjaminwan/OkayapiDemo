package com.benjaminwan.okayapidemo.data.request.cdn

import com.benjaminwan.okayapidemo.common.APP_CDN_UPLOADIMAGE_BYBASE64
import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.sign

data class CDNUploadImgByBase64Req(
        val file: String,
        val file_name: String,
        val s: String = APP_CDN_UPLOADIMAGE_BYBASE64,
        val app_key: String = APP_KEY
) {
    var sign: String = sortedMapOf<String, String>("file" to file, "file_name" to file_name, "s" to s).sign()
}
