package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.benjaminwan.okayapidemo.common.EXT_BARCODE_GEN
import com.benjaminwan.okayapidemo.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_bar_code.*
import org.jetbrains.anko.toast

class BarCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_code)
        mBarCodeGenBtn.setOnClickListener {
            val code_str = mBarCodeEt.text
            if (code_str.isEmpty()) {
                toast("请输入要生成的内容")
                return@setOnClickListener
            }
            val url = "http://api.okayapi.com/?s=$EXT_BARCODE_GEN&check_sum=$code_str"
            GlideUtils.loadImageFitCenter(this, url, mBarcodeIv)
        }
    }

}
