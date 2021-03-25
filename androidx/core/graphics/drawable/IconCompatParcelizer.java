package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.a;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class IconCompatParcelizer {
  public static IconCompat read(a parama) {
    IconCompat iconCompat = new IconCompat();
    iconCompat.mType = parama.b(iconCompat.mType, 1);
    iconCompat.mData = parama.b(iconCompat.mData, 2);
    iconCompat.mParcelable = parama.b(iconCompat.mParcelable, 3);
    iconCompat.mInt1 = parama.b(iconCompat.mInt1, 4);
    iconCompat.mInt2 = parama.b(iconCompat.mInt2, 5);
    iconCompat.mTintList = (ColorStateList)parama.b((Parcelable)iconCompat.mTintList, 6);
    iconCompat.mTintModeStr = parama.b(iconCompat.mTintModeStr, 7);
    iconCompat.onPostParceling();
    return iconCompat;
  }
  
  public static void write(IconCompat paramIconCompat, a parama) {
    parama.a(true, true);
    paramIconCompat.onPreParceling(parama.a());
    parama.a(paramIconCompat.mType, 1);
    parama.a(paramIconCompat.mData, 2);
    parama.a(paramIconCompat.mParcelable, 3);
    parama.a(paramIconCompat.mInt1, 4);
    parama.a(paramIconCompat.mInt2, 5);
    parama.a((Parcelable)paramIconCompat.mTintList, 6);
    parama.a(paramIconCompat.mTintModeStr, 7);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\androidx\core\graphics\drawable\IconCompatParcelizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */