package br.com.itau.pix.domain.model.util;

import br.com.itau.pix.controller.model.KeyDTO;
import br.com.itau.pix.integration.entity.KeyEntity;

public class KeyMapper {

    public static KeyEntity dtoToEntity(KeyDTO keyDTO) {
        return KeyEntity.builder()
                .setId(keyDTO.getKeyId())
                .setKeyType(keyDTO.getKeyType())
                .setKeyValue(keyDTO.getKey())
                .setAccountType(keyDTO.getAccountType())
                .setBranchNumber(Integer.valueOf(keyDTO.getBranchNumber()))
                .setAccountNumber(Integer.valueOf(keyDTO.getAccounteNumber()))
                .setClientName(keyDTO.getClientName())
                .setClientLastName(keyDTO.getClientLastName());
    }

    public static KeyDTO entityToKeyDto(KeyEntity keyEntity) {
        return KeyDTO.builder()
                .setKeyId(keyEntity.getId())
                .setKeyType(keyEntity.getKeyType())
                .setKey(keyEntity.getKeyValue())
                .setAccountType(keyEntity.getAccountType())
                .setBranchNumber(String.valueOf(keyEntity.getBranchNumber()))
                .setAccounteNumber(String.valueOf(keyEntity.getAccountNumber()))
                .setClientName(keyEntity.getClientName())
                .setClientLastName(keyEntity.getClientLastName())
                .setCreateTime(keyEntity.getCreateDate())
                .setDeactivateDateTime(keyEntity.getDeactivationDate());
    }
}
