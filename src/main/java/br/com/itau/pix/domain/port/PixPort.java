package br.com.itau.pix.domain.port;

import br.com.itau.pix.controller.model.KeyDTO;
import br.com.itau.pix.domain.model.KeyType;
import br.com.itau.pix.integration.entity.KeyEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface PixPort {

    UUID createKey(KeyDTO keyDTO);

    KeyDTO updateKey(KeyDTO keyDTO);

    KeyDTO deactivateKey(UUID id);

    KeyEntity findKeyById(UUID keyId);

    List<KeyDTO> findKeyByKeyType(KeyType keyType);

    List<KeyDTO> findKeyByBranchAndAccount(int branch, int Account);

    List<KeyDTO> findKeyByClientName(String clientName, String clienteLastName);

    List<KeyDTO> findKeyByCreateDate(LocalDateTime createDate);

    List<KeyDTO> findKeyByDeactivateDate(LocalDateTime DeactivateDate);

}
