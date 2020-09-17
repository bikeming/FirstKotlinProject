package com.bike.mykotlin.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.bike.mykotlin.R
import com.bike.mykotlin.adapter.ViewpagerAdapter
import com.bike.mykotlin.fragment.BlogFragment
import com.bike.mykotlin.fragment.ProjectFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mViewPagerAdapter: ViewpagerAdapter by lazy {
        ViewpagerAdapter(supportFragmentManager, getFragmentList(), listOf("博客", "项目"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTabLayout.setupWithViewPager(mViewPager.apply { adapter = mViewPagerAdapter })
    }

    private fun getFragmentList(): List<Fragment> {
        return arrayListOf<Fragment>().apply {
            add(BlogFragment.newInstance())
            add(ProjectFragment.newInstance())
        }
    }

}
