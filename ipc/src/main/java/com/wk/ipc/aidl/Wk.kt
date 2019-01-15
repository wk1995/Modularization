package com.wk.ipc.aidl

import android.os.Parcel
import android.os.Parcelable

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/12/30
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class Wk() : Parcelable {
    private var name: String? = null


    constructor(name: String?) : this() {
        this.name = name
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
    }

    override fun describeContents() = 0

    fun readFromParcel(mParcel: Parcel) {
        name = mParcel.readString()
    }

    override fun toString(): String {
        return "wk name is $name"
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Wk> {
            override fun createFromParcel(p0: Parcel?) = Wk(p0?.readString())

            override fun newArray(p0: Int): Array<Wk?> = arrayOfNulls(p0)
        }
    }
}
