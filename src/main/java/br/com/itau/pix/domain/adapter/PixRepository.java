package br.com.itau.pix.domain.adapter;

import br.com.itau.pix.domain.model.KeyType;
import br.com.itau.pix.integration.entity.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PixRepository extends JpaRepository<KeyEntity, UUID> {

    long countAllByBranchNumberAndAccountNumber(int branch, int account);
    Optional<KeyEntity> findById(UUID id);
    List<KeyEntity> findAllByKeyType(KeyType keyType);
    List<KeyEntity> findAllByBranchNumberAndAccountNumber(int branch, int account);
    List<KeyEntity> findAllByClientNameAndClientLastName(String clientName, String clientLastName);
    List<KeyEntity> findAllByCreateDate(LocalDateTime createDate);
    List<KeyEntity> findAllByDeactivationDate(LocalDateTime deactivationDate);

}
