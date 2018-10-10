package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import com.benjaminwan.okayapidemo.common.BaseSubscriber
import com.benjaminwan.okayapidemo.common.LogMsg
import com.benjaminwan.okayapidemo.common.execute
import com.benjaminwan.okayapidemo.data.api.EmailApi
import com.benjaminwan.okayapidemo.data.protocol.email.EmailSendCaptchaMsg
import com.benjaminwan.okayapidemo.data.protocol.email.EmailSendMsg
import com.benjaminwan.okayapidemo.data.protocol.email.EmailVerifyCaptchaMsg
import com.benjaminwan.okayapidemo.data.request.email.EmailSendCaptchaReq
import com.benjaminwan.okayapidemo.data.request.email.EmailSendReq
import com.benjaminwan.okayapidemo.data.request.email.EmailVerifyCaptchaReq
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_email.*
import org.jetbrains.anko.toast
import rx.Observable
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class EmailActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "EmailActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@EmailActivity)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mEmailSendBtn.setOnClickListener(this)
        mEmailSendCaptchaBtn.setOnClickListener(this)
        mEmailVerifyCaptchaBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mEmailSendBtn -> {
                val emailAddr = mEmailAddrEt.text.toString()
                if (emailAddr.isEmpty()) {
                    toast("请输入邮件地址")
                }
                val htmlfileStream = resources.openRawResource(R.raw.email)
                val htmlStr = readTextFromInputStream(htmlfileStream)
                Log.i(TAG, htmlStr)
                RetrofitFactory.create(EmailApi::class.java)
                        .Send(EmailSendReq(emailAddr, "测试邮件", htmlStr))
                        .execute(object : BaseSubscriber<EmailSendMsg>() {
                            override fun onNext(t: EmailSendMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mEmailSendCaptchaBtn -> {
                val emailAddr = mEmailAddrEt.text.toString()
                if (emailAddr.isEmpty()) {
                    toast("请输入邮件地址")
                }
                RetrofitFactory.create(EmailApi::class.java)
                        .SendCaptcha(EmailSendCaptchaReq(emailAddr))
                        .execute(object : BaseSubscriber<EmailSendCaptchaMsg>() {
                            override fun onNext(t: EmailSendCaptchaMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mEmailVerifyCaptchaBtn -> {
                val emailAddr = mEmailAddrEt.text.toString()
                if (emailAddr.isEmpty()) {
                    toast("请输入邮件地址")
                }
                val captchaStr = mEmailCaptchaEt.text.toString()
                if (captchaStr.isEmpty()) {
                    toast("请输入验证码")
                }
                RetrofitFactory.create(EmailApi::class.java)
                        .VerifyCaptcha(EmailVerifyCaptchaReq(emailAddr, captchaStr))
                        .execute(object : BaseSubscriber<EmailVerifyCaptchaMsg>() {
                            override fun onNext(t: EmailVerifyCaptchaMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)


            }
            else -> {
            }
        }
    }

    private fun readTextFromInputStream(inputStream: InputStream): String {
        inputStream.use { input ->
            val reader = InputStreamReader(input)
            val bufferedReader = BufferedReader(reader)
            val buffer = StringBuffer()
            var str: String?
            str = bufferedReader.readLine()
            while (str != null) {
                buffer.append(str)
                str = bufferedReader.readLine()
            }
            return buffer.toString()
        }
    }
}
