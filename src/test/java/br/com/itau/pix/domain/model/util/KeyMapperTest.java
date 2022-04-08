package br.com.itau.pix.domain.model.util;

import br.com.itau.pix.controller.model.KeyDTO;
import br.com.itau.pix.domain.model.AccountType;
import br.com.itau.pix.domain.model.ClientType;
import br.com.itau.pix.domain.model.KeyType;
import br.com.itau.pix.integration.entity.KeyEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static br.com.itau.pix.domain.model.util.KeyGenerator.getDTO;
import static br.com.itau.pix.domain.model.util.KeyGenerator.getEntity;
import static br.com.itau.pix.domain.model.util.KeyMapper.entityToKeyDto;
import static br.com.itau.pix.domain.model.util.KeyMapper.dtoToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyMapperTest {

    @Test
    public void shouldMapEntityToDTO() {
        UUID keyId = UUID.randomUUID();
        KeyEntity entity = getEntity(keyId, KeyType.CPF, "123456789012", AccountType.CHECKING, null, null);

        assertEquals(entity, dtoToEntity(getDTO(keyId, KeyType.CPF, "123456789012", AccountType.CHECKING, ClientType.PF,null, null)));
    }

    @Test
    public void shouldMapDTOToEntity() {
        UUID keyId = UUID.randomUUID();
        KeyDTO dto = getDTO(keyId, KeyType.CPF, "123456789012", AccountType.CHECKING, ClientType.PF,null, null);

        assertEquals(dto, entityToKeyDto(getEntity(keyId, KeyType.CPF, "123456789012", AccountType.CHECKING, null, null)));
    }


}
