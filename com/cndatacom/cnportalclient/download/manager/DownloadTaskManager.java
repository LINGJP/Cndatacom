package com.cndatacom.cnportalclient.download.manager;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.cndatacom.cnportalclient.download.entity.DownloadTask;
import com.cndatacom.cnportalclient.download.interfaces.IDownloadTaskEvent;
import com.cndatacom.cnportalclient.download.thread.DownloadTaskThreadManager;
import java.util.ArrayList;
import java.util.List;

public class DownloadTaskManager {
  private Handler a;
  
  private HandlerThread b;
  
  private Context c;
  
  private List<DownloadTask> d = new ArrayList<DownloadTask>();
  
  private IDownloadTaskEvent e;
  
  private IDownloadTaskEvent f = new IDownloadTaskEvent(this) {
      public boolean getAskWifi() {
        return (DownloadTaskManager.a(this.a) != null) ? DownloadTaskManager.a(this.a).getAskWifi() : false;
      }
      
      public int getTaskThreadDownloadedSize(DownloadTask param1DownloadTask, int param1Int) {
        return (DownloadTaskManager.a(this.a) != null) ? DownloadTaskManager.a(this.a).getTaskThreadDownloadedSize(param1DownloadTask, param1Int) : 0;
      }
      
      public void taskCancel(DownloadTask param1DownloadTask) {
        DownloadTaskManager.a(this.a, param1DownloadTask);
        if (DownloadTaskManager.a(this.a) != null) {
          param1DownloadTask.setStatus(4);
          DownloadTaskManager.a(this.a).taskCancel(param1DownloadTask);
        } 
      }
      
      public void taskDownloading(DownloadTask param1DownloadTask, int param1Int) {
        if (DownloadTaskManager.a(this.a) != null) {
          if (param1DownloadTask.getTaskFileSize() <= param1Int)
            return; 
          param1DownloadTask.setStatus(2);
          DownloadTaskManager.a(this.a).taskDownloading(param1DownloadTask, param1Int);
        } 
      }
      
      public void taskError(DownloadTask param1DownloadTask, String param1String) {
        DownloadTaskManager.a(this.a, param1DownloadTask);
        if (DownloadTaskManager.a(this.a) != null) {
          param1DownloadTask.setStatus(5);
          DownloadTaskManager.a(this.a).taskError(param1DownloadTask, param1String);
        } 
      }
      
      public void taskFinish(DownloadTask param1DownloadTask, int param1Int) {
        DownloadTaskManager.a(this.a, param1DownloadTask);
        if (DownloadTaskManager.a(this.a) != null) {
          if (param1DownloadTask.getTaskFileSize() > param1Int)
            return; 
          param1DownloadTask.setStatus(6);
          DownloadTaskManager.a(this.a).taskFinish(param1DownloadTask, param1Int);
        } 
      }
      
      public void taskPause(DownloadTask param1DownloadTask, int param1Int) {
        DownloadTaskManager.a(this.a, param1DownloadTask);
        if (DownloadTaskManager.a(this.a) != null) {
          if (param1DownloadTask.getTaskFileSize() <= param1Int)
            return; 
          param1DownloadTask.setStatus(3);
          DownloadTaskManager.a(this.a).taskPause(param1DownloadTask, param1Int);
        } 
      }
      
      public void taskThreadDownloading(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {
        if (DownloadTaskManager.a(this.a) != null)
          DownloadTaskManager.a(this.a).taskThreadDownloading(param1DownloadTask, param1Int1, param1Int2); 
      }
      
      public void taskThreadError(DownloadTask param1DownloadTask, int param1Int, String param1String) {
        if (DownloadTaskManager.a(this.a) != null)
          DownloadTaskManager.a(this.a).taskThreadError(param1DownloadTask, param1Int, param1String); 
      }
      
      public void taskThreadFinish(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {
        if (DownloadTaskManager.a(this.a) != null)
          DownloadTaskManager.a(this.a).taskThreadFinish(param1DownloadTask, param1Int1, param1Int2); 
      }
      
      public void taskThreadPause(DownloadTask param1DownloadTask, int param1Int1, int param1Int2) {
        if (DownloadTaskManager.a(this.a) != null)
          DownloadTaskManager.a(this.a).taskThreadPause(param1DownloadTask, param1Int1, param1Int2); 
      }
      
      public void taskWaiting(DownloadTask param1DownloadTask) {
        if (DownloadTaskManager.a(this.a) != null) {
          param1DownloadTask.setStatus(1);
          DownloadTaskManager.a(this.a).taskWaiting(param1DownloadTask);
        } 
      }
    };
  
  public DownloadTaskManager(Context paramContext, String paramString, IDownloadTaskEvent paramIDownloadTaskEvent) {
    this.c = paramContext;
    this.e = paramIDownloadTaskEvent;
    this.b = new HandlerThread(paramString, 10);
    this.b.start();
    this.a = new Handler(this.b.getLooper());
  }
  
  private void a(DownloadTask paramDownloadTask) {
    for (int i = 0; i < this.d.size(); i++) {
      DownloadTask downloadTask = this.d.get(i);
      if (downloadTask.getTaskId().equals(paramDownloadTask.getTaskId())) {
        if (this.d.size() > 0) {
          this.d.remove(i);
          this.a.removeCallbacks((Runnable)downloadTask.getDownloadTaskThreadManager());
          return;
        } 
        break;
      } 
    } 
  }
  
  public void addDownloadTask(DownloadTask paramDownloadTask) {
    paramDownloadTask.setDownloadTaskThreadManager(new DownloadTaskThreadManager(this.c, this.a, paramDownloadTask, this.f));
    if (this.d.size() == 0 || this.d.size() > 0) {
      paramDownloadTask.setStatus(1);
      if (this.e != null)
        this.e.taskWaiting(paramDownloadTask); 
    } 
    this.d.add(paramDownloadTask);
    this.a.post((Runnable)paramDownloadTask.getDownloadTaskThreadManager());
  }
  
  public void cancelDownloadTask(String paramString) {
    for (int i = 0; i < this.d.size(); i++) {
      DownloadTask downloadTask = this.d.get(i);
      if (downloadTask.getTaskId().equals(paramString)) {
        if (i == 0) {
          downloadTask.getDownloadTaskThreadManager().cancelTaskThread();
          return;
        } 
        if (this.e != null) {
          this.e.taskCancel(downloadTask);
          return;
        } 
        break;
      } 
    } 
  }
  
  public List<DownloadTask> getDownloadTasks() {
    return this.d;
  }
  
  public void pauseDownloadTask(String paramString) {
    for (int i = 0; i < this.d.size(); i++) {
      DownloadTask downloadTask = this.d.get(i);
      if (downloadTask.getTaskId().equals(paramString)) {
        if (i == 0) {
          downloadTask.getDownloadTaskThreadManager().pauseTaskThread();
        } else if (this.e != null) {
          this.e.taskPause(downloadTask, 0);
        } 
        if (this.d.size() > 0) {
          this.d.remove(i);
          return;
        } 
        break;
      } 
    } 
  }
  
  public void release() {
    if (this.a != null)
      this.a.removeCallbacksAndMessages(null); 
    if (this.b != null)
      this.b.quit(); 
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclient\download\manager\DownloadTaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */