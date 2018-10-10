package com.benjaminwan.okayapidemo.common

//Hello World接口
const val APP_HELLOWORLD = "App.Hello.World"
//用户模块
const val APP_USER_CHECK = "App.User.Check"
const val APP_USER_LOGIN = "App.User.Login"
const val APP_USER_PROFILE = "App.User.Profile"
const val APP_USER_REGISTER = "App.User.Register"
const val APP_USER_UPDATEEXTINFO = "App.User.UpdateExtInfo"
//用户元数据
const val APP_USER_META_CREATE = "App.User_Meta.Create"
const val APP_USER_META_DELETE = "App.User_Meta.Delete"
const val APP_USER_META_GET = "App.User_Meta.Get"
const val APP_USER_META_MULTIGET = "App.User_Meta.MultiGet"
const val APP_USER_META_UPDATE = "App.User_Meta.Update"
//用户集合
const val APP_USER_SET_ADD = "App.User_Set.Add"
const val APP_USER_SET_CLEAR = "App.User_Set.Clear"
const val APP_USER_SET_COUNT = "App.User_Set.Count"
const val APP_USER_SET_DELETE = "App.User_Set.Delete"
const val APP_USER_SET_GETITEM = "App.User_Set.GetItem"
const val APP_USER_SET_GETLIST = "App.User_Set.GetList"
const val APP_USER_SET_QUERY = "App.User_Set.Query"
const val APP_USER_SET_UPDATE = "App.User_Set.Update"
//用户计数器
const val APP_USER_COUNTER_GET = "App.User_Counter.Get"
const val APP_USER_COUNTER_SETUP = "App.User_Counter.Setup"
const val APP_USER_COUNTER_SMARTREFRESH = "App.User_Counter.SmartRefresh"
const val APP_USER_COUNTER_UPDATE = "App.User_Counter.Update"
//应用元数据
const val APP_MAIN_META_CREATE = "App.Main_Meta.Create"
const val APP_MAIN_META_DELETE = "App.Main_Meta.Delete"
const val APP_MAIN_META_GET = "App.Main_Meta.Get"
const val APP_MAIN_META_MULTIGET = "App.Main_Meta.MultiGet"
const val APP_MAIN_META_UPDATE = "App.Main_Meta.Update"
//应用集合
const val APP_MAIN_SET_ADD = "App.Main_Set.Add"
const val APP_MAIN_SET_CLEAR = "App.Main_Set.Clear"
const val APP_MAIN_SET_COUNT = "App.Main_Set.Count"
const val APP_MAIN_SET_DELETE = "App.Main_Set.Delete"
const val APP_MAIN_SET_GETITEM = "App.Main_Set.GetItem"
const val APP_MAIN_SET_GETLIST = "App.Main_Set.GetList"
const val APP_MAIN_SET_QUERY = "App.Main_Set.Query"
const val APP_MAIN_SET_UPDATE = "App.Main_Set.Update"
//应用计数器
const val APP_MAIN_COUNTER_GET = "App.Main_Counter.Get"
const val APP_MAIN_COUNTER_SETUP = "App.Main_Counter.Setup"
const val APP_MAIN_COUNTER_SMARTREFRESH = "App.Main_Counter.SmartRefresh"
const val APP_MAIN_COUNTER_UPDATE = "App.Main_Counter.Update"
//日志模块
const val APP_LOGGER_RECORD = "App.Logger.Record"

//CDN上传
const val APP_CDN_UPLOADIMAGE = "App.CDN.UploadImg"
const val APP_CDN_UPLOADIMAGE_BYBASE64 = "App.CDN.UploadImgByBase64"
const val APP_CDN_UPLOADOFFICE = "App.CDN.UploadOffice"
//邮件服务
const val APP_EMAIL_SEND = "App.Email.Send"
const val APP_EMAIL_SENDCAPTCHA = "App.Email.SendCaptcha"
const val APP_EMAIL_VERIFYCAPTCHA = "App.Email.VerifyCaptcha"
//图形验证码
const val APP_CAPTCHA_CREATE = "App.Captcha.Create"
const val APP_CAPTCHA_VERIFY = "App.Captcha.Verify"
const val CAPTCHA_OUTPUT = "output"//直接输出验证码图片
const val CAPTCHA_DATA = "data"//返回base64后的验证码图片数据
//用户头像
const val EXT_AVATAR_SHOW = "Ext.Avatar.Show"
//条形码
const val EXT_BARCODE_GEN = "Ext.BarCode.Gen"
//IP服务
const val EXT_IP_GETINFO = "Ext.IP.GetInfo"
//拼音服务
const val EXT_PINYIN_ABBR = "Ext.Pinyin.Abbr"
const val EXT_PINYIN_CONVERT = "Ext.Pinyin.Convert"
const val EXT_PINYIN_NAME = "Ext.Pinyin.Name"
const val EXT_PINYIN_SENTENCE = "Ext.Pinyin.Sentence"
//二维码服务
const val EXT_QRCODE_DECODE = "Ext.QrCode.Decode"
const val EXT_QRCODE_PNG = "Ext.QrCode.Png"