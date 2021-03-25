package org.apache.cordova;

import android.util.Pair;
import android.util.SparseArray;

public class CallbackMap {
  private SparseArray<Pair<CordovaPlugin, Integer>> callbacks = new SparseArray();
  
  private int currentCallbackId = 0;
  
  public Pair<CordovaPlugin, Integer> getAndRemoveCallback(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield callbacks : Landroid/util/SparseArray;
    //   6: iload_1
    //   7: invokevirtual get : (I)Ljava/lang/Object;
    //   10: checkcast android/util/Pair
    //   13: astore_2
    //   14: aload_0
    //   15: getfield callbacks : Landroid/util/SparseArray;
    //   18: iload_1
    //   19: invokevirtual remove : (I)V
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_2
    //   25: areturn
    //   26: astore_2
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_2
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	26	finally
  }
  
  public int registerCallback(CordovaPlugin paramCordovaPlugin, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield currentCallbackId : I
    //   6: istore_3
    //   7: aload_0
    //   8: iload_3
    //   9: iconst_1
    //   10: iadd
    //   11: putfield currentCallbackId : I
    //   14: aload_0
    //   15: getfield callbacks : Landroid/util/SparseArray;
    //   18: iload_3
    //   19: new android/util/Pair
    //   22: dup
    //   23: aload_1
    //   24: iload_2
    //   25: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   28: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   31: invokevirtual put : (ILjava/lang/Object;)V
    //   34: aload_0
    //   35: monitorexit
    //   36: iload_3
    //   37: ireturn
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   2	34	38	finally
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\org\apache\cordova\CallbackMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */