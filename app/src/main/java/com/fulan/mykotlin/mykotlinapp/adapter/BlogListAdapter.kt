package com.fulan.mykotlin.mykotlinapp.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fulan.mykotlin.mykotlinapp.R
import com.fulan.mykotlin.mykotlinapp.bean.HomeBean

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp.adapter
 * @Description:
 * @author: fjm
 * @date: 2018/8/24 10:05
 * @Version:1.0
 */
class BlogListAdapter : BaseQuickAdapter<HomeBean.Data.Data, BaseViewHolder>(R.layout.item_homelist) {
    override fun convert(helper: BaseViewHolder, item: HomeBean.Data.Data) {
        helper.setText(R.id.tv_author, item.author)
                .setText(R.id.tv_title, item.title)
                .setText(R.id.tv_niceDate, item.niceDate)
    }

}