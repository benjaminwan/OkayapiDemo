package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.BaseSubscriber
import com.benjaminwan.okayapidemo.common.LogMsg
import com.benjaminwan.okayapidemo.common.execute
import com.benjaminwan.okayapidemo.data.api.MainMetaApi
import com.benjaminwan.okayapidemo.data.protocol.mainmeta.*
import com.benjaminwan.okayapidemo.data.request.mainmeta.*
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.google.gson.Gson
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_meta.*
import rx.Observable

class MainMetaActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "MainMetaActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_meta)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@MainMetaActivity)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mMainMetaCreateBtn.setOnClickListener(this)
        mMainMetaDeleteBtn.setOnClickListener(this)
        mMainMetaGetBtn.setOnClickListener(this)
        mMainMetaMultiGetBtn.setOnClickListener(this)
        mMainMetaUpdateBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mMainMetaCreateBtn -> {
                val data_map = hashMapOf("x" to 10, "y" to 20, "z" to 30, "abc" to "cde")
                val data_str = Gson().toJson(data_map)

                RetrofitFactory.create(MainMetaApi::class.java)
                        .Create(MainMetaCreateReq("key_test", data_str))
                        .execute(object : BaseSubscriber<MainMetaCreateMsg>() {
                            override fun onNext(t: MainMetaCreateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainMetaDeleteBtn -> {
                RetrofitFactory.create(MainMetaApi::class.java)
                        .Delete(MainMetaDeleteReq("key_test"))
                        .execute(object : BaseSubscriber<MainMetaDeleteMsg>() {
                            override fun onNext(t: MainMetaDeleteMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)


            }
            R.id.mMainMetaGetBtn -> {
                RetrofitFactory.create(MainMetaApi::class.java)
                        .Get(MainMetaGetReq("key_test"))
                        .execute(object : BaseSubscriber<MainMetaGetMsg>() {
                            override fun onNext(t: MainMetaGetMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                t.data.data.forEach { (key, value) -> mRecMsgTv.LogMsg("key=$key,value=$value,type=${value.javaClass.simpleName}") }
                            }
                        }, this)
            }
            R.id.mMainMetaMultiGetBtn -> {
                val array_keys = arrayListOf("key_test", "key_test1")
                val keys = array_keys.joinToString(",")

                RetrofitFactory.create(MainMetaApi::class.java)
                        .MultiGet(MainMetaMultiGetReq(keys))
                        .execute(object : BaseSubscriber<MainMetaMultiGetMsg>() {
                            override fun onNext(t: MainMetaMultiGetMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                                t.data.items.forEach { (key, items) ->
                                    mRecMsgTv.LogMsg("meta key=$key")
                                    items.forEach { (key, value) -> mRecMsgTv.LogMsg("key=$key,value=${value.toString()}") }
                                }
                            }
                        }, this)
            }
            R.id.mMainMetaUpdateBtn -> {
                val data_map = hashMapOf("x" to 20, "y" to 30, "z" to 50, "abc" to "kkk", "888" to "999")
                val data_str = Gson().toJson(data_map)
                RetrofitFactory.create(MainMetaApi::class.java)
                        .Update(MainMetaUpdateReq("key_test", data_str))
                        .execute(object : BaseSubscriber<MainMetaUpdateMsg>() {
                            override fun onNext(t: MainMetaUpdateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }

            else -> {
            }
        }
    }
}
