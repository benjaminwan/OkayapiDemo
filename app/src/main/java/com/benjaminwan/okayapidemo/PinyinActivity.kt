package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.BaseSubscriber
import com.benjaminwan.okayapidemo.common.LogMsg
import com.benjaminwan.okayapidemo.common.execute
import com.benjaminwan.okayapidemo.data.api.PinyinApi
import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinAbbrMsg
import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinConvertMsg
import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinNameMsg
import com.benjaminwan.okayapidemo.data.protocol.pinyin.PinyinSentenceMsg
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinAbbrReq
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinConvertReq
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinNameReq
import com.benjaminwan.okayapidemo.data.request.pinyin.PinyinSentenceReq
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_pinyin.*
import org.jetbrains.anko.toast
import rx.Observable

class PinyinActivity : RxAppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pinyin)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@PinyinActivity)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mAbbrBtn.setOnClickListener(this)
        mConvertBtn.setOnClickListener(this)
        mNameBtn.setOnClickListener(this)
        mSentenceBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mAbbrBtn -> {
                val text = mAbbrEt.text.toString()
                if (text.isEmpty()) {
                    toast("请输入用于获取拼音首字符串的内容")
                }
                RetrofitFactory.create(PinyinApi::class.java)
                        .Abbr(PinyinAbbrReq(text))
                        .execute(object : BaseSubscriber<PinyinAbbrMsg>() {
                            override fun onNext(t: PinyinAbbrMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                mAbbrCodeTv.text = t.data.pinyin
                            }
                        }, this)
            }
            R.id.mConvertBtn -> {
                val text = mConvertEt.text.toString()
                if (text.isEmpty()) {
                    toast("请输入用于转换的内容")
                }
                RetrofitFactory.create(PinyinApi::class.java)
                        .Convert(PinyinConvertReq(text))
                        .execute(object : BaseSubscriber<PinyinConvertMsg>() {
                            override fun onNext(t: PinyinConvertMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                mConvertCodeTv.text = t.data.pinyin
                            }
                        }, this)
            }
            R.id.mNameBtn -> {
                val text = mNameEt.text.toString()
                if (text.isEmpty()) {
                    toast("请输入姓名")
                }
                RetrofitFactory.create(PinyinApi::class.java)
                        .Name(PinyinNameReq(text))
                        .execute(object : BaseSubscriber<PinyinNameMsg>() {
                            override fun onNext(t: PinyinNameMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                mNameCodeTv.text = t.data.pinyin
                            }
                        }, this)

            }
            R.id.mSentenceBtn -> {
                val text = mSentenceEt.text.toString()
                if (text.isEmpty()) {
                    toast("请输入整段内容")
                }
                RetrofitFactory.create(PinyinApi::class.java)
                        .Sentence(PinyinSentenceReq(text))
                        .execute(object : BaseSubscriber<PinyinSentenceMsg>() {
                            override fun onNext(t: PinyinSentenceMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                mSentenceCodeTv.text = t.data.pinyin
                            }
                        }, this)

            }

            else -> {
            }
        }
    }

}
