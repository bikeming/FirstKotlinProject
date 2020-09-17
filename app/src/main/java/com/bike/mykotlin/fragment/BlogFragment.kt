package com.bike.mykotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bike.mykotlin.Constant
import com.bike.mykotlin.R
import com.bike.mykotlin.activity.DetailHomeActivity
import com.bike.mykotlin.adapter.BlogListAdapter
import com.bike.mykotlin.bean.HomeBean
import com.bike.mykotlin.network.MRetrofit
import com.bike.mykotlin.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * @ClassName: com.bike.mykotlin.fragment
 * @Description: 博客模块
 * @author: fjm
 * @date: 2018/8/24 11:17
 * @Version:1.0
 */
class BlogFragment : Fragment() {

    private val mAdapter: BlogListAdapter by lazy {
        BlogListAdapter()
    }
    private var mPage: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        with(mAdapter) {
            bindToRecyclerView(recyclerView)

            setOnItemClickListener { _, _, position ->
                startActivity(Intent().apply {
                    setClass(activity, DetailHomeActivity::class.java)
                    putExtra(Constant.URL_DETAIL, mAdapter.data[position].link)
                })
            }
            setOnLoadMoreListener({
                mPage++
                getBlogsData()
            }, recyclerView)
        }

        smartRefreshLayout.setOnRefreshListener {
            mPage = 0
            getBlogsData()
        }

        getBlogsData()
    }

    private fun getBlogsData() {
        val call = MRetrofit.instance.getHttpApi().getHomeData(mPage)
        call.enqueue(object : Callback<HomeBean> {
            override fun onResponse(call: Call<HomeBean>, response: Response<HomeBean>) {
                if (response.isSuccessful) {
                    val total = response.body().data.total
                    if (total <= mAdapter.data.size) {
                        mAdapter.loadMoreEnd()
                        return
                    }
                    if (smartRefreshLayout.isRefreshing) {
                        mAdapter.setNewData(response.body().data.datas)
                        smartRefreshLayout.finishRefresh()
                    } else {
                        mAdapter.addData(response.body().data.datas)
                        mAdapter.loadMoreComplete()
                    }

                }
            }

            override fun onFailure(call: Call<HomeBean>, t: Throwable) {
                smartRefreshLayout?.takeIf { it.isRefreshing }?.finishRefresh()
                        ?: mAdapter.loadMoreFail()
                activity.showToast("blog获取失败")
            }
        })
    }

    companion object {
        fun newInstance(): BlogFragment = BlogFragment()
    }
}