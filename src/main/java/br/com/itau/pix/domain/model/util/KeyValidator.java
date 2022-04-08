package br.com.itau.pix.domain.model.util;

import br.com.itau.pix.domain.model.KeyType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.itau.pix.domain.model.util.CpfValidator.stringToListInteger;

public class KeyValidator {

    public static boolean validKey(String key, KeyType keyType) {
        switch (keyType) {
            case CELULAR:
                Pattern pattern = Pattern.compile("\\+[0-9]{2}[0-9]{2,3}[0-9]{9}$");
                Matcher matcher = pattern.matcher(key);
                return matcher.find();
            case EMAIL:
                Pattern pattern1 = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@([a-z0-9]+[a-z0-9]*)+(\\.[a-z]+[a-z]*){1,2}");
                Matcher matcher1 = pattern1.matcher(key);
                return matcher1.find() && key.length() <= 77;
            case CPF:
                return CpfValidator.validate(stringToListInteger(key));
            case CNPJ:
                // TODO
                return false;
            default:
                return key.length() <= 36;
        }
    }
}
