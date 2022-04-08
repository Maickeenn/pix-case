package br.com.itau.pix.integration.entity;

import br.com.itau.pix.domain.model.AccountType;
import br.com.itau.pix.domain.model.KeyType;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pix_key")
public class KeyEntity {

    @Id
    @NotNull
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotNull
    @Column(length = 9)
    @Enumerated(EnumType.STRING)
    private KeyType keyType;

    @NotNull
    @Column(length = 77, unique = true)
    private String keyValue;

    @NotNull
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull
    @Column(length = 4)
    private int branchNumber;

    @NotNull
    @Column(length = 8)
    private int accountNumber;

    @NotNull
    @Column(length = 30)
    private String clientName;

    @NotNull
    @Column(length = 45)
    private String clientLastName;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime deactivationDate;

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public KeyEntity setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public KeyEntity setDeactivationDate(LocalDateTime deleteDate) {
        this.deactivationDate = deleteDate;
        return this;
    }

    public KeyEntity() {

    }

    public String getKeyValue() {
        return keyValue;
    }

    public KeyEntity setKeyValue(String keyValue) {
        this.keyValue = keyValue;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public KeyEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public KeyEntity setKeyType(KeyType keyType) {
        this.keyType = keyType;
        return this;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public KeyEntity setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

    public KeyEntity setBranchNumber(int branchNumber) {
        this.branchNumber = branchNumber;
        return this;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public KeyEntity setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public KeyEntity setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public KeyEntity setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
        return this;
    }

    public static KeyEntity builder() {
        return new KeyEntity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyEntity)) return false;

        KeyEntity entity = (KeyEntity) o;

        if (getBranchNumber() != entity.getBranchNumber()) return false;
        if (getAccountNumber() != entity.getAccountNumber()) return false;
        if (getId() != null ? !getId().equals(entity.getId()) : entity.getId() != null) return false;
        if (getKeyType() != entity.getKeyType()) return false;
        if (getKeyValue() != null ? !getKeyValue().equals(entity.getKeyValue()) : entity.getKeyValue() != null)
            return false;
        if (getAccountType() != entity.getAccountType()) return false;
        if (getClientName() != null ? !getClientName().equals(entity.getClientName()) : entity.getClientName() != null)
            return false;
        if (getClientLastName() != null ? !getClientLastName().equals(entity.getClientLastName()) : entity.getClientLastName() != null)
            return false;
        if (getCreateDate() != null ? !getCreateDate().equals(entity.getCreateDate()) : entity.getCreateDate() != null)
            return false;
        return getDeactivationDate() != null ? getDeactivationDate().equals(entity.getDeactivationDate()) : entity.getDeactivationDate() == null;
    }
}
