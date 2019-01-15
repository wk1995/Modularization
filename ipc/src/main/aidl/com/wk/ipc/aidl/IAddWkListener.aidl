// IAddWkListener.aidl
package com.wk.ipc.aidl;

// Declare any non-default types here with import statements
import com.wk.ipc.aidl.Wk;
interface IAddWkListener {
    void listener(in Wk wk);
}
