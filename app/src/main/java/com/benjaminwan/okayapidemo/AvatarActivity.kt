package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.benjaminwan.okayapidemo.common.EXT_AVATAR_SHOW
import com.benjaminwan.okayapidemo.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_avatar.*
import org.jetbrains.anko.toast

class AvatarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar)
        mAvatarShowBtn.setOnClickListener {
            val nickname = mNameEt.text.toString()
            if (nickname.isEmpty()) {
                toast("请输入用户名")
            }
            val url = "http://api.okayapi.com/?s=$EXT_AVATAR_SHOW&nickname=$nickname"
            GlideUtils.loadImageFitCenter(this, url, mAvatarIv)
        }
    }
}
