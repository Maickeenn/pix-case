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
        final Optional<KeyEntity> byId = pixRepository.findById(keyDTO.getKeyId());
        if (byId.isEmpty()) {
            throw new KeyNotFoundException();
        } else if (byId.get().getDeactivationDate() == null) {
                throw new InvalidKeyException();
        }
        return entityToKeyDto(pixRepository.save(dtoToEntity(keyDTO)));
    }

    @Override
    public KeyDTO deactivateKey(UUID id) {
        final Optional<KeyEntity> key = pixRepository.findById(id);
        return entityToKeyDto(pixRepository.save(key.orElseThrow(() -> new InvalidKeyException()).setDeactivationDate(LocalDateTime.now())));
    }

    @Override
    public KeyEntity findKeyById(UUID keyId) {
        return pixRepository.findById(keyId).orElseThrow(() -> new KeyNotFoundException());
    }

    @Override
    public List<KeyDTO> findKeyByKeyType(KeyType keyType) {
        return pixRepository.findAllByKeyType(keyType).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByBranchAndAccount(int branch, int account) {
        return pixRepository.findAllByBranchNumberAndAccountNumber(branch, account).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByClientName(String clientName, String clientLastName) {
        return pixRepository.findAllByClientNameAndClientLastName(clientName, clientLastName).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByCreateDate(LocalDateTime createDate) {
        return pixRepository.findAllByCreateDate(createDate).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeyDTO> findKeyByDeactivateDate(LocalDateTime deactivationDate) {
        return pixRepository.findAllByDeactivationDate(deactivationDate).stream()
                .map(KeyMapper::entityToKeyDto)
                .collect(Collectors.toList());
    }

    private void canAddNewKey(KeyDTO keyDTO) {
        int maxKey = keyDTO.getClientType().equals(ClientType.PF) ? 5 : 20;
        if (pixRepository.countAllByBranchNumberAndAccountNumber(Integer.valueOf(keyDTO.getBranchNumber()), Integer.valueOf(keyDTO.getAccounteNumber())) >= maxKey)
            throw new MaxKeyException();
    }
}
