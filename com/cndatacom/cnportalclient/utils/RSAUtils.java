package com.cndatacom.cnportalclient.utils;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtils {
  private static byte[] a(Cipher paramCipher, int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    if (paramInt1 == 2) {
      paramInt1 = paramInt2 / 8;
    } else {
      paramInt1 = paramInt2 / 8 - 11;
    } 
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    paramInt2 = 0;
    int i = 0;
    while (paramArrayOfbyte.length > paramInt2) {
      byte[] arrayOfByte1;
      if (paramArrayOfbyte.length - paramInt2 > paramInt1) {
        arrayOfByte1 = paramCipher.doFinal(paramArrayOfbyte, paramInt2, paramInt1);
      } else {
        arrayOfByte1 = paramCipher.doFinal(paramArrayOfbyte, paramInt2, paramArrayOfbyte.length - paramInt2);
      } 
      byteArrayOutputStream.write(arrayOfByte1, 0, arrayOfByte1.length);
      paramInt2 = ++i * paramInt1;
    } 
    byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
    byteArrayOutputStream.close();
    return arrayOfByte;
  }
  
  public static byte[] encryptByPublicKey(byte[] paramArrayOfbyte) {
    RSAPublicKey rSAPublicKey = (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKzDO99FYDlQKslgsplrIun+QvV6mO1uXO6p3j6T13nEf4/hAJXEhEQSUZssuhu6yCca4BU+R7pV7P7FQmEs2XUCAwEAAQ==", 2)));
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(1, rSAPublicKey);
    return a(cipher, 1, paramArrayOfbyte, rSAPublicKey.getModulus().bitLength());
  }
}


/* Location:              C:\Users\15856\Desktop\2675384-dex2jar.jar!\com\cndatacom\cnportalclien\\utils\RSAUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */