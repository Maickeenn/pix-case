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

    long countAllByBranchNumberAndAccountNumberAndDeactivationDate(int branch, int account, LocalDateTime deactrivationDate);
    Optional<KeyEntity> findByIdAndDeactivationDate(UUID id, LocalDateTime deactrivationDate);
    List<KeyEntity> findAllByKeyTypeAndDeactivationDate(KeyType keyType, LocalDateTime deactrivationDate);
    List<KeyEntity> findAllByBranchNumberAndAccountNumberAndDeactivationDate(int branch, int account, LocalDateTime deactrivationDate);
    List<KeyEntity> findAllByClientNameAndClientLastNameAndDeactivationDate(String clientName, String clientLastName, LocalDateTime deactrivationDate);
    List<KeyEntity> findAllByCreateDateAndDeactivationDate(LocalDateTime createDate, LocalDateTime deactrivationDate);
    List<KeyEntity> findAllByDeactivationDateAndDeactivationDate(LocalDateTime deactivationDate, LocalDateTime deactrivationDate);

}
