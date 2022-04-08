package br.com.itau.pix.domain.model.util;

import br.com.itau.pix.controller.model.KeyDTO;
import br.com.itau.pix.domain.model.AccountType;
import br.com.itau.pix.domain.model.ClientType;
import br.com.itau.pix.domain.model.KeyType;
import br.com.itau.pix.integration.entity.KeyEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class KeyGenerator {
    public static KeyEntity getEntity(UUID keyId, KeyType keyType, String key, AccountType accountType, LocalDateTime createTime, LocalDateTime deactivationDate){
        return KeyEntity.builder()
                .setId(keyId)
                .setKeyValue(key)
                .setKeyType(keyType)
                .setAccountType(accountType)
                .setBranchNumber(0)
                .setAccountNumber(1)
                .setClientName("Joao")
                .setClientLastName("Pedro")
                .setCreateDate(createTime)
                .setDeactivationDate(deactivationDate);
    }

    public static KeyDTO getDTO(UUID keyId, KeyType keyType, String key, AccountType accountType, ClientType clientType, LocalDateTime createTime,LocalDateTime deactivationDate){
        return KeyDTO.builder()
                .setKeyId(keyId)
                .setKey(key.toString())
                .setKeyType(keyType)
                .setAccountType(accountType)
                .setBranchNumber("0")
                .setAccounteNumber("1")
                .setClientType(clientType)
                .setClientName("Joao")
                .setClientLastName("Pedro")
                .setCreateTime(createTime)
                .setDeactivateDateTime(deactivationDate);
    }
}
