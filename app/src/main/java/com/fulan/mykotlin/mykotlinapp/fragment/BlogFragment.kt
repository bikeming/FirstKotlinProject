package com.fulan.mykotlin.mykotlinapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fulan.mykotlin.mykotlinapp.Constant
import com.fulan.mykotlin.mykotlinapp.R
import com.fulan.mykotlin.mykotlinapp.activity.DetailHomeActivity
import com.fulan.mykotlin.mykotlinapp.adapter.BlogListAdapter
import com.fulan.mykotlin.mykotlinapp.bean.HomeBean
import com.fulan.mykotlin.mykotlinapp.network.MRetrofit
import com.fulan.mykotlin.mykotlinapp.showToast
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp.fragment
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

        recyclerView.run {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            setHasFixedSize(true)
            adapter = mAdapter
        }

        mAdapter.run {
            bindToRecyclerView(recyclerView)

            setOnItemClickListener({ adapte, view, position ->
                val intent = Intent()
                intent.setClass(activity, DetailHomeActivity::class.java)
                intent.putExtra(Constant.URL_DETAIL, mAdapter.data[position].link)
                startActivity(intent)
            })
            setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
                mPage++
                getBlogsData()
            }, recyclerView)
        }

        smartRefreshLayout.setOnRefreshListener({
            mPage = 0
            getBlogsData()
        })

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
                if (smartRefreshLayout.isRefreshing) {
                    smartRefreshLayout.finishRefresh()
                } else {
                    mAdapter.loadMoreFail()
                }
                activity.showToast("blog获取失败")
            }
        })
    }

    companion object {
        fun newInstance(): BlogFragment {
            val fragment = BlogFragment()
            return fragment
        }
    }
}