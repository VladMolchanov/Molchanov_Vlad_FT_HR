package by.molchanov.humanresources.security;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AESEncryptionTest {

    @Test
    public void encryptionOfStringChangeTest() {
        String firstTestString = "This is test";
        String secondTestString;
        AESEncryption aesEncryption = new AESEncryption();
        secondTestString = aesEncryption.encryptionOfString(firstTestString);
        Assert.assertNotEquals(firstTestString, secondTestString);
    }

    @Test
    public void encryptionOfStringReuseTest() {
        String firstTestString = "This is test";
        String secondTestString = firstTestString;
        AESEncryption aesEncryption = new AESEncryption();
        firstTestString = aesEncryption.encryptionOfString(firstTestString);
        secondTestString = aesEncryption.encryptionOfString(secondTestString);
        Assert.assertEquals(firstTestString, secondTestString);
    }
}
