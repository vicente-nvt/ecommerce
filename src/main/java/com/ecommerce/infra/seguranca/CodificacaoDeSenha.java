package com.ecommerce.infra.seguranca;

import java.security.MessageDigest;

import com.ecommerce.dominio.entidades.CodificadorDeSenha;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CodificacaoDeSenha implements PasswordEncoder, CodificadorDeSenha {

    @Override
    public String encode(CharSequence rawPassword) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(rawPassword.toString().getBytes("ASCII"));
        } catch (Exception e) {
        }
        byte[] hash = digest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    @Override
    public String codificar(String senha) {
        return encode(senha);
    }
}