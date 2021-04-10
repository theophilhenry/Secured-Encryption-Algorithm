/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author TOTO
 */
public class Security {
   
   //==========================================================================================================================================================================================================================
   //---------------------------------------------------------------------------------------AES-------------------------------------------------------------------------------------------------
   //==========================================================================================================================================================================================================================
    
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-512");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String EncryptAES(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String DecryptAES(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
            
    //==========================================================================================================================================================================================================================
    //------------------------------------------------------------------------------------HASH--------------------------------------------------------------------------------------------------------------------------------------
    //==========================================================================================================================================================================================================================
    
    
    
    public static String MakeSalt()
    {
        SecureRandom random1 = new SecureRandom();
            byte[] salted = new byte[16];
            random1.nextBytes(salted);
            String s = Base64.getEncoder().encodeToString(salted);
            return s;
    }
    
    public static String MakeHash(String text, String salt) throws NoSuchAlgorithmException
    {
        String result = "";
        
        try {
            
            
                int total = 0;

                
                for(int i=0;i<text.length();i++)
                {
                    total += (int)text.charAt(i);
                }
            
            
            byte[] salPasti = new byte[16];
            salPasti = salt.getBytes();
           
            
           
                      
            
            for (int i = 0; i < 2; i++) 
            {
                KeySpec spec = new PBEKeySpec(text.toCharArray(),salPasti , 65536, 512);
                SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] hash = f.generateSecret(spec).getEncoded();
                Base64.Encoder enc = Base64.getEncoder();

                String hashPlusSalt = enc.encodeToString(hash) + salt;

                KeySpec spec2 = new PBEKeySpec(hashPlusSalt.toCharArray(), salPasti, 65536, 512);
                SecretKeyFactory f2 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
                byte[] hash2 = f2.generateSecret(spec2).getEncoded();

                result = enc.encodeToString(hash2);
               
            }
            
            
            
            
            
            
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public static int getDecimal(String hex){  
    String digits = "0123456789ABCDEF";  
             hex = hex.toUpperCase();  
             int val = 0;  
             for (int i = 0; i < hex.length(); i++)  
             {  
                 char c = hex.charAt(i);  
                 int d = digits.indexOf(c);  
                 val = 16*val + d;  
             }  
             return val;  

    }  
    
    //==========================================================================================================================================================================================================================
    //--------------------------------------------------------------------------------------------RSA---------------------------------------------------------------------------------------------------------------------
    //==========================================================================================================================================================================================================================
    
    
    
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public void RSAKeyGenerator(int number) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(number);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
    
    
    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    
    
    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] EncryptRSA(String data, String pubKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(pubKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String DecryptRSA(byte[] data, PrivateKey privKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        return new String(cipher.doFinal(data));
    }

    public static String DecryptRSA(String data, String base64PrivKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return DecryptRSA(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivKey));
    }
    
    
    
    //==========================================================================================================================================================================================================================
    //--------------------------------------------------------------------------------------------YANG PERLU---------------------------------------------------------------------------------------------------------------------
    //==========================================================================================================================================================================================================================
    
    
    public static String Encrypt3(String text,String keyAES,String salt,String pubRSAKey)
    {
        String result = "";
        try {
            
            
            String hash = MakeHash(text, salt);
            String encryptRSA = Base64.getEncoder().encodeToString(Security.EncryptRSA(hash, pubRSAKey));
            String encryptAES = EncryptAES(encryptRSA,keyAES);
            result = encryptAES;          
            
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static String Decrypt3(String cipher, String keyAES, String salt, String privRSAKey)
    {
        String result = "";
        try {
            
            
            String decryptAES = DecryptAES(cipher, keyAES);
            String decryptRSA = DecryptRSA(decryptAES, privRSAKey);
            result = decryptRSA;
            
            
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static String Encrypt2(String text,String keyAES,String pubRSAKey)
    {
        String result = "";
        try {
            
            
            String encryptAES = EncryptAES(text,keyAES);
            String encryptRSA = Base64.getEncoder().encodeToString(Security.EncryptRSA(encryptAES, pubRSAKey));
            result = encryptRSA;
            
            
        } catch (BadPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public static String Decrypt2(String cipher, String keyAES, String privRSAKey)
    {
        String result = "";
        try {
            
            
            String decryptRSA = DecryptRSA(cipher, privRSAKey);
            String decryptAES = DecryptAES(decryptRSA, keyAES);
            result = decryptRSA;
            
            
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    
}


    

