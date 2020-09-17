package com.bike.mykotlin

import android.app.Application
import android.util.Log
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.smtt.sdk.QbSdk

/**
 * @ClassName: com.bike.mykotlin
 * @Description:
 * @author: fjm
 * @date: 2018/9/3 14:23
 * @Version:1.0
 */

class MApp : Application() {

    companion object {
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, refreshLayout ->
                //全局设置主题颜色
                refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
                //指定 Header
                ClassicsHeader(context)
            }
//            SmartRefreshLayout.setDefaultRefreshFooterCreater { context, layout ->
//                //默认是 BallPulseFooter
//                BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary))
//            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        val cb = object : QbSdk.PreInitCallback {
            override fun onViewInitFinished(arg0: Boolean) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is $arg0")
            }

            override fun onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        }
        //x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, cb)
    }
}
