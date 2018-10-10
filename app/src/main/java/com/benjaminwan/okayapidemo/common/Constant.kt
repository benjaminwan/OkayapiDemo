package com.benjaminwan.okayapidemo.common

//以下使用演示版账号api_demo的接入凭证
const val SERVER_ADDRESS = "http://api.okayapi.com/"
const val APP_KEY = "16BD4337FB1D355902E0502AFCBFD4DF"
const val APP_SECRECT = "4c1402596e4cd017eeaO670df6f8B6783475b4ac8A32B4900f20abP2159711ad"

//prefs key
const val PREFS = "okayapidemo"
const val KEY_SP_UUID = "sp_uuid"//存储最后一次登录的uuid
const val KEY_SP_TOKEN = "sp_token"//存储最后一次登录的token

//可见性
const val VISIABLE_PRIVATE = "private"
const val VISIABLE_PROTECTED = "protected"
const val VISIABLE_PUBLIC = "public"

//计数器类型
typealias CounterTpye = String
const val HOUR: CounterTpye = "hour"
const val DAY: CounterTpye = "day"
const val WEEK: CounterTpye = "week"
const val MONTH: CounterTpye = "month"
const val YEAR: CounterTpye = "year"
const val FOREVER: CounterTpye = "forever"

//日志级别
typealias LogTpye = String
const val ERROR: LogTpye = "ERROR"
const val WARNING: LogTpye = "WARNING"
const val NOTICE: LogTpye = "NOTICE"
const val INFO: LogTpye = "INFO"
const val DEBUG: LogTpye = "DEBUG"