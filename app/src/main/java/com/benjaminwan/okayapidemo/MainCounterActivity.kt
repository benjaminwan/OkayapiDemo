package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.BaseSubscriber
import com.benjaminwan.okayapidemo.common.FOREVER
import com.benjaminwan.okayapidemo.common.LogMsg
import com.benjaminwan.okayapidemo.common.execute
import com.benjaminwan.okayapidemo.data.api.MainCounterApi
import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterGetMsg
import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterSetupMsg
import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterSmartRefreshMsg
import com.benjaminwan.okayapidemo.data.protocol.maincounter.MainCounterUpdateMsg
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterGetReq
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterSetupReq
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterSmartRefreshReq
import com.benjaminwan.okayapidemo.data.request.maincounter.MainCounterUpdateReq
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_counter.*
import rx.Observable

class MainCounterActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "MainCounterActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_counter)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@MainCounterActivity)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mMainCounterGetBtn.setOnClickListener(this)
        mMainCounterSetupBtn.setOnClickListener(this)
        mMainCounterSmartRefreshBtn.setOnClickListener(this)
        mMainCounterUpdateBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mMainCounterGetBtn -> {
                RetrofitFactory.create(MainCounterApi::class.java)
                        .Get(MainCounterGetReq(FOREVER, "counter_test"))
                        .execute(object : BaseSubscriber<MainCounterGetMsg>() {
                            override fun onNext(t: MainCounterGetMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainCounterSetupBtn -> {
                RetrofitFactory.create(MainCounterApi::class.java)
                        .Setup(MainCounterSetupReq(FOREVER, "counter_test", "0"))
                        .execute(object : BaseSubscriber<MainCounterSetupMsg>() {
                            override fun onNext(t: MainCounterSetupMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainCounterSmartRefreshBtn -> {
                RetrofitFactory.create(MainCounterApi::class.java)
                        .SmartRefresh(MainCounterSmartRefreshReq(FOREVER, "counter_test", "1"))
                        .execute(object : BaseSubscriber<MainCounterSmartRefreshMsg>() {
                            override fun onNext(t: MainCounterSmartRefreshMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainCounterUpdateBtn -> {
                RetrofitFactory.create(MainCounterApi::class.java)
                        .Update(MainCounterUpdateReq(FOREVER, "counter_test", "1"))
                        .execute(object : BaseSubscriber<MainCounterUpdateMsg>() {
                            override fun onNext(t: MainCounterUpdateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            else -> {
            }
        }
    }
}
