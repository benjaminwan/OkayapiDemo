package com.benjaminwan.okayapidemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import android.util.Base64
import android.util.Log
import android.view.View
import com.benjaminwan.okayapidemo.common.*
import com.benjaminwan.okayapidemo.data.api.CDNApi
import com.benjaminwan.okayapidemo.data.protocol.cdn.CDNUploadImgByBase64Msg
import com.benjaminwan.okayapidemo.data.protocol.cdn.CDNUploadImgMsg
import com.benjaminwan.okayapidemo.data.request.cdn.CDNUploadImgByBase64Req
import com.benjaminwan.okayapidemo.net.RetrofitFactory
import com.benjaminwan.okayapidemo.utils.DateUtils
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_cdn.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.Observable
import java.io.File

class CDNActivity : RxAppCompatActivity(), View.OnClickListener, TakePhoto.TakeResultListener {
    private val TAG = "CDNActivity"
    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private var mLocalFileName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdn)
        initViews()
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        RetrofitFactory.setOnHttpRequestListener { msg ->
            Observable.just(msg)
                    .execute(object : BaseSubscriber<String>() {
                        override fun onNext(t: String) {
                            mSendMsgTv.LogMsg(t.toString())
                        }
                    }, this@CDNActivity)
        }
    }

    private fun initViews() {
        mSendMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mRecMsgTv.movementMethod = ScrollingMovementMethod.getInstance()
        mUploadImgBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUploadImgBtn -> {
                showAlertView()
            }
            else -> {
            }
        }
    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                when (position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> {
                        mTakePhoto.onPickFromGallery()
                    }

                    else -> {
                    }
                }
            }
        }).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }

    override fun takeSuccess(result: TResult?) {
        println("take success ${result?.image?.originalPath} ${result?.image?.compressPath}")
        mLocalFileName = result?.image?.compressPath
        Log.i(TAG, mLocalFileName)
        val file = File(mLocalFileName)
        when (mUploadTypeRg.checkedRadioButtonId) {
            R.id.mUploadImgRb -> {//文件方式上传
                val requestFile = RequestBody.create(MediaType.parse("image/jpg"), file)
                val fileBody = MultipartBody.Part.createFormData("file", file.name, requestFile)
                val queryMap = mapOf("s" to APP_CDN_UPLOADIMAGE, "app_key" to APP_KEY, "sign" to sortedMapOf<String, String>("s" to APP_CDN_UPLOADIMAGE).sign())
                RetrofitFactory.create(CDNApi::class.java)
                        .UploadImg(queryMap, fileBody)
                        .execute(object : BaseSubscriber<CDNUploadImgMsg>() {
                            override fun onNext(t: CDNUploadImgMsg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            R.id.mUploadBase64Rb -> {//base64方式上传
                val encode = Base64.encodeToString(file.readBytes(), Base64.DEFAULT)
                val base64file = "data:image/jpg;base64,$encode"
                Log.i(TAG, base64file)
                RetrofitFactory.create(CDNApi::class.java)
                        .UploadImgByBase64(CDNUploadImgByBase64Req(base64file, file.name))
                        .execute(object : BaseSubscriber<CDNUploadImgByBase64Msg>() {
                            override fun onNext(t: CDNUploadImgByBase64Msg) {
                                mRecMsgTv.LogMsg(t.toString())
                            }
                        }, this)
            }
            else -> {
            }
        }


    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {

    }
}
