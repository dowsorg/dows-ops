package org.dows.ssh;

import lombok.Getter;

@Getter
public enum SshKeyTypes {
    /**
     * The default generate away
     */
    rsa("id_rsa"),
    ed25519("id_ed25519");
    private String privateKeyFile;

    SshKeyTypes(String privateKeyFile) {
        this.privateKeyFile = privateKeyFile;
    }
}