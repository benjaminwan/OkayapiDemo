package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.*
import com.benjaminwan.okayapidemo.data.api.UserApi
import com.benjaminwan.okayapidemo.data.api.UserCounterApi
import com.benjaminwan.okayapidemo.data.protocol.user.UserCheckMsg
import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterGetMsg
import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterSetupMsg
import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterSmartRefreshMsg
import com.benjaminwan.okayapidemo.data.protocol.usercounter.UserCounterUpdateMsg
import com.benjaminwan.okayapidemo.data.request.user.UserCheckReq
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterGetReq
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterSetupReq
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterSmartRefreshReq
import com.benjaminwan.okayapidemo.data.request.usercounter.UserCounterUpdateReq
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.benjaminwan.okayapidemo.utils.AppPrefsUtils
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_user_counter.*
import org.jetbrains.anko.toast
import rx.Observable

class UserCounterActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "UserCounterActivity"
    private var isLogin = false
    private var mUuid = ""
    private var mToken = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_counter)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@UserCounterActivity)
        }
        mUuid = AppPrefsUtils.getString(KEY_SP_UUID)
        mToken = AppPrefsUtils.getString(KEY_SP_TOKEN)
        if (mUuid.isNotEmpty() and mToken.isNotEmpty()) {
            RetrofitFactory.create(UserApi::class.java)
                    .Check(UserCheckReq(mUuid, mToken))
                    .execute(object : BaseSubscriber<UserCheckMsg>() {
                        override fun onNext(t: UserCheckMsg) {
                            mRecMsgTv.LogMsg(t.toString())
                            when (t.data.err_code) {
                                0 -> {
                                    mUserLoginStateTv.text = "登录状态：用户已登录"
                                    isLogin = true
                                }
                                1 -> {
                                    mUserLoginStateTv.text = "登录状态：未登录，或登录态已过期"
                                    isLogin = false
                                }
                                else -> {
                                }
                            }
                        }
                    }, this)
        } else {
            mUserLoginStateTv.text = "登录状态：未登录"
            isLogin = false
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mUserCounterGetBtn.setOnClickListener(this)
        mUserCounterSetupBtn.setOnClickListener(this)
        mUserCounterSmartRefreshBtn.setOnClickListener(this)
        mUserCounterUpdateBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (!isLogin) {
            toast("请返回用户模块登录后再操作")
            return
        }
        when (view.id) {
            R.id.mUserCounterGetBtn -> {
                RetrofitFactory.create(UserCounterApi::class.java)
                        .Get(UserCounterGetReq(mUuid, mToken, HOUR, "counter_test"))
                        .execute(object : BaseSubscriber<UserCounterGetMsg>() {
                            override fun onNext(t: UserCounterGetMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserCounterSetupBtn -> {
                RetrofitFactory.create(UserCounterApi::class.java)
                        .Setup(UserCounterSetupReq(mUuid, mToken, HOUR, "counter_test", "0"))
                        .execute(object : BaseSubscriber<UserCounterSetupMsg>() {
                            override fun onNext(t: UserCounterSetupMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserCounterSmartRefreshBtn -> {
                RetrofitFactory.create(UserCounterApi::class.java)
                        .SmartRefresh(UserCounterSmartRefreshReq(mUuid, mToken, HOUR, "counter_test", "1"))
                        .execute(object : BaseSubscriber<UserCounterSmartRefreshMsg>() {
                            override fun onNext(t: UserCounterSmartRefreshMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserCounterUpdateBtn -> {
                RetrofitFactory.create(UserCounterApi::class.java)
                        .Update(UserCounterUpdateReq(mUuid, mToken, HOUR, "counter_test", "1"))
                        .execute(object : BaseSubscriber<UserCounterUpdateMsg>() {
                            override fun onNext(t: UserCounterUpdateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            else -> {
            }
        }
    }

}
