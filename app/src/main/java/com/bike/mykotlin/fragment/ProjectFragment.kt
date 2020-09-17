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
import com.bike.mykotlin.adapter.ProjectListAdapter
import com.bike.mykotlin.bean.ProjectBean
import com.bike.mykotlin.network.MRetrofit
import com.bike.mykotlin.showToast
import kotlinx.android.synthetic.main.fragment_project.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * @ClassName: com.bike.mykotlin.fragment
 * @Description:项目模块
 * @author: fjm
 * @date: 2018/8/22 11:34
 * @Version:1.0
 */
class ProjectFragment : Fragment() {

    private val adapter: ProjectListAdapter by lazy {
        ProjectListAdapter()
    }
    private val projectLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_project, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(mRecyclerView) {
            layoutManager = projectLayoutManager
            setHasFixedSize(true)
            adapter = adapter
        }
        with(adapter) {
            bindToRecyclerView(mRecyclerView)
            setOnItemClickListener { _, _, position ->
                startActivity(Intent().apply {
                    setClass(activity, DetailHomeActivity::class.java)
                    putExtra(Constant.URL_DETAIL, adapter.data[position].link)
                })
            }
        }

        initData()
    }

    private fun initData() {
        val call = MRetrofit.instance.getHttpApi().getProjectData()
        call.enqueue(object : Callback<ProjectBean> {
            override fun onResponse(call: Call<ProjectBean>, response: Response<ProjectBean>) {
                if (response.isSuccessful) {
                    adapter.setNewData(response.body().data.datas)
                }
            }

            override fun onFailure(call: Call<ProjectBean>, t: Throwable) {
                activity.showToast("获取失败")

            }
        })
    }

    companion object {
        fun newInstance(): ProjectFragment = ProjectFragment()
    }

}