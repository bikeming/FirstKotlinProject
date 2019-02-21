package com.fulan.mykotlin.mykotlinapp.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fulan.mykotlin.mykotlinapp.R
import com.fulan.mykotlin.mykotlinapp.bean.ProjectBean

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp.adapter
 * @Description:
 * @author: fjm
 * @date: 2018/9/11 18:55
 * @Version:1.0
 */
class ProjectListAdapter : BaseQuickAdapter<ProjectBean.Data.Data, BaseViewHolder>(R.layout.item_projectlist) {
    override fun convert(helper: BaseViewHolder, item: ProjectBean.Data.Data) {
        helper.setText(R.id.tv_author, item.title)
                .setText(R.id.tv_title, item.desc)
                .setText(R.id.tv_niceDate, item.niceDate)
        val imageView = helper.getView<ImageView>(R.id.iv_img)
        Glide.with(mContext).load(item.envelopePic).into(imageView)

    }

}