package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private EncryptionService encryptionService;
    private CredentialMapper credentialMapper;


    public CredentialService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentials(int userId) {
        List<Credential> credentials = credentialMapper.getCredentials(userId);
        for (Credential credential : credentials) {
            credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        }
        return credentials;
    }

    public void createCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedKey));
        credentialMapper.insert(new Credential(null, credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getUserId(), credential.getPassword()));
    }

}

