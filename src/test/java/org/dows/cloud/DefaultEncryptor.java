package org.dows.cloud;

import org.jasypt.util.text.BasicTextEncryptor;

public class DefaultEncryptor {
    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("02700083-9fd9-4b82-a4b4-9177e0560e92");
        String encrypt = encryptor.encrypt("");
        System.out.println(encrypt);
    }
}
