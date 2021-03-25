package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.c;
import android.arch.lifecycle.e;
import android.arch.lifecycle.f;
import android.arch.lifecycle.m;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.KeyEventDispatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SupportActivity extends Activity implements e, KeyEventDispatcher.Component {
  private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap();
  
  private f mLifecycleRegistry = new f(this);
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
    View view = getWindow().getDecorView();
    return (view != null && KeyEventDispatcher.dispatchBeforeHierarchy(view, paramKeyEvent)) ? true : KeyEventDispatcher.dispatchKeyEvent(this, view, (Window.Callback)this, paramKeyEvent);
  }
  
  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent) {
    View view = getWindow().getDecorView();
    return (view != null && KeyEventDispatcher.dispatchBeforeHierarchy(view, paramKeyEvent)) ? true : super.dispatchKeyShortcutEvent(paramKeyEvent);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public <T extends ExtraData> T getExtraData(Class<T> paramClass) {
    return (T)this.mExtraDataMap.get(paramClass);
  }
  
  public c getLifecycle() {
    return (c)this.mLifecycleRegistry;
  }
  
  protected void onCreate(@Nullable Bundle paramBundle) {
    super.onCreate(paramBundle);
    m.a(this);
  }
  
  @CallSuper
  protected void onSaveInstanceState(Bundle paramBundle) {
    this.mLifecycleRegistry.a(c.b.c);
    super.onSaveInstanceState(paramBundle);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void putExtraData(ExtraData paramExtraData) {
    this.mExtraDataMap.put(paramExtraData.getClass(), paramExtraData);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public boolean superDispatchKeyEvent(KeyEvent paramKeyEvent) {
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static class ExtraData {}
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\app\SupportActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */