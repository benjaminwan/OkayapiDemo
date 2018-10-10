package com.benjaminwan.okayapidemo.utils

import android.util.Log
import com.benjaminwan.okayapidemo.common.APP_KEY
import com.benjaminwan.okayapidemo.common.APP_SECRECT
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

object SignUtils {
    /**
     * 返回加密后的字符串
     * @param map map
     * @return string
     */
    fun getSignStr(map: SortedMap<String, String>): String {
        map["app_key"] = APP_KEY
        val stringBuffer: StringBuilder = StringBuilder()
        for (str in map.values) {
            stringBuffer.append(str)
        }
        stringBuffer.append(APP_SECRECT)
        val sign = signMd5(stringBuffer.toString(), true)
        Log.d("====getSignOfMap", sign)
        return sign
    }

    /**
     * MD5签名
     * @param str 需要签名的字符串
     * @param isUpper 是否大写
     * @return 签名后字符串（大写）
     */
    fun signMd5(str: String, isUpper: Boolean): String {
        val md5: MessageDigest

        try {
            md5 = MessageDigest.getInstance("MD5")
            val bytes = md5.digest(str.toByteArray())
            val result = StringBuilder()
            for (b in bytes) {
                var temp = Integer.toHexString(b.toInt() and 0xff)
                if (temp.length < 2) {
                    temp = "0$temp"
                }
                result.append(temp)
            }
            return if (isUpper) result.toString().toUpperCase() else result.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}