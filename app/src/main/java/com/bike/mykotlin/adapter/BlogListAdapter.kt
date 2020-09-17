package com.bike.mykotlin.adapter

import com.bike.mykotlin.R
import com.bike.mykotlin.bean.HomeBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 *
 * @ClassName: com.bike.mykotlin.adapter
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