package com.google.crypto.tink.daead;

import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;
/* loaded from: classes2.dex */
public final class DeterministicAeadConfig {
    public static final String AES_SIV_TYPE_URL = new AesSivKeyManager().getKeyType();
    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = RegistryConfig.getDefaultInstance();
    @Deprecated
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();

    static {
        try {
            init();
        } catch (GeneralSecurityException e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    private DeterministicAeadConfig() {
    }

    @Deprecated
    public static void init() {
        register();
    }

    public static void register() {
        AesSivKeyManager.register(true);
        DeterministicAeadWrapper.register();
    }
}
