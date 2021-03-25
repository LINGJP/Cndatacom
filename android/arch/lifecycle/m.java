package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class m extends Fragment {
  private a a;
  
  public static void a(Activity paramActivity) {
    FragmentManager fragmentManager = paramActivity.getFragmentManager();
    if (fragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
      fragmentManager.beginTransaction().add(new m(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
      fragmentManager.executePendingTransactions();
    } 
  }
  
  private void a(c.a parama) {
    Activity activity = getActivity();
    if (activity instanceof g) {
      ((g)activity).a().a(parama);
      return;
    } 
    if (activity instanceof e) {
      c c = ((e)activity).getLifecycle();
      if (c instanceof f)
        ((f)c).a(parama); 
    } 
  }
  
  private void a(a parama) {
    if (parama != null)
      parama.a(); 
  }
  
  private void b(a parama) {
    if (parama != null)
      parama.b(); 
  }
  
  private void c(a parama) {
    if (parama != null)
      parama.c(); 
  }
  
  public void onActivityCreated(Bundle paramBundle) {
    super.onActivityCreated(paramBundle);
    a(this.a);
    a(c.a.ON_CREATE);
  }
  
  public void onDestroy() {
    super.onDestroy();
    a(c.a.ON_DESTROY);
    this.a = null;
  }
  
  public void onPause() {
    super.onPause();
    a(c.a.ON_PAUSE);
  }
  
  public void onResume() {
    super.onResume();
    c(this.a);
    a(c.a.ON_RESUME);
  }
  
  public void onStart() {
    super.onStart();
    b(this.a);
    a(c.a.ON_START);
  }
  
  public void onStop() {
    super.onStop();
    a(c.a.ON_STOP);
  }
  
  static interface a {
    void a();
    
    void b();
    
    void c();
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\arch\lifecycle\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */