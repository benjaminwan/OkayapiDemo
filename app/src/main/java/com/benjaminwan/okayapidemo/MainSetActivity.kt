package com.benjaminwan.okayapidemo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.benjaminwan.okayapidemo.common.BaseSubscriber
import com.benjaminwan.okayapidemo.common.LogMsg
import com.benjaminwan.okayapidemo.common.execute
import com.benjaminwan.okayapidemo.data.api.MainSetApi
import com.benjaminwan.okayapidemo.data.protocol.mainset.*
import com.benjaminwan.okayapidemo.data.request.mainset.*
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.google.gson.Gson
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main_set.*
import rx.Observable

class MainSetActivity : RxAppCompatActivity(), View.OnClickListener {
    private val TAG = "MainSetActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_set)
        initViews()
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@MainSetActivity)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mMainSetAddBtn.setOnClickListener(this)
        mMainSetClearBtn.setOnClickListener(this)
        mMainSetCountBtn.setOnClickListener(this)
        mMainSetDeleteBtn.setOnClickListener(this)
        mMainSetGetItemBtn.setOnClickListener(this)
        mMainSetGetListBtn.setOnClickListener(this)
        mMainSetQueryBtn.setOnClickListener(this)
        mMainSetUpdateBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mMainSetAddBtn -> {
                val data_map1 = hashMapOf("name" to "test1", "age" to 18)
                val data_str1 = Gson().toJson(data_map1)
                RetrofitFactory.create(MainSetApi::class.java)
                        .Add(MainSetAddReq("key_names", data_str1))
                        .execute(object : BaseSubscriber<MainSetAddMsg>() {
                            override fun onNext(t: MainSetAddMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)

                val data_map2 = hashMapOf("name" to "test2", "age" to 19)
                val data_str2 = Gson().toJson(data_map2)
                RetrofitFactory.create(MainSetApi::class.java)
                        .Add(MainSetAddReq("key_names", data_str2))
                        .execute(object : BaseSubscriber<MainSetAddMsg>() {
                            override fun onNext(t: MainSetAddMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)

            }
            R.id.mMainSetClearBtn -> {
                RetrofitFactory.create(MainSetApi::class.java)
                        .Clear(MainSetClearReq("key_names"))
                        .execute(object : BaseSubscriber<MainSetClearMsg>() {
                            override fun onNext(t: MainSetClearMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainSetCountBtn -> {
                RetrofitFactory.create(MainSetApi::class.java)
                        .Count(MainSetCountReq("key_names"))
                        .execute(object : BaseSubscriber<MainSetCountMsg>() {
                            override fun onNext(t: MainSetCountMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainSetDeleteBtn -> {
                RetrofitFactory.create(MainSetApi::class.java)
                        .Delete(MainSetDeleteReq("1"))
                        .execute(object : BaseSubscriber<MainSetDeleteMsg>() {
                            override fun onNext(t: MainSetDeleteMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainSetGetItemBtn -> {
                RetrofitFactory.create(MainSetApi::class.java)
                        .GetItem(MainSetGetItemReq("1"))
                        .execute(object : BaseSubscriber<MainSetGetItemMsg>() {
                            override fun onNext(t: MainSetGetItemMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainSetGetListBtn -> {
                RetrofitFactory.create(MainSetApi::class.java)
                        .GetList(MainSetGetListReq("key_names"))
                        .execute(object : BaseSubscriber<MainSetGetListMsg>() {
                            override fun onNext(t: MainSetGetListMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainSetQueryBtn -> {
                RetrofitFactory.create(MainSetApi::class.java)
                        .Query(MainSetQueryReq("key_names", ""))
                        .execute(object : BaseSubscriber<MainSetQueryMsg>() {
                            override fun onNext(t: MainSetQueryMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mMainSetUpdateBtn -> {
                val data_map = hashMapOf("name" to "test1_modify", "age" to 28, "hello" to "world")
                val data_str = Gson().toJson(data_map)
                RetrofitFactory.create(MainSetApi::class.java)
                        .Update(MainSetUpdateReq("1", data_str))
                        .execute(object : BaseSubscriber<MainSetUpdateMsg>() {
                            override fun onNext(t: MainSetUpdateMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            else -> {
            }
        }
    }

}
