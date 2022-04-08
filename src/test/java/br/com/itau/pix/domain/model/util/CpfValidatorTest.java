package br.com.itau.pix.domain.model.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static br.com.itau.pix.domain.model.util.CpfValidator.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CpfValidatorTest {

    @Test
    public void shouldGenerateRandomNumbers(){
        assertTrue(random() != random());
    }

    @Test
    public void shouldGenerateCheckDigit(){
        assertTrue(generateCheckDigit(null) == 0);
        assertTrue(generateCheckDigit(stringToListInteger("422016788")) == 9);
        assertTrue(generateCheckDigit(stringToListInteger("4220167889")) == 8);
    }
    @Test
    public void shouldValidateCpf(){
        assertTrue(validate(stringToListInteger("42201678898")));
        assertFalse(validate(stringToListInteger("42201678897")));
    }
}
