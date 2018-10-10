package com.benjaminwan.okayapidemo.common

import android.widget.TextView
import com.benjaminwan.okayapidemo.utils.DateUtils
import com.benjaminwan.okayapidemo.utils.DateUtils.FORMAT_HALF
import com.benjaminwan.okayapidemo.utils.SignUtils.getSignStr
import com.benjaminwan.okayapidemo.utils.SignUtils.signMd5
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

fun String.Md5(): String {
    return signMd5(this, false)
}

fun SortedMap<String, String>.sign(): String {
    return getSignStr(this)
}

fun TextView.LogMsg(msg: String) {
    val now = DateUtils.getNow(FORMAT_HALF)
    this.append("$now\n$msg\n")
    val offset = this.getLineCount() * this.getLineHeight()
    if (offset > this.getHeight() - this.getLineHeight()) {
        this.scrollTo(0, offset - this.getHeight() + this.getLineHeight())
    }
}

