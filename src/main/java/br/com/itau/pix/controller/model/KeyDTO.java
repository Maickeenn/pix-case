package br.com.itau.pix.controller.model;

import br.com.itau.pix.domain.model.AccountType;
import br.com.itau.pix.domain.model.ClientType;
import br.com.itau.pix.domain.model.KeyType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class KeyDTO {

    @JsonProperty(value = "chaveId")
    private UUID keyId;

    @JsonProperty(value = "tipoChave")
    private KeyType keyType;

    @JsonProperty(value = "valorChave")
    private String key;

    @JsonProperty(value = "tipoConta")
    private AccountType accountType;

    @JsonProperty(value = "numeroAgencia")
    private String branchNumber;

    @JsonProperty(value = "numeroConta")
    private String accounteNumber;

    @JsonProperty(value = "nome")
    private String clientName;

    @JsonProperty(value = "sobrenome")
    private String clientLastName;

    @JsonProperty(value = "tipoPessoa")
    private ClientType clientType;

    @JsonProperty(value = "dateHoraCriacao")
    private LocalDateTime createTime;

    @JsonProperty(value = "dataHoraInativacao")
    private LocalDateTime deactivateDateTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public KeyDTO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getDeactivateDateTime() {
        return deactivateDateTime;
    }

    public KeyDTO setDeactivateDateTime(LocalDateTime deactivateDateTime) {
        this.deactivateDateTime = deactivateDateTime;
        return this;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public KeyDTO setKeyType(KeyType keyType) {
        this.keyType = keyType;
        return this;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public KeyDTO setClientType(ClientType clientType) {
        this.clientType = clientType;
        return this;
    }

    public String getKey() {
        return key;
    }

    public KeyDTO setKey(String key) {
        this.key = key;
        return this;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public KeyDTO setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public KeyDTO setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
        return this;
    }

    public String getAccounteNumber() {
        return accounteNumber;
    }

    public KeyDTO setAccounteNumber(String accounteNumber) {
        this.accounteNumber = accounteNumber;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public KeyDTO setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public KeyDTO setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
        return this;
    }

    public UUID getKeyId() {
        return keyId;
    }

    public KeyDTO setKeyId(UUID keyId) {
        this.keyId = keyId;
        return this;
    }
    public static KeyDTO builder(){
        return new KeyDTO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyDTO)) return false;

        KeyDTO keyDTO = (KeyDTO) o;

        if (getKeyId() != null ? !getKeyId().equals(keyDTO.getKeyId()) : keyDTO.getKeyId() != null) return false;
        if (getKeyType() != keyDTO.getKeyType()) return false;
        if (getKey() != null ? !getKey().equals(keyDTO.getKey()) : keyDTO.getKey() != null) return false;
        if (getAccountType() != keyDTO.getAccountType()) return false;
        if (getBranchNumber() != null ? !getBranchNumber().equals(keyDTO.getBranchNumber()) : keyDTO.getBranchNumber() != null)
            return false;
        if (getAccounteNumber() != null ? !getAccounteNumber().equals(keyDTO.getAccounteNumber()) : keyDTO.getAccounteNumber() != null)
            return false;
        if (getClientName() != null ? !getClientName().equals(keyDTO.getClientName()) : keyDTO.getClientName() != null)
            return false;
        if (getClientLastName() != null ? !getClientLastName().equals(keyDTO.getClientLastName()) : keyDTO.getClientLastName() != null)
            return false;
        if (getCreateTime() != null ? !getCreateTime().equals(keyDTO.getCreateTime()) : keyDTO.getCreateTime() != null)
            return false;
        return getDeactivateDateTime() != null ? getDeactivateDateTime().equals(keyDTO.getDeactivateDateTime()) : keyDTO.getDeactivateDateTime() == null;
    }
}
