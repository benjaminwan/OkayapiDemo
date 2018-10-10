package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Base64
import android.view.View
import com.benjaminwan.okayapidemo.common.BaseSubscriber
import com.benjaminwan.okayapidemo.common.LogMsg
import com.benjaminwan.okayapidemo.common.execute
import com.benjaminwan.okayapidemo.data.api.CaptchaApi
import com.benjaminwan.okayapidemo.data.protocol.captcha.CaptchaCreateMsg
import com.benjaminwan.okayapidemo.data.protocol.captcha.CaptchaVerifyMsg
import com.benjaminwan.okayapidemo.data.request.captcha.CaptchaCreateReq
import com.benjaminwan.okayapidemo.data.request.captcha.CaptchaVerifyReq
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.benjaminwan.okayapidemo.utils.GlideUtils
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_captcha.*
import org.jetbrains.anko.toast
import rx.Observable

class CaptchaActivity : RxAppCompatActivity(), View.OnClickListener {
    private var mCaptchaId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captcha)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@CaptchaActivity)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mCaptchaCreateDataBtn.setOnClickListener(this)
        mCaptchaVerifyBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mCaptchaCreateDataBtn -> {
                RetrofitFactory.create(CaptchaApi::class.java)
                        .Create(CaptchaCreateReq())
                        .execute(object : BaseSubscriber<CaptchaCreateMsg>() {
                            override fun onNext(t: CaptchaCreateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                mCaptchaId = t.data.captcha_id
                                mCaptchaIDTv.text = mCaptchaId
                                val img_bytearray = Base64.decode(t.data.captcha_img, Base64.DEFAULT)
                                GlideUtils.loadByteArray(this@CaptchaActivity, img_bytearray, mCaptchaIv)
                            }
                        }, this)

            }
            R.id.mCaptchaVerifyBtn -> {
                if (mCaptchaId.isEmpty()) {
                    toast("请先创建验证码")
                }
                val code = mCaptchaCodeEt.text.toString()
                if (code.isEmpty()) {
                    toast("请输入验证码")
                }

                RetrofitFactory.create(CaptchaApi::class.java)
                        .Verify(CaptchaVerifyReq(mCaptchaId, code))
                        .execute(object : BaseSubscriber<CaptchaVerifyMsg>() {
                            override fun onNext(t: CaptchaVerifyMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                val result = when (t.data.err_code) {
                                    0 -> {
                                        "验证通过"
                                    }
                                    1 -> {
                                        "验证码已过期，或不存在"
                                    }
                                    2 -> {
                                        "验证码错误，需要刷新重试"
                                    }
                                    else -> {
                                        "未知错误"
                                    }
                                }
                                mRecMsgTv.LogMsg(result)
                            }
                        }, this)

            }
            else -> {
            }
        }
    }
}
