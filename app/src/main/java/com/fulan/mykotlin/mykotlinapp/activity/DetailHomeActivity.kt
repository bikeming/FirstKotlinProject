package com.fulan.mykotlin.mykotlinapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.fulan.mykotlin.mykotlinapp.Constant
import com.fulan.mykotlin.mykotlinapp.R
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.layout_detail_home.*

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp.activity
 * @Description:详情页
 * @author: fjm
 * @date: 2018/9/3 15:51
 * @Version:1.0
 */
class DetailHomeActivity : AppCompatActivity(), View.OnClickListener {

    protected var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_detail_home)
        url = intent.extras.getString(Constant.URL_DETAIL)

        initView()
    }

    private fun initView() {
        tv_detail.setOnClickListener(this)

        val settings = webview.settings
        settings.setSupportZoom(true)
        settings.displayZoomControls = false
        settings.useWideViewPort = true

        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webView: com.tencent.smtt.sdk.WebView?, url: String?): Boolean {
                webView!!.loadUrl(url)
                return true
            }

        }
        webview.loadUrl(url)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_detail -> finish()

        }
    }

    override fun onBackPressed() {
        if (webview != null && webview.canGoBack()) webview.goBack() else finish()
    }

}