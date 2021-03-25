package android.support.v4.app;

import android.arch.lifecycle.e;
import android.arch.lifecycle.j;
import android.arch.lifecycle.k;
import android.arch.lifecycle.n;
import android.arch.lifecycle.o;
import android.arch.lifecycle.p;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
  static boolean DEBUG = false;
  
  static final String TAG = "LoaderManager";
  
  @NonNull
  private final e mLifecycleOwner;
  
  @NonNull
  private final LoaderViewModel mLoaderViewModel;
  
  LoaderManagerImpl(@NonNull e parame, @NonNull p paramp) {
    this.mLifecycleOwner = parame;
    this.mLoaderViewModel = LoaderViewModel.getInstance(paramp);
  }
  
  @MainThread
  @NonNull
  private <D> Loader<D> createAndInstallLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks, @Nullable Loader<D> paramLoader) {
    try {
      StringBuilder stringBuilder;
      this.mLoaderViewModel.startCreatingLoader();
      Loader<D> loader = paramLoaderCallbacks.onCreateLoader(paramInt, paramBundle);
      if (loader == null)
        throw new IllegalArgumentException("Object returned from onCreateLoader must not be null"); 
      if (loader.getClass().isMemberClass() && !Modifier.isStatic(loader.getClass().getModifiers())) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Object returned from onCreateLoader must not be a non-static inner member class: ");
        stringBuilder.append(loader);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      LoaderInfo<D> loaderInfo = new LoaderInfo<D>(paramInt, (Bundle)stringBuilder, loader, paramLoader);
      if (DEBUG) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("  Created new loader ");
        stringBuilder1.append(loaderInfo);
        Log.v("LoaderManager", stringBuilder1.toString());
      } 
      this.mLoaderViewModel.putLoader(paramInt, loaderInfo);
      return loaderInfo.setCallback(this.mLifecycleOwner, paramLoaderCallbacks);
    } finally {
      this.mLoaderViewModel.finishCreatingLoader();
    } 
  }
  
  @MainThread
  public void destroyLoader(int paramInt) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("destroyLoader must be called on the main thread"); 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("destroyLoader in ");
      stringBuilder.append(this);
      stringBuilder.append(" of ");
      stringBuilder.append(paramInt);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    if (loaderInfo != null) {
      loaderInfo.destroy(true);
      this.mLoaderViewModel.removeLoader(paramInt);
    } 
  }
  
  @Deprecated
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    this.mLoaderViewModel.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  @Nullable
  public <D> Loader<D> getLoader(int paramInt) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    return (Loader)((loaderInfo != null) ? loaderInfo.getLoader() : null);
  }
  
  public boolean hasRunningLoaders() {
    return this.mLoaderViewModel.hasRunningLoaders();
  }
  
  @MainThread
  @NonNull
  public <D> Loader<D> initLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("initLoader must be called on the main thread"); 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("initLoader in ");
      stringBuilder.append(this);
      stringBuilder.append(": args=");
      stringBuilder.append(paramBundle);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    if (loaderInfo == null)
      return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, null); 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("  Re-using existing loader ");
      stringBuilder.append(loaderInfo);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    return (Loader)loaderInfo.setCallback(this.mLifecycleOwner, paramLoaderCallbacks);
  }
  
  public void markForRedelivery() {
    this.mLoaderViewModel.markForRedelivery();
  }
  
  @MainThread
  @NonNull
  public <D> Loader<D> restartLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("restartLoader must be called on the main thread"); 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("restartLoader in ");
      stringBuilder.append(this);
      stringBuilder.append(": args=");
      stringBuilder.append(paramBundle);
      Log.v("LoaderManager", stringBuilder.toString());
    } 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    Loader<?> loader = null;
    if (loaderInfo != null)
      loader = loaderInfo.destroy(false); 
    return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, (Loader)loader);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("LoaderManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(this.mLifecycleOwner, stringBuilder);
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  public static class LoaderInfo<D> extends j<D> implements Loader.OnLoadCompleteListener<D> {
    @Nullable
    private final Bundle mArgs;
    
    private final int mId;
    
    private e mLifecycleOwner;
    
    @NonNull
    private final Loader<D> mLoader;
    
    private LoaderManagerImpl.LoaderObserver<D> mObserver;
    
    private Loader<D> mPriorLoader;
    
    LoaderInfo(int param1Int, @Nullable Bundle param1Bundle, @NonNull Loader<D> param1Loader1, @Nullable Loader<D> param1Loader2) {
      this.mId = param1Int;
      this.mArgs = param1Bundle;
      this.mLoader = param1Loader1;
      this.mPriorLoader = param1Loader2;
      this.mLoader.registerListener(param1Int, this);
    }
    
    @MainThread
    Loader<D> destroy(boolean param1Boolean) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Destroying: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mLoader.cancelLoad();
      this.mLoader.abandon();
      LoaderManagerImpl.LoaderObserver<D> loaderObserver = this.mObserver;
      if (loaderObserver != null) {
        removeObserver(loaderObserver);
        if (param1Boolean)
          loaderObserver.reset(); 
      } 
      this.mLoader.unregisterListener(this);
      if ((loaderObserver != null && !loaderObserver.hasDeliveredData()) || param1Boolean) {
        this.mLoader.reset();
        return this.mPriorLoader;
      } 
      return this.mLoader;
    }
    
    public void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mId=");
      param1PrintWriter.print(this.mId);
      param1PrintWriter.print(" mArgs=");
      param1PrintWriter.println(this.mArgs);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mLoader=");
      param1PrintWriter.println(this.mLoader);
      Loader<D> loader = this.mLoader;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("  ");
      loader.dump(stringBuilder.toString(), param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      if (this.mObserver != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mCallbacks=");
        param1PrintWriter.println(this.mObserver);
        LoaderManagerImpl.LoaderObserver<D> loaderObserver = this.mObserver;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(param1String);
        stringBuilder1.append("  ");
        loaderObserver.dump(stringBuilder1.toString(), param1PrintWriter);
      } 
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mData=");
      param1PrintWriter.println(getLoader().dataToString(getValue()));
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mStarted=");
      param1PrintWriter.println(hasActiveObservers());
    }
    
    @NonNull
    Loader<D> getLoader() {
      return this.mLoader;
    }
    
    boolean isCallbackWaitingForData() {
      boolean bool = hasActiveObservers();
      boolean bool1 = false;
      if (!bool)
        return false; 
      bool = bool1;
      if (this.mObserver != null) {
        bool = bool1;
        if (!this.mObserver.hasDeliveredData())
          bool = true; 
      } 
      return bool;
    }
    
    void markForRedelivery() {
      e e1 = this.mLifecycleOwner;
      LoaderManagerImpl.LoaderObserver<D> loaderObserver = this.mObserver;
      if (e1 != null && loaderObserver != null) {
        super.removeObserver(loaderObserver);
        observe(e1, loaderObserver);
      } 
    }
    
    protected void onActive() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Starting: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mLoader.startLoading();
    }
    
    protected void onInactive() {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  Stopping: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mLoader.stopLoading();
    }
    
    public void onLoadComplete(@NonNull Loader<D> param1Loader, @Nullable D param1D) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onLoadComplete: ");
        stringBuilder.append(this);
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      if (Looper.myLooper() == Looper.getMainLooper()) {
        setValue(param1D);
        return;
      } 
      if (LoaderManagerImpl.DEBUG)
        Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread"); 
      postValue(param1D);
    }
    
    public void removeObserver(@NonNull k<? super D> param1k) {
      super.removeObserver(param1k);
      this.mLifecycleOwner = null;
      this.mObserver = null;
    }
    
    @MainThread
    @NonNull
    Loader<D> setCallback(@NonNull e param1e, @NonNull LoaderManager.LoaderCallbacks<D> param1LoaderCallbacks) {
      LoaderManagerImpl.LoaderObserver<D> loaderObserver = new LoaderManagerImpl.LoaderObserver<D>(this.mLoader, param1LoaderCallbacks);
      observe(param1e, loaderObserver);
      if (this.mObserver != null)
        removeObserver(this.mObserver); 
      this.mLifecycleOwner = param1e;
      this.mObserver = loaderObserver;
      return this.mLoader;
    }
    
    public void setValue(D param1D) {
      super.setValue(param1D);
      if (this.mPriorLoader != null) {
        this.mPriorLoader.reset();
        this.mPriorLoader = null;
      } 
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(64);
      stringBuilder.append("LoaderInfo{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" #");
      stringBuilder.append(this.mId);
      stringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(this.mLoader, stringBuilder);
      stringBuilder.append("}}");
      return stringBuilder.toString();
    }
  }
  
  static class LoaderObserver<D> implements k<D> {
    @NonNull
    private final LoaderManager.LoaderCallbacks<D> mCallback;
    
    private boolean mDeliveredData = false;
    
    @NonNull
    private final Loader<D> mLoader;
    
    LoaderObserver(@NonNull Loader<D> param1Loader, @NonNull LoaderManager.LoaderCallbacks<D> param1LoaderCallbacks) {
      this.mLoader = param1Loader;
      this.mCallback = param1LoaderCallbacks;
    }
    
    public void dump(String param1String, PrintWriter param1PrintWriter) {
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mDeliveredData=");
      param1PrintWriter.println(this.mDeliveredData);
    }
    
    boolean hasDeliveredData() {
      return this.mDeliveredData;
    }
    
    public void onChanged(@Nullable D param1D) {
      if (LoaderManagerImpl.DEBUG) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  onLoadFinished in ");
        stringBuilder.append(this.mLoader);
        stringBuilder.append(": ");
        stringBuilder.append(this.mLoader.dataToString(param1D));
        Log.v("LoaderManager", stringBuilder.toString());
      } 
      this.mCallback.onLoadFinished(this.mLoader, param1D);
      this.mDeliveredData = true;
    }
    
    @MainThread
    void reset() {
      if (this.mDeliveredData) {
        if (LoaderManagerImpl.DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("  Resetting: ");
          stringBuilder.append(this.mLoader);
          Log.v("LoaderManager", stringBuilder.toString());
        } 
        this.mCallback.onLoaderReset(this.mLoader);
      } 
    }
    
    public String toString() {
      return this.mCallback.toString();
    }
  }
  
  static class LoaderViewModel extends n {
    private static final o.a FACTORY = new o.a() {
        @NonNull
        public <T extends n> T create(@NonNull Class<T> param2Class) {
          return (T)new LoaderManagerImpl.LoaderViewModel();
        }
      };
    
    private boolean mCreatingLoader = false;
    
    private SparseArrayCompat<LoaderManagerImpl.LoaderInfo> mLoaders = new SparseArrayCompat();
    
    @NonNull
    static LoaderViewModel getInstance(p param1p) {
      return (LoaderViewModel)(new o(param1p, FACTORY)).a(LoaderViewModel.class);
    }
    
    public void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      if (this.mLoaders.size() > 0) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.println("Loaders:");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(param1String);
        stringBuilder.append("    ");
        String str = stringBuilder.toString();
        int i;
        for (i = 0; i < this.mLoaders.size(); i++) {
          LoaderManagerImpl.LoaderInfo loaderInfo = (LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i);
          param1PrintWriter.print(param1String);
          param1PrintWriter.print("  #");
          param1PrintWriter.print(this.mLoaders.keyAt(i));
          param1PrintWriter.print(": ");
          param1PrintWriter.println(loaderInfo.toString());
          loaderInfo.dump(str, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
        } 
      } 
    }
    
    void finishCreatingLoader() {
      this.mCreatingLoader = false;
    }
    
    <D> LoaderManagerImpl.LoaderInfo<D> getLoader(int param1Int) {
      return (LoaderManagerImpl.LoaderInfo<D>)this.mLoaders.get(param1Int);
    }
    
    boolean hasRunningLoaders() {
      int j = this.mLoaders.size();
      for (int i = 0; i < j; i++) {
        if (((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).isCallbackWaitingForData())
          return true; 
      } 
      return false;
    }
    
    boolean isCreatingLoader() {
      return this.mCreatingLoader;
    }
    
    void markForRedelivery() {
      int j = this.mLoaders.size();
      for (int i = 0; i < j; i++)
        ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).markForRedelivery(); 
    }
    
    protected void onCleared() {
      super.onCleared();
      int j = this.mLoaders.size();
      for (int i = 0; i < j; i++)
        ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).destroy(true); 
      this.mLoaders.clear();
    }
    
    void putLoader(int param1Int, @NonNull LoaderManagerImpl.LoaderInfo param1LoaderInfo) {
      this.mLoaders.put(param1Int, param1LoaderInfo);
    }
    
    void removeLoader(int param1Int) {
      this.mLoaders.remove(param1Int);
    }
    
    void startCreatingLoader() {
      this.mCreatingLoader = true;
    }
  }
  
  static final class null implements o.a {
    @NonNull
    public <T extends n> T create(@NonNull Class<T> param1Class) {
      return (T)new LoaderManagerImpl.LoaderViewModel();
    }
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\app\LoaderManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */