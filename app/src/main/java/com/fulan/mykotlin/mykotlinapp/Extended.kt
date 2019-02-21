package com.fulan.mykotlin.mykotlinapp

import android.content.Context
import android.widget.Toast


/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp.fragment
 * @Description:
 * @author: fjm
 * @date: 2018/12/18 17:17
 * @Version:1.0
 */
fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, length).show()
}
