package com.wk.list.ui

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath


@Route(path = RouterPath.ListActivityMainActivity)
class ListActivityMainListActivity : BaseMainListActivity(){
    private val list by lazy{
        val list =ArrayList<String>()
        list.add("ListView")
        list.add("RecyclerView")
        list
    }

    override fun getRecyclerItemList()=list

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText=bundle?.getString(BundleKey.ItemText)
        when(itemText){
            list[0]->{
                ARouter.getInstance().build(RouterPath.ListViewActivity).navigation()
            }
            list[1]->{
                val intentw = Intent(Intent.ACTION_MAIN)
                intentw.addCategory(Intent.CATEGORY_HOME)
                intentw.setClassName("android",
                        "com.android.internal.app.ResolverActivity")
                startActivity(intentw)
               /* val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                val resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
                val currentHomePackage = resolveInfo.activityInfo.packageName
                Toast.makeText(this
                        ,currentHomePackage+"  this thePackageName is ${(this.application as ListApplication).thePackageName}"
                        ,Toast.LENGTH_SHORT)
                        .show()*/
               /*  val PATH = (Environment.getExternalStorageDirectory()
                        .absolutePath
                        + File.separator
                        + File.separator)
                val path=PATH+"wk"
                val f=File(path)
                if(f.exists()){
                   Toast.makeText(this,
                           "存在 absolutePath  ${f.absolutePath}  path ${f.path}",Toast.LENGTH_SHORT).show()
                }else{
                    f.mkdirs()
                    f.createNewFile()
                    Toast.makeText(this,
                            "新建： absolutePath  ${f.absolutePath}  path ${f.path}",Toast.LENGTH_SHORT).show()
                }*/
            }
        }
    }
}
