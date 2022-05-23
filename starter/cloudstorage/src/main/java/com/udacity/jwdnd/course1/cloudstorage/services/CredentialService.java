package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;

public class CredentialService {
    private EncryptionService encryptionService;

    public CredentialService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public String encryptPassword(String password){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        return encryptedPassword;
    }

    public String decryptPassword(String encryptedPassword){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
        return decryptedPassword;
    }

}
