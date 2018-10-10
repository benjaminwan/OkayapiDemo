package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.benjaminwan.okayapidemo.common.BaseSubscriber
import com.benjaminwan.okayapidemo.common.LogMsg
import com.benjaminwan.okayapidemo.common.execute
import com.benjaminwan.okayapidemo.data.api.IPApi
import com.benjaminwan.okayapidemo.data.protocol.ip.IPGetInfoMsg
import com.benjaminwan.okayapidemo.data.request.ip.IPGetInfoReq
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_ipinfo.*
import org.jetbrains.anko.toast
import rx.Observable

class IPInfoActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipinfo)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@IPInfoActivity)
        }
        mGetInfoBtn.setOnClickListener {
            val ip_str = mIpAddrEt.text.toString()
            if (ip_str.isEmpty()) {
                toast("请输入IP地址")
            }
            RetrofitFactory.create(IPApi::class.java)
                    .GetInfo(IPGetInfoReq(ip_str))
                    .execute(object : BaseSubscriber<IPGetInfoMsg>() {
                        override fun onNext(t: IPGetInfoMsg) {
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
