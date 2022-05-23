package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getCredentials(int userId);


    @Insert("INSERT INTO CREDENTIALS (url, username, key, userid, password ) VALUES(#{url}, #{username}, #{key}, #{userId}, #{password} )")
    @Options(useGeneratedKeys = true, keyColumn = "credentialId", keyProperty = "credentialId")
    int insert(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteByCredentialId(Integer credentialId);

    @Update ("UPDATE CREDENTIALS SET url=#{url}, username= #{username}, key=#{key}, password=#{encryptedPassword} WHERE credentialId = #{credentialId}")
    void updateCredential(Credential credential);

}