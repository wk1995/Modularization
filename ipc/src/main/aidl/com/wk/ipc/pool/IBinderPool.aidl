// IBinderPool.aidl
package com.wk.ipc.pool;

// Declare any non-default types here with import statements

interface IBinderPool {

    IBinder getInBinder(int binderCode);
}
