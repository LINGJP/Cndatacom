package com.cndatacom.cnportalclient.plugin;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.cndatacom.cnportalclient.download.entity.DownloadTask;
import com.cndatacom.cnportalclient.download.interfaces.IDownloadTaskEvent;
import com.cndatacom.cnportalclient.download.manager.DownloadTaskManager;
import com.cndatacom.cnportalclient.utils.CdcUtils;
import java.io.File;
import java.util.Date;
import java.util.List;

public class DownloadApkManager {
  public static final int mThreadNum = 5;
  
  private Handler a;
  
  private HandlerThread b = new HandlerThread("downloadApkThread", 10);
  
  private DownloadTaskManager c;
  
  private DownloadListener d;
  
  public DownloadApkManager(Context paramContext) {
    this.b.start();
    this.a = new Handler(this.b.getLooper());
    this.c = new DownloadTaskManager(paramContext, "downloadApkManager", new IDownloadTaskEvent(this) {
          public boolean getAskWifi() {
            return true;
          }
          
          public int getTaskThreadDownloadedSize(DownloadTask param1DownloadTask, int param1Int) {
            return 0;
          }
          
          public void taskCancel(DownloadTask param1DownloadTask) {}
          
          public void taskDownloading(DownloadTask param1DownloadTask, int param1Int) {
            if (DownloadApkManager.a(this.a) != null)
              DownloadApkManager.a(this.a).taskDownloading(param1DownloadTask, param1Int); 
          }
          
          public void taskError(DownloadTask param1DownloadTask, String param1String) {
            if (DownloadApkManager.a(this.a) != null)
              DownloadApkManager.a(this.a).taskError(param1DownloadTask, param1String); 
          }
          
          public void taskFinish(DownloadTask param1DownloadTask, int param1Int) {
            if (DownloadApkManager.a(this.a) != null)
              DownloadApkManager.a(this.a).taskFinish(param1DownloadTask, param1Int); 
          }
          
          public void taskPause(DownloadTask param1DownloadTask, int param1Int) {}
          
          public void taskThreadDownloading(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {}
          
          public void taskThreadError(DownloadTask param1DownloadTask, int param1Int, String param1String) {}
          
          public void taskThreadFinish(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {}
          
          public void taskThreadPause(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {}
          
          public void taskWaiting(DownloadTask param1DownloadTask) {
            if (DownloadApkManager.a(this.a) != null)
              DownloadApkManager.a(this.a).taskWaiting(param1DownloadTask); 
          }
        });
  }
  
  public void addApkTask(String paramString) {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString.hashCode());
    stringBuilder1.append("");
    String str2 = stringBuilder1.toString();
    DownloadTask downloadTask = new DownloadTask();
    downloadTask.setTaskName(str2);
    downloadTask.setTaskExt("apk");
    downloadTask.setTaskId(str2);
    downloadTask.setTaskUrl(paramString);
    Context context = CdcUtils.getContext();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("CDCLogs");
    stringBuilder2.append(File.separator);
    stringBuilder2.append("APK");
    File file = CdcUtils.openFileDir(context, stringBuilder2.toString());
    if (file == null)
      return; 
    stringBuilder2 = new StringBuilder();
    stringBuilder2.append(file.getPath());
    stringBuilder2.append(File.separator);
    stringBuilder2.append(str2);
    stringBuilder2.append(".");
    stringBuilder2.append(downloadTask.getTaskExt());
    String str3 = stringBuilder2.toString();
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(file.getPath());
    stringBuilder3.append(File.separator);
    stringBuilder3.append(str2);
    stringBuilder3.append(".temp");
    String str1 = stringBuilder3.toString();
    downloadTask.setTaskPath(str3);
    downloadTask.setTaskTempPath(str1);
    downloadTask.setThreadNum(5);
    downloadTask.setCreateTime(new Date());
    if (!isDownloadExists(str2))
      this.c.addDownloadTask(downloadTask); 
  }
  
  public boolean isDownloadExists(String paramString) {
    List<DownloadTask> list = this.c.getDownloadTasks();
    if (list != null)
      for (int i = 0; i < list.size(); i++) {
        if (((DownloadTask)list.get(i)).getTaskId().equals(paramString))
          return true; 
      }  
    return false;
  }
  
  public void release() {
    this.c.release();
    if (this.a != null)
      this.a.removeCallbacksAndMessages(null); 
    if (this.b != null)
      this.b.quit(); 
  }
  
  public void setDownloadListener(DownloadListener paramDownloadListener) {
    this.d = paramDownloadListener;
  }
  
  public static interface DownloadListener {
    void taskDownloading(DownloadTask param1DownloadTask, int param1Int);
    
    void taskError(DownloadTask param1DownloadTask, String param1String);
    
    void taskFinish(DownloadTask param1DownloadTask, int param1Int);
    
    void taskWaiting(DownloadTask param1DownloadTask);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\plugin\DownloadApkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */