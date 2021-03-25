package com.cndatacom.cnportalclient.download.thread;

import android.content.Context;
import com.cndatacom.cnportalclient.download.entity.DownloadTask;
import com.cndatacom.cnportalclient.download.interfaces.IDownloadThreadEvent;
import com.cndatacom.cnportalclient.download.utils.HttpUtil;
import com.cndatacom.cnportalclient.http.HttpClient;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class DownloadTaskThread extends Thread {
  private final int a = 30000;
  
  private final int b = 30000;
  
  private final int c = 8192;
  
  private boolean d;
  
  private boolean e = false;
  
  private DownloadTask f;
  
  private int g = -1;
  
  private int h = 0;
  
  private int i = 0;
  
  private int j = 0;
  
  private int k = 0;
  
  private volatile boolean l = true;
  
  private RandomAccessFile m;
  
  private IDownloadThreadEvent n;
  
  private Context o;
  
  private Thread p = new Thread(this) {
      public void run() {
        // Byte code:
        //   0: aload_0
        //   1: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   4: invokestatic a : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;)Z
        //   7: ifeq -> 101
        //   10: aload_0
        //   11: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   14: invokestatic b : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;)Z
        //   17: ifne -> 101
        //   20: aload_0
        //   21: monitorenter
        //   22: aload_0
        //   23: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   26: invokevirtual getDownloadedSize : ()I
        //   29: istore_1
        //   30: aload_0
        //   31: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   34: invokestatic c : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;)Lcom/cndatacom/cnportalclient/download/interfaces/IDownloadThreadEvent;
        //   37: ifnull -> 77
        //   40: aload_0
        //   41: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   44: invokestatic b : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;)Z
        //   47: ifne -> 77
        //   50: aload_0
        //   51: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   54: invokestatic c : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;)Lcom/cndatacom/cnportalclient/download/interfaces/IDownloadThreadEvent;
        //   57: aload_0
        //   58: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   61: invokestatic d : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;)Lcom/cndatacom/cnportalclient/download/entity/DownloadTask;
        //   64: aload_0
        //   65: getfield a : Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;
        //   68: invokestatic e : (Lcom/cndatacom/cnportalclient/download/thread/DownloadTaskThread;)I
        //   71: iload_1
        //   72: invokeinterface taskThreadDownloading : (Lcom/cndatacom/cnportalclient/download/entity/DownloadTask;II)V
        //   77: ldc2_w 1000
        //   80: invokestatic sleep : (J)V
        //   83: goto -> 91
        //   86: astore_2
        //   87: aload_2
        //   88: invokevirtual printStackTrace : ()V
        //   91: aload_0
        //   92: monitorexit
        //   93: goto -> 0
        //   96: astore_2
        //   97: aload_0
        //   98: monitorexit
        //   99: aload_2
        //   100: athrow
        //   101: return
        // Exception table:
        //   from	to	target	type
        //   22	77	96	finally
        //   77	83	86	java/lang/InterruptedException
        //   77	83	96	finally
        //   87	91	96	finally
        //   91	93	96	finally
        //   97	99	96	finally
      }
    };
  
  public DownloadTaskThread(Context paramContext, int paramInt1, int paramInt2, int paramInt3, DownloadTask paramDownloadTask, IDownloadThreadEvent paramIDownloadThreadEvent, boolean paramBoolean) {
    this.o = paramContext;
    this.g = paramInt1;
    this.h = paramInt2;
    this.j = paramInt3;
    this.f = paramDownloadTask;
    this.n = paramIDownloadThreadEvent;
    this.d = paramBoolean;
    this.k = this.n.getTaskThreadDownloadedSize(this.f, this.g);
    this.i = this.h + this.k;
  }
  
  private InputStream a(String paramString, URL paramURL) {
    if (paramString.startsWith("https://")) {
      HttpsURLConnection httpsURLConnection = a(paramURL);
      httpsURLConnection.setConnectTimeout(30000);
      httpsURLConnection.setReadTimeout(30000);
      HttpUtil.seURLConnectiontHeader(httpsURLConnection);
      stringBuilder = new StringBuilder();
      stringBuilder.append("bytes=");
      stringBuilder.append(this.i);
      stringBuilder.append("-");
      stringBuilder.append(this.j);
      httpsURLConnection.setRequestProperty("Range", stringBuilder.toString());
      return httpsURLConnection.getInputStream();
    } 
    HttpURLConnection httpURLConnection = b((URL)stringBuilder);
    httpURLConnection.setConnectTimeout(30000);
    httpURLConnection.setReadTimeout(30000);
    HttpUtil.seURLConnectiontHeader(httpURLConnection);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("bytes=");
    stringBuilder.append(this.i);
    stringBuilder.append("-");
    stringBuilder.append(this.j);
    httpURLConnection.setRequestProperty("Range", stringBuilder.toString());
    return httpURLConnection.getInputStream();
  }
  
  private HttpsURLConnection a(URL paramURL) {
    SSLContext sSLContext = SSLContext.getInstance("SSL");
    HttpClient.IgnoreSSLTrustManager ignoreSSLTrustManager = new HttpClient.IgnoreSSLTrustManager();
    SecureRandom secureRandom = new SecureRandom();
    sSLContext.init(null, new TrustManager[] { (TrustManager)ignoreSSLTrustManager }, secureRandom);
    SSLSocketFactory sSLSocketFactory = sSLContext.getSocketFactory();
    HttpsURLConnection httpsURLConnection = (HttpsURLConnection)paramURL.openConnection();
    HttpUtil.seURLConnectiontHeader(httpsURLConnection);
    httpsURLConnection.setHostnameVerifier(new HostnameVerifier(this) {
          public boolean verify(String param1String, SSLSession param1SSLSession) {
            return true;
          }
        });
    httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
    return httpsURLConnection;
  }
  
  private HttpURLConnection b(URL paramURL) {
    return (HttpURLConnection)paramURL.openConnection();
  }
  
  public void cancel() {
    this.l = false;
    this.p.interrupt();
  }
  
  public int getDownloadedSize() {
    return Math.min(this.k, this.j - this.h);
  }
  
  public void pause() {
    this.l = false;
    this.p.interrupt();
    if (this.n != null) {
      int i = getDownloadedSize();
      if (i != 0)
        this.n.taskThreadPause(this.f, this.g, i); 
    } 
  }
  
  public void run() {
    this.p.start();
    if (this.j > this.i)
      try {
        URL uRL = new URL(this.f.getTaskUrl());
        InputStream inputStream = a(this.f.getTaskUrl(), uRL);
        this.m = new RandomAccessFile(this.f.getTaskTempPath(), "rw");
        this.m.seek(this.i);
        byte[] arrayOfByte = new byte[8192];
        while (true) {
          int i = inputStream.read(arrayOfByte);
          if (i > 0 && this.h + this.k < this.j && this.l) {
            if (!CdcUtils.isNetworkAvailable(this.o)) {
              if (this.n != null)
                this.n.taskThreadError(this.f, this.g, "无网络"); 
              break;
            } 
            if (this.d && !CdcUtils.isWifiConnected(this.o)) {
              if (this.n != null)
                this.n.taskThreadError(this.f, this.g, "非WIFI环境"); 
              break;
            } 
            this.m.write(arrayOfByte, 0, i);
            this.k += i;
            continue;
          } 
          break;
        } 
        inputStream.close();
        if (this.m != null)
          try {
            this.m.close();
          } catch (IOException iOException) {
            iOException.printStackTrace();
          }  
      } catch (Exception exception) {
        exception.printStackTrace();
        if (this.n != null)
          this.n.taskThreadError(this.f, this.g, "网络异常"); 
        if (this.m != null)
          this.m.close(); 
      } finally {
        Exception exception;
      }  
    if (this.l) {
      this.e = true;
      this.p.interrupt();
      this.k = this.j - this.h;
      if (this.n != null)
        this.n.taskThreadFinish(this.f, this.g, getDownloadedSize()); 
    } 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\thread\DownloadTaskThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */