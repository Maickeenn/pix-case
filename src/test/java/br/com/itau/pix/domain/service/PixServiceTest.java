package br.com.itau.pix.domain.service;

import br.com.itau.pix.controller.model.KeyDTO;
import br.com.itau.pix.domain.adapter.PixRepository;
import br.com.itau.pix.domain.exception.InvalidKeyException;
import br.com.itau.pix.domain.exception.KeyNotFoundException;
import br.com.itau.pix.domain.exception.MaxKeyException;
import br.com.itau.pix.domain.model.AccountType;
import br.com.itau.pix.domain.model.ClientType;
import br.com.itau.pix.domain.model.KeyType;
import br.com.itau.pix.integration.entity.KeyEntity;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.itau.pix.domain.model.util.KeyGenerator.getDTO;
import static br.com.itau.pix.domain.model.util.KeyMapper.dtoToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PixServiceTest {

    @Mock
    private PixRepository pixRepository;

    @InjectMocks
    private final PixService pixService = new PixService();

    @Test
    public void shouldCreateKey(){
        final UUID keyId = UUID.randomUUID();
        final KeyDTO dto = getDTO(null, KeyType.CPF, "123456789012", AccountType.CHECKING, ClientType.PF,null, null);
        final KeyEntity entity = dtoToEntity(dto);
        when(pixRepository.countAllByBranchNumberAndAccountNumber(0,1)).thenReturn(1L);
        when(pixRepository.save(any(KeyEntity.class))).thenReturn(entity.setId(keyId));
        assertEquals(keyId, pixService.createKey(dto));
    }

    @Test()
    public void shouldNotCreateKey(){
        final KeyDTO dto = getDTO(null, KeyType.CPF, "123456789012", AccountType.CHECKING, ClientType.PF,null, null);
        when(pixRepository.countAllByBranchNumberAndAccountNumber(0,1)).thenReturn(6L);
        assertThrows(MaxKeyException.class, () -> pixService.createKey(dto));

        dto.setClientType(ClientType.PJ);
        when(pixRepository.countAllByBranchNumberAndAccountNumber(0,1)).thenReturn(21L);
        assertThrows(MaxKeyException.class, () -> pixService.createKey(dto));

    }

    @Test
    public void shouldUpdateKey(){
        final UUID keyId = UUID.randomUUID();
        final LocalDateTime now = LocalDateTime.now();
        final KeyDTO dto = getDTO(keyId, KeyType.CPF, "123456789012", AccountType.CHECKING, ClientType.PF,null, now);
        final KeyEntity entity = dtoToEntity(dto);
        when(pixRepository.findById(any(UUID.class))).thenReturn(Optional.of(entity.setId(keyId).setDeactivationDate(now)));
        when(pixRepository.save(any(KeyEntity.class))).thenReturn(entity.setId(keyId));
        assertEquals(dto, pixService.updateKey(dto));
    }

    @Test
    public void shouldNotUpdateKey(){
        final UUID keyId = UUID.randomUUID();
        final KeyDTO dto = getDTO(keyId, KeyType.CPF, "123456789012", AccountType.CHECKING, ClientType.PF,null, null);
        final KeyEntity entity = dtoToEntity(dto);
        when(pixRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(KeyNotFoundException.class,() -> pixService.updateKey(dto));

        when(pixRepository.findById(any(UUID.class))).thenReturn(Optional.of(entity.setCreateDate(null)));
        assertThrows(InvalidKeyException.class,() -> pixService.updateKey(dto));

    }
}
