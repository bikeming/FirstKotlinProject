package com.bike.mykotlin.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bike.mykotlin.R
import kotlinx.android.synthetic.main.fragment_student.*

/**
 *
 * @ClassName: com.bike.mykotlin.fragment
 * @Description:
 * @author: fjm
 * @date: 2018/8/22 11:33
 * @Version:1.0
 */
class StudyFragment : Fragment() {
    companion object {
        fun newInstance(): StudyFragment {
            val fragment = StudyFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mContentView = inflater.inflate(R.layout.fragment_student, container, false)
        return mContentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button.setOnClickListener(View.OnClickListener {

//            val uriStr = "gankao://myLiveCourseList?device_id=**&partner_id=**&course_id=**&sign=**"
            val uriStr = "gankao://test_host"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriStr))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
        })
    }
}