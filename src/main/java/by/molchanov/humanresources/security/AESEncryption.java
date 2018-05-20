package by.molchanov.humanresources.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESEncryption {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int AES_KEY_LENGTH = 16;
    private static final int FIRST_SYMBOL_POSITION = 0;
    private static final String ENCRYPTION_TYPE = "AES";

    public String encryptionOfString(String primaryString) {
        String encryptedString = primaryString;
        String keyString = primaryString;
        if (primaryString.length() > AES_KEY_LENGTH) {
            keyString = primaryString.substring(FIRST_SYMBOL_POSITION, AES_KEY_LENGTH);
        } else if (primaryString.length() < AES_KEY_LENGTH) {
            StringBuilder changedValue = new StringBuilder(primaryString);
            int symbolNumber = changedValue.length();
            for (; symbolNumber < AES_KEY_LENGTH; symbolNumber++) {
                changedValue.append(primaryString.charAt(FIRST_SYMBOL_POSITION));
            }
            keyString = changedValue.toString();
        }
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_TYPE);
            SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), ENCRYPTION_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedValue = cipher.doFinal(primaryString.getBytes());
            BigInteger value = new BigInteger(1, encryptedValue);
            encryptedString = value.toString(AES_KEY_LENGTH);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            LOGGER.warn("String wasn't encrypted!", e);
        }
        return encryptedString;
    }
}
