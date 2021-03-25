package android.support.v4.graphics.drawable;

import android.support.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompatParcelizer;
import androidx.versionedparcelable.a;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class IconCompatParcelizer extends IconCompatParcelizer {
  public static IconCompat read(a parama) {
    return IconCompatParcelizer.read(parama);
  }
  
  public static void write(IconCompat paramIconCompat, a parama) {
    IconCompatParcelizer.write(paramIconCompat, parama);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\graphics\drawable\IconCompatParcelizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */