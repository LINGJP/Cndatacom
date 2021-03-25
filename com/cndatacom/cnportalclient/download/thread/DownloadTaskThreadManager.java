package com.cndatacom.cnportalclient.download.thread;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.cndatacom.cnportalclient.download.entity.DownloadTask;
import com.cndatacom.cnportalclient.download.interfaces.IDownloadTaskEvent;
import com.cndatacom.cnportalclient.download.interfaces.IDownloadThreadEvent;
import com.cndatacom.cnportalclient.download.utils.HttpUtil;
import com.cndatacom.cnportalclient.http.HttpClient;
import com.cndatacom.cnportalclient.utils.FileUtils;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class DownloadTaskThreadManager implements Runnable {
  private Context a;
  
  private List<DownloadTaskThread> b = new ArrayList<DownloadTaskThread>();
  
  private DownloadTask c;
  
  private Handler d;
  
  private volatile boolean e = false;
  
  private IDownloadTaskEvent f;
  
  private IDownloadThreadEvent g = new IDownloadThreadEvent(this) {
      public int getTaskThreadDownloadedSize(DownloadTask param1DownloadTask, int param1Int) {
        return (DownloadTaskThreadManager.a(this.a) != null) ? DownloadTaskThreadManager.a(this.a).getTaskThreadDownloadedSize(param1DownloadTask, param1Int) : 0;
      }
      
      public void taskThreadDownloading(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {
        if (DownloadTaskThreadManager.a(this.a) != null)
          DownloadTaskThreadManager.a(this.a).taskThreadDownloading(param1DownloadTask, param1Int1, param1Int2); 
      }
      
      public void taskThreadError(DownloadTask param1DownloadTask, int param1Int, String param1String) {
        if (DownloadTaskThreadManager.a(this.a) != null) {
          DownloadTaskThreadManager.a(this.a).taskThreadError(param1DownloadTask, param1Int, param1String);
          DownloadTaskThreadManager.a(this.a).taskError(param1DownloadTask, param1String);
        } 
        synchronized (DownloadTaskThreadManager.e(this.a)) {
          DownloadTaskThreadManager.e(this.a).notifyAll();
          return;
        } 
      }
      
      public void taskThreadFinish(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {
        // Byte code:
        //   0: aload_0
        //   1: monitorenter
        //   2: aload_0
        //   3: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   6: invokestatic a : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Lcom/cndatacom/cnportalclient/download/interfaces/IDownloadTaskEvent;
        //   9: ifnull -> 27
        //   12: aload_0
        //   13: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   16: invokestatic a : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Lcom/cndatacom/cnportalclient/download/interfaces/IDownloadTaskEvent;
        //   19: aload_1
        //   20: iload_2
        //   21: iload_3
        //   22: invokeinterface taskThreadFinish : (Lcom/cndatacom/cnportalclient/download/entity/DownloadTask;II)V
        //   27: aload_0
        //   28: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   31: invokevirtual getTaskDownloadedSize : ()I
        //   34: istore_2
        //   35: iload_2
        //   36: i2l
        //   37: aload_0
        //   38: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   41: invokestatic b : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Lcom/cndatacom/cnportalclient/download/entity/DownloadTask;
        //   44: invokevirtual getTaskFileSize : ()J
        //   47: lcmp
        //   48: iflt -> 158
        //   51: aload_0
        //   52: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   55: invokestatic c : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Z
        //   58: ifne -> 158
        //   61: aload_0
        //   62: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   65: iconst_1
        //   66: invokestatic a : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;Z)Z
        //   69: pop
        //   70: aload_0
        //   71: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   74: invokestatic d : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Ljava/lang/Thread;
        //   77: invokevirtual interrupt : ()V
        //   80: aload_1
        //   81: invokevirtual getTaskPath : ()Ljava/lang/String;
        //   84: ifnull -> 102
        //   87: aload_0
        //   88: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   91: aload_1
        //   92: invokevirtual getTaskTempPath : ()Ljava/lang/String;
        //   95: aload_1
        //   96: invokevirtual getTaskPath : ()Ljava/lang/String;
        //   99: invokestatic a : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;Ljava/lang/String;Ljava/lang/String;)V
        //   102: aload_0
        //   103: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   106: invokestatic a : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Lcom/cndatacom/cnportalclient/download/interfaces/IDownloadTaskEvent;
        //   109: ifnull -> 126
        //   112: aload_0
        //   113: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   116: invokestatic a : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Lcom/cndatacom/cnportalclient/download/interfaces/IDownloadTaskEvent;
        //   119: aload_1
        //   120: iload_2
        //   121: invokeinterface taskFinish : (Lcom/cndatacom/cnportalclient/download/entity/DownloadTask;I)V
        //   126: aload_0
        //   127: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   130: invokestatic e : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Landroid/os/Handler;
        //   133: astore_1
        //   134: aload_1
        //   135: monitorenter
        //   136: aload_0
        //   137: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;
        //   140: invokestatic e : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager;)Landroid/os/Handler;
        //   143: invokevirtual notifyAll : ()V
        //   146: aload_1
        //   147: monitorexit
        //   148: goto -> 158
        //   151: astore #4
        //   153: aload_1
        //   154: monitorexit
        //   155: aload #4
        //   157: athrow
        //   158: aload_0
        //   159: monitorexit
        //   160: return
        //   161: astore_1
        //   162: aload_0
        //   163: monitorexit
        //   164: aload_1
        //   165: athrow
        // Exception table:
        //   from	to	target	type
        //   2	27	161	finally
        //   27	102	161	finally
        //   102	126	161	finally
        //   126	136	161	finally
        //   136	148	151	finally
        //   153	155	151	finally
        //   155	158	161	finally
        //   158	160	161	finally
        //   162	164	161	finally
      }
      
      public void taskThreadPause(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {
        if (DownloadTaskThreadManager.a(this.a) != null)
          DownloadTaskThreadManager.a(this.a).taskThreadPause(param1DownloadTask, param1Int1, param1Int2); 
      }
    };
  
  private Thread h = new Thread(this) {
      public void run() {
        /* monitor enter ThisExpression{InnerObjectType{ObjectType{com/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager}.Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThreadManager$2;}} */
        while (!DownloadTaskThreadManager.c(this.a)) {
          Exception exception;
          boolean bool = false;
          try {
            int i = this.a.getTaskDownloadedSize();
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException null) {}
          } catch (InterruptedException null) {
            boolean bool1 = bool;
          } finally {}
          exception.printStackTrace();
        } 
      }
    };
  
  public DownloadTaskThreadManager(Context paramContext, Handler paramHandler, DownloadTask paramDownloadTask, IDownloadTaskEvent paramIDownloadTaskEvent) {
    this.a = paramContext;
    this.d = paramHandler;
    this.c = paramDownloadTask;
    this.f = paramIDownloadTaskEvent;
  }
  
  private int a(String paramString) {
    return paramString.startsWith("https://") ? b(paramString) : c(paramString);
  }
  
  private void a(String paramString1, String paramString2) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #6
    //   3: aconst_null
    //   4: astore #4
    //   6: new java/io/File
    //   9: dup
    //   10: aload_1
    //   11: invokespecial <init> : (Ljava/lang/String;)V
    //   14: astore #5
    //   16: aload #5
    //   18: invokevirtual exists : ()Z
    //   21: ifeq -> 80
    //   24: new java/io/FileInputStream
    //   27: dup
    //   28: aload_1
    //   29: invokespecial <init> : (Ljava/lang/String;)V
    //   32: astore_1
    //   33: new java/io/FileOutputStream
    //   36: dup
    //   37: aload_2
    //   38: invokespecial <init> : (Ljava/lang/String;)V
    //   41: astore_2
    //   42: sipush #1444
    //   45: newarray byte
    //   47: astore #4
    //   49: aload_1
    //   50: aload #4
    //   52: invokevirtual read : ([B)I
    //   55: istore_3
    //   56: iload_3
    //   57: iconst_m1
    //   58: if_icmpeq -> 72
    //   61: aload_2
    //   62: aload #4
    //   64: iconst_0
    //   65: iload_3
    //   66: invokevirtual write : ([BII)V
    //   69: goto -> 49
    //   72: aload_1
    //   73: invokevirtual close : ()V
    //   76: aload_2
    //   77: invokevirtual close : ()V
    //   80: aload #5
    //   82: ifnull -> 137
    //   85: invokestatic gc : ()V
    //   88: aload #5
    //   90: invokevirtual delete : ()Z
    //   93: pop
    //   94: return
    //   95: astore_1
    //   96: goto -> 138
    //   99: astore_2
    //   100: aload #5
    //   102: astore_1
    //   103: goto -> 118
    //   106: astore_1
    //   107: aload #4
    //   109: astore #5
    //   111: goto -> 138
    //   114: astore_2
    //   115: aload #6
    //   117: astore_1
    //   118: aload_1
    //   119: astore #4
    //   121: aload_2
    //   122: invokevirtual printStackTrace : ()V
    //   125: aload_1
    //   126: ifnull -> 137
    //   129: invokestatic gc : ()V
    //   132: aload_1
    //   133: invokevirtual delete : ()Z
    //   136: pop
    //   137: return
    //   138: aload #5
    //   140: ifnull -> 152
    //   143: invokestatic gc : ()V
    //   146: aload #5
    //   148: invokevirtual delete : ()Z
    //   151: pop
    //   152: aload_1
    //   153: athrow
    // Exception table:
    //   from	to	target	type
    //   6	16	114	java/lang/Exception
    //   6	16	106	finally
    //   16	49	99	java/lang/Exception
    //   16	49	95	finally
    //   49	56	99	java/lang/Exception
    //   49	56	95	finally
    //   61	69	99	java/lang/Exception
    //   61	69	95	finally
    //   72	80	99	java/lang/Exception
    //   72	80	95	finally
    //   121	125	106	finally
  }
  
  private boolean a(Context paramContext, boolean paramBoolean) {
    try {
      String str = this.c.getTaskUrl();
      if (TextUtils.isEmpty(str)) {
        if (this.f != null) {
          this.f.taskError(this.c, "地址不存在");
          return false;
        } 
      } else {
        int j = (int)this.c.getTaskFileSize();
        int i = j;
        if (j == 0)
          i = a(str); 
        if (i <= 0) {
          if (this.f != null) {
            this.f.taskError(this.c, "文件长度为0");
            return false;
          } 
        } else {
          str = this.c.getRootPath();
          if (!TextUtils.isEmpty(str)) {
            long l1 = FileUtils.getMemorySize(str);
            if ((i * 2) > l1) {
              if (this.f != null) {
                this.f.taskError(this.c, "内存空间不足");
                return false;
              } 
              return false;
            } 
          } 
          DownloadTask downloadTask = this.c;
          long l = i;
          downloadTask.setTaskFileSize(l);
          File file = new File(this.c.getTaskTempPath());
          if (!file.getParentFile().exists())
            file.getParentFile().mkdirs(); 
          if (!file.exists()) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.c.getTaskTempPath(), "rw");
            randomAccessFile.setLength(l);
            randomAccessFile.close();
          } 
          int k = this.c.getThreadNum();
          int m = i / k;
          j = 0;
          while (true) {
            if (j < k) {
              int n = j + 1;
              int i1 = j * m;
              if (j == k - 1) {
                j = i;
              } else {
                j = i1 + m;
              } 
              DownloadTaskThread downloadTaskThread = new DownloadTaskThread(paramContext, n, i1, j, this.c, this.g, paramBoolean);
              this.b.add(downloadTaskThread);
              downloadTaskThread.start();
              j = n;
              continue;
            } 
            return true;
          } 
        } 
        return false;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      if (this.f != null)
        this.f.taskError(this.c, "网络异常"); 
      return false;
    } 
    return false;
  }
  
  private int b(String paramString) {
    int i = 0;
    try {
      URL uRL = new URL(paramString);
      SSLContext sSLContext = SSLContext.getInstance("SSL");
      HttpClient.IgnoreSSLTrustManager ignoreSSLTrustManager = new HttpClient.IgnoreSSLTrustManager();
      SecureRandom secureRandom = new SecureRandom();
      sSLContext.init(null, new TrustManager[] { (TrustManager)ignoreSSLTrustManager }, secureRandom);
      SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
      HttpsURLConnection httpsURLConnection = (HttpsURLConnection)uRL.openConnection();
      HttpUtil.seURLConnectiontHeader(httpsURLConnection);
      httpsURLConnection.setHostnameVerifier(new HostnameVerifier(this) {
            public boolean verify(String param1String, SSLSession param1SSLSession) {
              return true;
            }
          });
      httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
      int j = httpsURLConnection.getContentLength();
      try {
        httpsURLConnection.disconnect();
        return j;
      } catch (Exception null) {
        i = j;
      } 
    } catch (Exception exception) {}
    exception.printStackTrace();
    return i;
  }
  
  private int c(String paramString) {
    boolean bool;
    try {
      HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(paramString)).openConnection();
      HttpUtil.seURLConnectiontHeader(httpURLConnection);
      bool = httpURLConnection.getContentLength();
      try {
        httpURLConnection.disconnect();
        return bool;
      } catch (Exception null) {}
    } catch (Exception exception) {
      bool = false;
    } 
    exception.printStackTrace();
    return bool;
  }
  
  public void cancelTaskThread() {
    this.e = true;
    this.h.interrupt();
    for (int i = 0; i < this.b.size(); i++)
      ((DownloadTaskThread)this.b.get(i)).cancel(); 
    if (this.f != null)
      this.f.taskCancel(this.c); 
    synchronized (this.d) {
      this.d.notifyAll();
      return;
    } 
  }
  
  public int getTaskDownloadedSize() {
    int i = 0;
    int j = 0;
    while (i < this.b.size()) {
      j += ((DownloadTaskThread)this.b.get(i)).getDownloadedSize();
      i++;
    } 
    return j;
  }
  
  public void pauseTaskThread() {
    this.e = true;
    this.h.interrupt();
    for (int i = 0; i < this.b.size(); i++)
      ((DownloadTaskThread)this.b.get(i)).pause(); 
    if (this.f != null)
      this.f.taskPause(this.c, getTaskDownloadedSize()); 
    synchronized (this.d) {
      this.d.notifyAll();
      return;
    } 
  }
  
  public void run() {
    boolean bool;
    if (this.f != null) {
      bool = this.f.getAskWifi();
    } else {
      bool = false;
    } 
    if (!a(this.a, bool))
      return; 
    this.h.start();
    try {
      synchronized (this.d) {
        this.d.wait();
        return;
      } 
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
      return;
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\thread\DownloadTaskThreadManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */