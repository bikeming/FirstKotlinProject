package com.fulan.mykotlin.mykotlinapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp.adapter
 * @Description:
 * @author: fjm
 * @date: 2018/8/22 10:59
 * @Version:1.0
 */
class ViewpagerAdapter(fragmentManager: FragmentManager, private var fragmentList: List<Fragment>,
                       private var titleName: List<String>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleName[position]
    }
}