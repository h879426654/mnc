package com.basics.support;

/**
 * Cryptor.
 */
public interface PasswordCryptor {
 /**
  * Encrypt the plain text password.
  * 
  * @param plainKey
  *         The password.
  * @return The encrypted password String.
  * @throws Exception
  *          If an error occurs.
  */
 String encrypt(String plainKey) throws Exception;

 /**
  * Decrypts the password.
  * 
  * @param encryptedKey
  *         the encrypted password.
  * @return The plain text password.
  * @throws Exception
  *          If an error occurs.
  */
 String decrypt(String encryptedKey) throws Exception;
}