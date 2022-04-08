package br.com.itau.pix.domain.model.util;

import br.com.itau.pix.domain.model.KeyType;
import org.junit.jupiter.api.Test;

import static br.com.itau.pix.domain.model.util.KeyValidator.validKey;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KeyValidatorTest {

    @Test
    public void shouldValidateCellNumberKey(){
        assertTrue(validKey("+55011999999999", KeyType.CELULAR));
        assertTrue(validKey("+5511999999999", KeyType.CELULAR));
        assertFalse(validKey("011999999999", KeyType.CELULAR));
        assertFalse(validKey("+011999999999", KeyType.CELULAR));
        assertFalse(validKey("+5511999999A99", KeyType.CELULAR));
        assertFalse(validKey("+550119999999991", KeyType.CELULAR));
        assertFalse(validKey("+551199999999", KeyType.CELULAR));
    }

    @Test
    public void shouldValidateCpf(){
        assertTrue(validKey("42201678898", KeyType.CPF));
        assertFalse(validKey("42201678897", KeyType.CPF));
        assertFalse(validKey("4220167889", KeyType.CPF));
        assertFalse(validKey("422016788982", KeyType.CPF));
    }

    @Test
    public void shouldValidateEmail(){
        assertTrue(validKey("teste@teste.com", KeyType.EMAIL));
        assertTrue(validKey("teste@teste.com.br", KeyType.EMAIL));
        assertFalse(validKey("testeasdasdasdasdasdasdqwdasikdujglasiydhblqwiysdgjasvbdpuiyhqalowdjhgvqwkdyghujtasfdqwvckidyhfgstajmydtfgqwscaikvydshjagsytdfvcqkwsduasjydftsacikfdhasmgtdfascv@teste.com.br", KeyType.EMAIL));
        assertFalse(validKey("testeteste.com", KeyType.EMAIL));
        assertFalse(validKey("@teste.com.br", KeyType.EMAIL));
        assertFalse(validKey("teste@.com.br", KeyType.EMAIL));
        assertFalse(validKey("teste@", KeyType.EMAIL));
    }

    @Test
    public void shouldValidateAleatoria(){
        assertTrue(validKey("dasiulydagsbldikygwvdlsadqwd", KeyType.ALEATORIO));
    }
}
