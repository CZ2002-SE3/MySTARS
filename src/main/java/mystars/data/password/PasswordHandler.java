package mystars.data.password;

import mystars.data.exception.MyStarsException;
import mystars.parser.Parser;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHandler {

    private static final int PBKDF2_ITERATIONS = 32768;
    private static final int KEY_LENGTH = 16;

    public String generatePBKDF2String(char[] password) throws MyStarsException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[KEY_LENGTH];
        random.nextBytes(salt);
        byte[] hash = generatePBKDF2(password, salt, PBKDF2_ITERATIONS, KEY_LENGTH);
        return Base64.getEncoder().encodeToString(salt) + Parser.TILDE_SEPARATOR
                + Base64.getEncoder().encodeToString(hash);
    }

    public byte[] generatePBKDF2(char[] password, byte[] salt, int iterations, int bytes) throws MyStarsException {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new MyStarsException("Hashing issue!");
        }
    }

    public boolean validatePassword(char[] password, char[] goodHash) throws MyStarsException {
        String[] params = String.valueOf(goodHash).split(Parser.TILDE_SEPARATOR);
        byte[] salt = Base64.getDecoder().decode(params[0]);
        byte[] hash = Base64.getDecoder().decode(params[1]);
        byte[] testHash = generatePBKDF2(password, salt, PBKDF2_ITERATIONS, hash.length);
        return slowEquals(hash, testHash);
    }

    private boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
