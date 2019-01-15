// IMyAidlInterface.aidl
package com.wk.ipc.aidl;

// Declare any non-default types here with import statements
import com.wk.ipc.aidl.Wk;
import com.wk.ipc.aidl.IAddWkListener;
interface IWkManager {

    //注意这里的in
    void addWk(in Wk wk);

    List<Wk> getWkList();

    void registerListener(IAddWkListener mIAddWkListener);
    void unregisterListener(IAddWkListener mIAddWkListener);
}
