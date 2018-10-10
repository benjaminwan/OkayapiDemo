package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.view.View
import com.benjaminwan.okayapidemo.common.KEY_SP_TOKEN
import com.benjaminwan.okayapidemo.common.KEY_SP_UUID
import com.benjaminwan.okayapidemo.utils.AppPrefsUtils
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : RxAppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        val uuid = AppPrefsUtils.getString(KEY_SP_UUID)
        val token = AppPrefsUtils.getString(KEY_SP_TOKEN)
        if (uuid.isNotEmpty()) {
            mLastUuidEt.setText(uuid)
        }
        if (token.isNotEmpty()) {
            mLastTokenEt.setText(token)
        }
    }

    private fun initViews() {
        mUserBtn.setOnClickListener(this)
        mMainMetaBtn.setOnClickListener(this)
        mMainSetBtn.setOnClickListener(this)
        mMainCounterBtn.setOnClickListener(this)
        mUserMetaBtn.setOnClickListener(this)
        mUserSetBtn.setOnClickListener(this)
        mUserCounterBtn.setOnClickListener(this)
        mLoggerBtn.setOnClickListener(this)
        mCDNUploadBtn.setOnClickListener(this)
        mEmailBtn.setOnClickListener(this)
        mCaptchaBtn.setOnClickListener(this)
        mAvatarBtn.setOnClickListener(this)
        mBarCodeBtn.setOnClickListener(this)
        mIPInfoBtn.setOnClickListener(this)
        mPinyinBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) = when (view.id) {
        R.id.mUserBtn -> {//用户模块
            startActivity<UserActivity>()
        }
        R.id.mMainMetaBtn -> {//用户元数据
            startActivity<MainMetaActivity>()
        }
        R.id.mMainSetBtn -> {//用户集合
            startActivity<MainSetActivity>()
        }
        R.id.mMainCounterBtn -> {//用户计数器
            startActivity<MainCounterActivity>()
        }
        R.id.mUserMetaBtn -> {//应用元数据
            startActivity<UserMetaActivity>()
        }
        R.id.mUserSetBtn -> {//应用集合
            startActivity<UserSetActivity>()
        }
        R.id.mUserCounterBtn -> {//应用计数器
            startActivity<UserCounterActivity>()
        }
        R.id.mLoggerBtn -> {//日志模块
            startActivity<LoggerActivity>()
        }
        R.id.mCDNUploadBtn -> {//CDN上传
            startActivity<CDNActivity>()
        }
        R.id.mEmailBtn -> {//邮件服务
            startActivity<EmailActivity>()
        }
        R.id.mCaptchaBtn -> {//图形验证码
            startActivity<CaptchaActivity>()
        }
        R.id.mAvatarBtn -> {//用户头像
            startActivity<AvatarActivity>()
        }
        R.id.mBarCodeBtn -> {//条形码服务
            startActivity<BarCodeActivity>()
        }
        R.id.mIPInfoBtn -> {//IP服务
            startActivity<IPInfoActivity>()
        }
        R.id.mPinyinBtn -> {//拼音服务
            startActivity<PinyinActivity>()
        }

        else -> {
        }
    }

}
