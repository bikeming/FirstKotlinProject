package com.fulan.mykotlin.mykotlinapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fulan.mykotlin.mykotlinapp.Constant
import com.fulan.mykotlin.mykotlinapp.R
import com.fulan.mykotlin.mykotlinapp.activity.DetailHomeActivity
import com.fulan.mykotlin.mykotlinapp.adapter.ProjectListAdapter
import com.fulan.mykotlin.mykotlinapp.bean.ProjectBean
import com.fulan.mykotlin.mykotlinapp.network.MRetrofit
import com.fulan.mykotlin.mykotlinapp.showToast
import kotlinx.android.synthetic.main.fragment_project.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp.fragment
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
        return inflater!!.inflate(R.layout.fragment_project, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mRecyclerView.run {
            layoutManager = projectLayoutManager
            setHasFixedSize(true)
            adapter = adapter
        }
        adapter.run {
            bindToRecyclerView(mRecyclerView)
            setOnItemClickListener({ adapte, view, position ->
                val intent = Intent()
                intent.setClass(activity, DetailHomeActivity::class.java)
                intent.putExtra(Constant.URL_DETAIL, adapter.data[position].link)
                startActivity(intent)
            })
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
        fun newInstance(): ProjectFragment {
            val fragment = ProjectFragment()
            return fragment
        }
    }

}