package br.com.itau.pix.domain.service;

import br.com.itau.pix.controller.model.KeyDTO;
import br.com.itau.pix.domain.adapter.PixRepository;
import br.com.itau.pix.domain.exception.InvalidKeyException;
import br.com.itau.pix.domain.exception.KeyNotFoundException;
import br.com.itau.pix.domain.exception.MaxKeyException;
import br.com.itau.pix.domain.model.ClientType;
import br.com.itau.pix.domain.model.KeyType;
import br.com.itau.pix.domain.model.util.KeyMapper;
import br.com.itau.pix.domain.port.PixPort;
import br.com.itau.pix.integration.entity.KeyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.itau.pix.domain.model.util.KeyMapper.entityToKeyDto;
import static br.com.itau.pix.domain.model.util.KeyMapper.dtoToEntity;

@Service
public class PixService implements PixPort {

    @Autowired
    private PixRepository pixRepository;

    @Override
    public UUID createKey(KeyDTO keyDTO) {

        canAddNewKey(keyDTO);

        return pixRepository.save(dtoToEntity(keyDTO).setCreateDate(LocalDateTime.now())).getId();
    }

    @Override
    public KeyDTO updateKey(KeyDTO keyDTO) {
        final Optional<KeyEntity> byId = pixRepository.findByIdAndDeactivationDate(keyDTO.getKeyId(), null);
        if (byId.isEmpty()) {
            throw new KeyNotFoundException();
        } else if (byId.get().getDeactivationDate() != null) {
                throw new InvalidKeyException();
        }
        return entityToKeyDto(pixRepository.save(dtoToEntity(keyDTO)));
    }

    @Override
    public KeyDTO deactivateKey(UUID id) {
        final Optional<KeyEntity> key = pixRepository.findByIdAndDeactivationDate(id, null);
        return entityToKeyDto(pixRepository.save(key.orElseThrow(() -> new InvalidKeyException()).setDeactivationDate(LocalDateTime.now())));
    }

    @Override
    public KeyEntity findKeyById(UUID keyId) {
        return pixRepository.findByIdAndDeactivationDate(keyId, null).orElseThrow(() -> new KeyNotFoundException());
    }

    @Override
    public List<KeyDTO> findKeyByKeyType(KeyType keyType) {
        return pixRepository.findAllByKeyTypeAndDeactivationDate(keyType, null).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByBranchAndAccount(int branch, int account) {
        return pixRepository.findAllByBranchNumberAndAccountNumberAndDeactivationDate(branch, account, null).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByClientName(String clientName, String clientLastName) {
        return pixRepository.findAllByClientNameAndClientLastNameAndDeactivationDate(clientName, clientLastName, null).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByCreateDate(LocalDateTime createDate) {
        return pixRepository.findAllByCreateDateAndDeactivationDate(createDate, null).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByDeactivateDate(LocalDateTime deactivationDate) {
        return pixRepository.findAllByDeactivationDateAndDeactivationDate(deactivationDate, null).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    private void canAddNewKey(KeyDTO keyDTO) {
        int maxKey = keyDTO.getClientType().equals(ClientType.PF) ? 5 : 20;
        if (pixRepository.countAllByBranchNumberAndAccountNumberAndDeactivationDate(Integer.valueOf(keyDTO.getBranchNumber()), Integer.valueOf(keyDTO.getAccounteNumber()), null) >= maxKey)
            throw new MaxKeyException();
    }
}
