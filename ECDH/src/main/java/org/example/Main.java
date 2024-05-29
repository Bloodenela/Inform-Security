package org.example;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Main {
    public static void main(String[] args) throws Exception{

        String s = "Helloworld!";
        KeyPair keyPair = ECDH.keysGenerate();
        byte[] sharedSecret = ECDH.shareSecret(ECDH.getPublicKey(keyPair), ECDH.getPrivateKey(keyPair));
        String encryptMessage = AES.encryptMessage(s, sharedSecret);
        String decryptmessage = AES.decryptMessage(encryptMessage, sharedSecret);
        System.out.println(encryptMessage);
        System.out.println(decryptmessage);
    }
}