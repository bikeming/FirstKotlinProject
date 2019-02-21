package com.fulan.mykotlin.mykotlinapp.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.fulan.mykotlin.mykotlinapp.R
import com.fulan.mykotlin.mykotlinapp.adapter.ViewpagerAdapter
import com.fulan.mykotlin.mykotlinapp.fragment.BlogFragment
import com.fulan.mykotlin.mykotlinapp.fragment.ProjectFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mViewPagerAdapter: ViewpagerAdapter by lazy {
        ViewpagerAdapter(supportFragmentManager, getFragmentList(), listOf("博客", "项目"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
    }

    private fun initViewPager() {
        mViewPager.adapter = mViewPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)
    }

    private fun getFragmentList(): ArrayList<Fragment> {
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.add(BlogFragment.newInstance())
        fragmentList.add(ProjectFragment.newInstance())
        return fragmentList
    }

}
