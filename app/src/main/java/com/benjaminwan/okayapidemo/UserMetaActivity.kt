package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.*
import com.benjaminwan.okayapidemo.data.api.UserApi
import com.benjaminwan.okayapidemo.data.api.UserMetaApi
import com.benjaminwan.okayapidemo.data.protocol.user.UserCheckMsg
import com.benjaminwan.okayapidemo.data.protocol.usermeta.*
import com.benjaminwan.okayapidemo.data.request.user.UserCheckReq
import com.benjaminwan.okayapidemo.data.request.usermeta.*
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.benjaminwan.okayapidemo.utils.AppPrefsUtils
import com.google.gson.Gson
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_user_meta.*
import org.jetbrains.anko.toast
import rx.Observable

class UserMetaActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "UserMetaActivity"
    private var isLogin = false
    private var mUuid = ""
    private var mToken = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_meta)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@UserMetaActivity)
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
        mUserMetaCreateBtn.setOnClickListener(this)
        mUserMetaDeleteBtn.setOnClickListener(this)
        mUserMetaGetBtn.setOnClickListener(this)
        mUserMetaMultiGetBtn.setOnClickListener(this)
        mUserMetaUpdateBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (!isLogin) {
            toast("请返回用户模块登录后再操作")
            return
        }
        when (view.id) {
            R.id.mUserMetaCreateBtn -> {
                val data_map = hashMapOf("x" to 10, "y" to 20, "z" to 30, "abc" to "cde")
                val data_str = Gson().toJson(data_map)
                RetrofitFactory.create(UserMetaApi::class.java)
                        .Create(UserMetaCreateReq(mUuid, mToken, "key_test", data_str, VISIABLE_PUBLIC))
                        .execute(object : BaseSubscriber<UserMetaCreateMsg>() {
                            override fun onNext(t: UserMetaCreateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }

            R.id.mUserMetaDeleteBtn -> {
                RetrofitFactory.create(UserMetaApi::class.java)
                        .Delete(UserMetaDeleteReq(mUuid, mToken, "key_test"))
                        .execute(object : BaseSubscriber<UserMetaDeleteMsg>() {
                            override fun onNext(t: UserMetaDeleteMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }

            R.id.mUserMetaGetBtn -> {
                RetrofitFactory.create(UserMetaApi::class.java)
                        .Get(UserMetaGetReq(mUuid, mToken, "key_test"))
                        .execute(object : BaseSubscriber<UserMetaGetMsg>() {
                            override fun onNext(t: UserMetaGetMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                t.data.data.forEach { (key, value) -> mRecMsgTv.LogMsg("key=$key,value=$value,type=${value.javaClass.simpleName}") }
                            }
                        }, this)

            }
            R.id.mUserMetaMultiGetBtn -> {
                val array_keys = arrayListOf("key_test", "key_test1")
                val keys = array_keys.joinToString(",")
                RetrofitFactory.create(UserMetaApi::class.java)
                        .MultiGet(UserMetaMultiGetReq(mUuid, mToken, keys))
                        .execute(object : BaseSubscriber<UserMetaMultiGetMsg>() {
                            override fun onNext(t: UserMetaMultiGetMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                //t.data.items.forEach { (key, value) -> logMsg("key=$key,value=${value.toString()}") }
                                t.data.items.forEach { (key, items) ->
                                    mRecMsgTv.LogMsg("meta key=$key")
                                    items.forEach { (key, value) -> mRecMsgTv.LogMsg("key=$key,value=${value.toString()}") }
                                }
                            }
                        }, this)
            }
            R.id.mUserMetaUpdateBtn -> {
                val data_map = hashMapOf("x" to 20, "y" to 30, "z" to 50, "abc" to "kkk", "888" to "999")
                val data_str = Gson().toJson(data_map)
                RetrofitFactory.create(UserMetaApi::class.java)
                        .Update(UserMetaUpdateReq(mUuid, mToken, "key_test", data_str))
                        .execute(object : BaseSubscriber<UserMetaUpdateMsg>() {
                            override fun onNext(t: UserMetaUpdateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            else -> {
            }
        }
    }

}
