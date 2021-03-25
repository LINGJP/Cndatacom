package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ComplexColorCompat {
  private static final String LOG_TAG = "ComplexColorCompat";
  
  private int mColor;
  
  private final ColorStateList mColorStateList;
  
  private final Shader mShader;
  
  private ComplexColorCompat(Shader paramShader, ColorStateList paramColorStateList, @ColorInt int paramInt) {
    this.mShader = paramShader;
    this.mColorStateList = paramColorStateList;
    this.mColor = paramInt;
  }
  
  @NonNull
  private static ComplexColorCompat createFromXml(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: invokevirtual getXml : (I)Landroid/content/res/XmlResourceParser;
    //   5: astore #4
    //   7: aload #4
    //   9: invokestatic asAttributeSet : (Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   12: astore #6
    //   14: aload #4
    //   16: invokeinterface next : ()I
    //   21: istore_3
    //   22: iconst_1
    //   23: istore_1
    //   24: iload_3
    //   25: iconst_2
    //   26: if_icmpeq -> 37
    //   29: iload_3
    //   30: iconst_1
    //   31: if_icmpeq -> 37
    //   34: goto -> 14
    //   37: iload_3
    //   38: iconst_2
    //   39: if_icmpeq -> 52
    //   42: new org/xmlpull/v1/XmlPullParserException
    //   45: dup
    //   46: ldc 'No start tag found'
    //   48: invokespecial <init> : (Ljava/lang/String;)V
    //   51: athrow
    //   52: aload #4
    //   54: invokeinterface getName : ()Ljava/lang/String;
    //   59: astore #5
    //   61: aload #5
    //   63: invokevirtual hashCode : ()I
    //   66: istore_3
    //   67: iload_3
    //   68: ldc 89650992
    //   70: if_icmpeq -> 97
    //   73: iload_3
    //   74: ldc 1191572447
    //   76: if_icmpeq -> 82
    //   79: goto -> 110
    //   82: aload #5
    //   84: ldc 'selector'
    //   86: invokevirtual equals : (Ljava/lang/Object;)Z
    //   89: ifeq -> 110
    //   92: iconst_0
    //   93: istore_1
    //   94: goto -> 112
    //   97: aload #5
    //   99: ldc 'gradient'
    //   101: invokevirtual equals : (Ljava/lang/Object;)Z
    //   104: ifeq -> 110
    //   107: goto -> 112
    //   110: iconst_m1
    //   111: istore_1
    //   112: iload_1
    //   113: tableswitch default -> 136, 0 -> 195, 1 -> 182
    //   136: new java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial <init> : ()V
    //   143: astore_0
    //   144: aload_0
    //   145: aload #4
    //   147: invokeinterface getPositionDescription : ()Ljava/lang/String;
    //   152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: pop
    //   156: aload_0
    //   157: ldc ': unsupported complex color tag '
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload_0
    //   164: aload #5
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: new org/xmlpull/v1/XmlPullParserException
    //   173: dup
    //   174: aload_0
    //   175: invokevirtual toString : ()Ljava/lang/String;
    //   178: invokespecial <init> : (Ljava/lang/String;)V
    //   181: athrow
    //   182: aload_0
    //   183: aload #4
    //   185: aload #6
    //   187: aload_2
    //   188: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/Shader;
    //   191: invokestatic from : (Landroid/graphics/Shader;)Landroid/support/v4/content/res/ComplexColorCompat;
    //   194: areturn
    //   195: aload_0
    //   196: aload #4
    //   198: aload #6
    //   200: aload_2
    //   201: invokestatic createFromXmlInner : (Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/content/res/ColorStateList;
    //   204: invokestatic from : (Landroid/content/res/ColorStateList;)Landroid/support/v4/content/res/ComplexColorCompat;
    //   207: areturn
  }
  
  static ComplexColorCompat from(@ColorInt int paramInt) {
    return new ComplexColorCompat(null, null, paramInt);
  }
  
  static ComplexColorCompat from(@NonNull ColorStateList paramColorStateList) {
    return new ComplexColorCompat(null, paramColorStateList, paramColorStateList.getDefaultColor());
  }
  
  static ComplexColorCompat from(@NonNull Shader paramShader) {
    return new ComplexColorCompat(paramShader, null, 0);
  }
  
  @Nullable
  public static ComplexColorCompat inflate(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) {
    try {
      return createFromXml(paramResources, paramInt, paramTheme);
    } catch (Exception exception) {
      Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", exception);
      return null;
    } 
  }
  
  @ColorInt
  public int getColor() {
    return this.mColor;
  }
  
  @Nullable
  public Shader getShader() {
    return this.mShader;
  }
  
  public boolean isGradient() {
    return (this.mShader != null);
  }
  
  public boolean isStateful() {
    return (this.mShader == null && this.mColorStateList != null && this.mColorStateList.isStateful());
  }
  
  public boolean onStateChanged(int[] paramArrayOfint) {
    if (isStateful()) {
      int i = this.mColorStateList.getColorForState(paramArrayOfint, this.mColorStateList.getDefaultColor());
      if (i != this.mColor) {
        this.mColor = i;
        return true;
      } 
    } 
    return false;
  }
  
  public void setColor(@ColorInt int paramInt) {
    this.mColor = paramInt;
  }
  
  public boolean willDraw() {
    return (isGradient() || this.mColor != 0);
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\android\support\v4\content\res\ComplexColorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */