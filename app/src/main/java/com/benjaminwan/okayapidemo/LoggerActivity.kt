package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.benjaminwan.okayapidemo.common.*
import com.benjaminwan.okayapidemo.data.api.LoggerApi
import com.benjaminwan.okayapidemo.data.protocol.logger.LoggerRecordMsg
import com.benjaminwan.okayapidemo.data.request.logger.LoggerRecordReq
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_logger.*
import org.jetbrains.anko.toast
import rx.Observable

class LoggerActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logger)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@LoggerActivity)
        }
        mLogRecordBtn.setOnClickListener {
            val msg = mMessageEt.text.toString()
            if (msg.isEmpty()) {
                toast("请输入日志内容")
            }
            val type = when (mLogTypeRg.checkedRadioButtonId) {
                R.id.mTypeErrorRb -> {
                    ERROR
                }
                R.id.mTypeWarningRb -> {
                    WARNING
                }
                R.id.mTypeNoticeRb -> {
                    NOTICE
                }
                R.id.mTypeInfoRb -> {
                    INFO
                }
                R.id.mTypeDebugRb -> {
                    DEBUG
                }
                else -> {
                    ERROR
                }
            }

            RetrofitFactory.create(LoggerApi::class.java)
                    .Record(LoggerRecordReq(type, msg))
                    .execute(object : BaseSubscriber<LoggerRecordMsg>() {
                        override fun onNext(t: LoggerRecordMsg) {
                            mRecMsgTv.LogMsg(t.toString())
                        }
                    }, this)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
    }
}
