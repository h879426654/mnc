package com.basics.support;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Allows passwords to be encrypted and decrypted.
 *
 * @author <a href="http://commons.apache.org/vfs/team-list.html">Commons VFS
 *         team</a>
 * @since 2.0
 */
public class DefaultPasswordCryptor implements PasswordCryptor {
 private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

 private static final byte[] KEY_BYTES = { 0x41, 0x70, 0x61, 0x63, 0x68, 0x65, 0x43, 0x6F, 0x6D, 0x6D, 0x6F, 0x6E, 0x73, 0x56, 0x46, 0x53 };

 private static final int INDEX_NOT_FOUND = -1;

 private static final int BITS_IN_HALF_BYTE = 4;

 private static final char MASK = 0x0f;

 /**
  * Encrypt the plain text password.
  *
  * @param plainKey
  *         The password.
  * @return The encrypted password String.
  * @throws Exception
  *          If an error occurs.
  */
 public String encrypt(String plainKey) throws Exception {
  byte[] input = plainKey.getBytes();
  SecretKeySpec key = new SecretKeySpec(DefaultPasswordCryptor.KEY_BYTES, "AES");
  Cipher cipher = Cipher.getInstance("AES");
  // encryption pass
  cipher.init(Cipher.ENCRYPT_MODE, key);
  byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
  int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
  ctLength += cipher.doFinal(cipherText, ctLength);
  return this.encode(cipherText);
 }

 /**
  * Decrypts the password.
  *
  * @param encryptedKey
  *         the encrypted password.
  * @return The plain text password.
  * @throws Exception
  *          If an error occurs.
  */
 public String decrypt(String encryptedKey) throws Exception {
  SecretKeySpec key = new SecretKeySpec(DefaultPasswordCryptor.KEY_BYTES, "AES");
  Cipher cipher = Cipher.getInstance("AES");
  cipher.init(Cipher.DECRYPT_MODE, key);
  byte[] decoded = this.decode(encryptedKey);
  byte[] plainText = new byte[cipher.getOutputSize(decoded.length)];
  int ptLength = cipher.update(decoded, 0, decoded.length, plainText, 0);
  ptLength += cipher.doFinal(plainText, ptLength);
  return new String(plainText).substring(0, ptLength);
 }

 private String encode(byte[] bytes) {
  StringBuilder builder = new StringBuilder();

  for (int i = 0; i < bytes.length; ++i) {
   builder.append(DefaultPasswordCryptor.HEX_CHARS[(bytes[i] >> DefaultPasswordCryptor.BITS_IN_HALF_BYTE) & DefaultPasswordCryptor.MASK]);
   builder.append(DefaultPasswordCryptor.HEX_CHARS[bytes[i] & DefaultPasswordCryptor.MASK]);
  }
  return builder.toString();
 }

 private byte[] decode(String str) {
  int length = str.length() / 2;
  byte[] decoded = new byte[length];
  char[] chars = str.toCharArray();
  int index = 0;
  for (int i = 0; i < chars.length; ++i) {
   int id1 = this.indexOf(DefaultPasswordCryptor.HEX_CHARS, chars[i]);
   if (id1 == -1) {
    throw new IllegalArgumentException("Character " + chars[i] + " at position " + i + " is not a valid hexidecimal character");
   }
   int id2 = this.indexOf(DefaultPasswordCryptor.HEX_CHARS, chars[++i]);
   if (id2 == -1) {
    throw new IllegalArgumentException("Character " + chars[i] + " at position " + i + " is not a valid hexidecimal character");
   }
   decoded[index++] = (byte) ((id1 << DefaultPasswordCryptor.BITS_IN_HALF_BYTE) | id2);
  }
  return decoded;
 }

 private int indexOf(char[] array, char valueToFind) {
  if (array == null) {
   return DefaultPasswordCryptor.INDEX_NOT_FOUND;
  }
  for (int i = 0; i < array.length; i++) {
   if (valueToFind == array[i]) {
    return i;
   }
  }
  return DefaultPasswordCryptor.INDEX_NOT_FOUND;
 }
}