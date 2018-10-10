package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.*
import com.benjaminwan.okayapidemo.data.api.UserApi
import com.benjaminwan.okayapidemo.data.protocol.user.*
import com.benjaminwan.okayapidemo.data.request.user.*
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.benjaminwan.okayapidemo.utils.AppPrefsUtils
import com.google.gson.Gson
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.toast
import rx.Observable

class UserActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "UserActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@UserActivity)
        }
        val uuid = AppPrefsUtils.getString(KEY_SP_UUID)
        val token = AppPrefsUtils.getString(KEY_SP_TOKEN)
        if (uuid.isNotEmpty()) {
            mLastUuidEt.text = uuid
        }
        if (token.isNotEmpty()) {
            mLastTokenEt.text = token
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRegisterBtn.setOnClickListener(this)
        mLoginBtn.setOnClickListener(this)
        mCheckBtn.setOnClickListener(this)
        mProfileBtn.setOnClickListener(this)
        mUpdateExtInfoBtn.setOnClickListener(this)
    }

    override fun onClick(view: View): Unit {
        when (view.id) {
            R.id.mRegisterBtn -> {
                if (mUsernameEt.text.toString().isEmpty()) {
                    toast("请填写用户名")
                    return
                }

                if (mPasswordEt.text.toString().isEmpty()) {
                    toast("请填写密码")
                }

                RetrofitFactory.create(UserApi::class.java)
                        .Register(UserRegisterReq(mUsernameEt.text.toString(), mPasswordEt.text.toString().Md5()))
                        .execute(object : BaseSubscriber<UserRegisterMsg>() {
                            override fun onNext(t: UserRegisterMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mLoginBtn -> {
                if (mUsernameEt.text.toString().isEmpty()) {
                    toast("请填写用户名")
                    return
                }

                if (mPasswordEt.text.toString().isEmpty()) {
                    toast("请填写密码")
                }

                RetrofitFactory.create(UserApi::class.java)
                        .Login(UserLoginReq(mUsernameEt.text.toString(), mPasswordEt.text.toString().Md5()))
                        .execute(object : BaseSubscriber<UserLoginMsg>() {
                            override fun onNext(t: UserLoginMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                AppPrefsUtils.putString(KEY_SP_UUID, t.data.uuid)
                                AppPrefsUtils.putString(KEY_SP_TOKEN, t.data.token)
                                if (t.data.uuid.isNotEmpty()) {
                                    mLastUuidEt.text = t.data.uuid
                                }

                                if (t.data.token.isNotEmpty()) {
                                    mLastTokenEt.text = t.data.token
                                }
                            }
                        }, this)

            }
            R.id.mCheckBtn -> {
                val uuid = AppPrefsUtils.getString(KEY_SP_UUID)
                val token = AppPrefsUtils.getString(KEY_SP_TOKEN)
                if (uuid.isEmpty() or token.isEmpty()) {
                    toast("用户未登录")
                }

                RetrofitFactory.create(UserApi::class.java)
                        .Check(UserCheckReq(uuid, token))
                        .execute(object : BaseSubscriber<UserCheckMsg>() {
                            override fun onNext(t: UserCheckMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                val ret_msg = when (t.data.err_code) {
                                    0 -> {
                                        "用户已登录"
                                    }
                                    1 -> {
                                        "未登录，或登录态已过期"
                                    }
                                    else -> {
                                        "未知错误"
                                    }
                                }
                                mRecMsgTv.LogMsg(ret_msg)

                            }
                        }
                                , this)
            }

            R.id.mProfileBtn -> {
                val uuid = AppPrefsUtils.getString(KEY_SP_UUID)
                val token = AppPrefsUtils.getString(KEY_SP_TOKEN)
                if (uuid.isEmpty() or token.isEmpty()) {
                    toast("用户未登录")
                }

                RetrofitFactory.create(UserApi::class.java)
                        .Profile(UserProfileReq(uuid, token))
                        .execute(object : BaseSubscriber<UserProfileMsg>() {
                            override fun onNext(t: UserProfileMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                t.data.info.ext_info.forEach { (key, value) -> mRecMsgTv.LogMsg("key=$key,value=$value") }
                            }
                        }, this)
            }

            R.id.mUpdateExtInfoBtn -> {
                val uuid = AppPrefsUtils.getString(KEY_SP_UUID)
                val token = AppPrefsUtils.getString(KEY_SP_TOKEN)
                if (uuid.isEmpty() or token.isEmpty()) {
                    toast("用户未登录")
                }
                val ext_map = hashMapOf("nickname" to "testname", "age" to 22, "hello" to "world", "up" to "down")
                val ext_info = Gson().toJson(ext_map)
                RetrofitFactory.create(UserApi::class.java)
                        .UpdateExtInfo(UserUpdateExtInfoReq(uuid, token, ext_info))
                        .execute(object : BaseSubscriber<UserUpdateExtInfoMsg>() {
                            override fun onNext(t: UserUpdateExtInfoMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                t.data.ext_info.forEach { (key, value) -> mRecMsgTv.LogMsg("key=$key,value=$value") }
                            }
                        }, this)
            }

            else -> {
            }
        }
    }

}
