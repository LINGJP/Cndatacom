package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.compat.R;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ColorStateListInflaterCompat {
  private static final int DEFAULT_COLOR = -65536;
  
  @NonNull
  public static ColorStateList createFromXml(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @Nullable Resources.Theme paramTheme) {
    int i;
    AttributeSet attributeSet = Xml.asAttributeSet(paramXmlPullParser);
    while (true) {
      i = paramXmlPullParser.next();
      if (i != 2 && i != 1)
        continue; 
      break;
    } 
    if (i != 2)
      throw new XmlPullParserException("No start tag found"); 
    return createFromXmlInner(paramResources, paramXmlPullParser, attributeSet, paramTheme);
  }
  
  @NonNull
  public static ColorStateList createFromXmlInner(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) {
    StringBuilder stringBuilder;
    String str = paramXmlPullParser.getName();
    if (!str.equals("selector")) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramXmlPullParser.getPositionDescription());
      stringBuilder.append(": invalid color state list tag ");
      stringBuilder.append(str);
      throw new XmlPullParserException(stringBuilder.toString());
    } 
    return inflate((Resources)stringBuilder, paramXmlPullParser, paramAttributeSet, paramTheme);
  }
  
  private static ColorStateList inflate(@NonNull Resources paramResources, @NonNull XmlPullParser paramXmlPullParser, @NonNull AttributeSet paramAttributeSet, @Nullable Resources.Theme paramTheme) {
    int j = paramXmlPullParser.getDepth() + 1;
    int[][] arrayOfInt3 = new int[20][];
    int[] arrayOfInt4 = new int[arrayOfInt3.length];
    int i = 0;
    while (true) {
      int k = paramXmlPullParser.next();
      if (k != 1) {
        int m = paramXmlPullParser.getDepth();
        if (m >= j || k != 3) {
          if (k != 2 || m > j || !paramXmlPullParser.getName().equals("item"))
            continue; 
          TypedArray typedArray = obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.ColorStateListItem);
          int n = typedArray.getColor(R.styleable.ColorStateListItem_android_color, -65281);
          float f = 1.0F;
          if (typedArray.hasValue(R.styleable.ColorStateListItem_android_alpha)) {
            f = typedArray.getFloat(R.styleable.ColorStateListItem_android_alpha, 1.0F);
          } else if (typedArray.hasValue(R.styleable.ColorStateListItem_alpha)) {
            f = typedArray.getFloat(R.styleable.ColorStateListItem_alpha, 1.0F);
          } 
          typedArray.recycle();
          int i1 = paramAttributeSet.getAttributeCount();
          int[] arrayOfInt = new int[i1];
          k = 0;
          for (m = 0; k < i1; m = i2) {
            int i3 = paramAttributeSet.getAttributeNameResource(k);
            int i2 = m;
            if (i3 != 16843173) {
              i2 = m;
              if (i3 != 16843551) {
                i2 = m;
                if (i3 != R.attr.alpha) {
                  if (paramAttributeSet.getAttributeBooleanValue(k, false)) {
                    i2 = i3;
                  } else {
                    i2 = -i3;
                  } 
                  arrayOfInt[m] = i2;
                  i2 = m + 1;
                } 
              } 
            } 
            k++;
          } 
          arrayOfInt = StateSet.trimStateSet(arrayOfInt, m);
          k = modulateColorAlpha(n, f);
          if (i)
            m = arrayOfInt.length; 
          arrayOfInt4 = GrowingArrayUtils.append(arrayOfInt4, i, k);
          arrayOfInt3 = GrowingArrayUtils.<int[]>append(arrayOfInt3, i, arrayOfInt);
          i++;
          continue;
        } 
      } 
      break;
    } 
    int[] arrayOfInt1 = new int[i];
    int[][] arrayOfInt2 = new int[i][];
    System.arraycopy(arrayOfInt4, 0, arrayOfInt1, 0, i);
    System.arraycopy(arrayOfInt3, 0, arrayOfInt2, 0, i);
    return new ColorStateList(arrayOfInt2, arrayOfInt1);
  }
  
  @ColorInt
  private static int modulateColorAlpha(@ColorInt int paramInt, @FloatRange(from = 0.0D, to = 1.0D) float paramFloat) {
    return paramInt & 0xFFFFFF | Math.round(Color.alpha(paramInt) * paramFloat) << 24;
  }
  
  private static TypedArray obtainAttributes(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfint) {
    return (paramTheme == null) ? paramResources.obtainAttributes(paramAttributeSet, paramArrayOfint) : paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfint, 0, 0);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\content\res\ColorStateListInflaterCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */