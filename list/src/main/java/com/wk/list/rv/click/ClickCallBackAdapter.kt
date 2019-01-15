package com.wk.list.rv.click

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wk.list.R
import java.text.FieldPosition

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/10/26
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class ClickCallBackAdapter(val mEtTextCallBack: EtTextCallBack?)
    : BaseQuickAdapter<ClickCallBackBean, BaseViewHolder>(R.layout.list_item_brvah_test) {

    interface EtTextCallBack {
        fun afterTextChanged(s: Editable?, position: Int)
    }


    override fun convert(helper: BaseViewHolder?, item: ClickCallBackBean?) {
        //如果这里用链式，则addTextChangedListener会触发多次
        helper?.setText(R.id.tvUserName, item?.note)
        helper?.addOnLongClickListener(R.id.tvUserName)
        helper?.setVisible(R.id.tvUserAge, item?.isShow ?: true)
        val et = helper?.getView<EditText>(R.id.tvUserAge)
        et?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mEtTextCallBack?.afterTextChanged(s, helper.layoutPosition)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}