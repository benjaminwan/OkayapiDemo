package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.*
import com.benjaminwan.okayapidemo.data.api.UserApi
import com.benjaminwan.okayapidemo.data.api.UserSetApi
import com.benjaminwan.okayapidemo.data.protocol.user.UserCheckMsg
import com.benjaminwan.okayapidemo.data.protocol.userset.*
import com.benjaminwan.okayapidemo.data.request.user.UserCheckReq
import com.benjaminwan.okayapidemo.data.request.userset.*
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.benjaminwan.okayapidemo.utils.AppPrefsUtils
import com.google.gson.Gson
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_user_set.*
import org.jetbrains.anko.toast
import rx.Observable

class UserSetActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "UserSetActivity"
    private var isLogin = false
    private var mUuid = ""
    private var mToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_set)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@UserSetActivity)
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
        mUserSetAddBtn.setOnClickListener(this)
        mUserSetClearBtn.setOnClickListener(this)
        mUserSetCountBtn.setOnClickListener(this)
        mUserSetDeleteBtn.setOnClickListener(this)
        mUserSetGetItemBtn.setOnClickListener(this)
        mUserSetGetListBtn.setOnClickListener(this)
        mUserSetQueryBtn.setOnClickListener(this)
        mUserSetUpdateBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (!isLogin) {
            toast("请返回用户模块登录后再操作")
            return
        }
        when (view.id) {
            R.id.mUserSetAddBtn -> {
                val data_map1 = hashMapOf("name" to "test1", "age" to 18)
                val data_str1 = Gson().toJson(data_map1)
                RetrofitFactory.create(UserSetApi::class.java)
                        .Add(UserSetAddReq(mUuid, mToken, "key_names", data_str1))
                        .execute(object : BaseSubscriber<UserSetAddMsg>() {
                            override fun onNext(t: UserSetAddMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)

                val data_map2 = hashMapOf("name" to "test2", "age" to 19)
                val data_str2 = Gson().toJson(data_map2)
                RetrofitFactory.create(UserSetApi::class.java)
                        .Add(UserSetAddReq(mUuid, mToken, "key_names", data_str2))
                        .execute(object : BaseSubscriber<UserSetAddMsg>() {
                            override fun onNext(t: UserSetAddMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserSetClearBtn -> {
                RetrofitFactory.create(UserSetApi::class.java)
                        .Clear(UserSetClearReq(mUuid, mToken, "key_names"))
                        .execute(object : BaseSubscriber<UserSetClearMsg>() {
                            override fun onNext(t: UserSetClearMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserSetCountBtn -> {
                RetrofitFactory.create(UserSetApi::class.java)
                        .Count(UserSetCountReq(mUuid, mToken, "key_names"))
                        .execute(object : BaseSubscriber<UserSetCountMsg>() {
                            override fun onNext(t: UserSetCountMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)

            }
            R.id.mUserSetDeleteBtn -> {
                RetrofitFactory.create(UserSetApi::class.java)
                        .Delete(UserSetDeleteReq(mUuid, mToken, "1"))
                        .execute(object : BaseSubscriber<UserSetDeleteMsg>() {
                            override fun onNext(t: UserSetDeleteMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserSetGetItemBtn -> {
                RetrofitFactory.create(UserSetApi::class.java)
                        .GetItem(UserSetGetItemReq(mUuid, mToken, "1"))
                        .execute(object : BaseSubscriber<UserSetGetItemMsg>() {
                            override fun onNext(t: UserSetGetItemMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserSetGetListBtn -> {
                RetrofitFactory.create(UserSetApi::class.java)
                        .GetList(UserSetGetListReq(mUuid, mToken, "key_names"))
                        .execute(object : BaseSubscriber<UserSetGetListMsg>() {
                            override fun onNext(t: UserSetGetListMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserSetQueryBtn -> {
                RetrofitFactory.create(UserSetApi::class.java)
                        .Query(UserSetQueryReq(mUuid, mToken, "key_names", ""))
                        .execute(object : BaseSubscriber<UserSetQueryMsg>() {
                            override fun onNext(t: UserSetQueryMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUserSetUpdateBtn -> {
                val data_map = hashMapOf("name" to "test2_modify", "age" to 28, "hello" to "world")
                val data_str = Gson().toJson(data_map)
                RetrofitFactory.create(UserSetApi::class.java)
                        .Update(UserSetUpdateReq(mUuid, mToken, "1", data_str))
                        .execute(object : BaseSubscriber<UserSetUpdateMsg>() {
                            override fun onNext(t: UserSetUpdateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            else -> {
            }
        }
    }

}
