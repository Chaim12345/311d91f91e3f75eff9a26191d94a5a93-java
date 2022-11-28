package com.google.crypto.tink.mac;

import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;
/* loaded from: classes2.dex */
public final class MacConfig {
    public static final String HMAC_TYPE_URL = new HmacKeyManager().getKeyType();
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;

    static {
        RegistryConfig defaultInstance = RegistryConfig.getDefaultInstance();
        TINK_1_0_0 = defaultInstance;
        TINK_1_1_0 = defaultInstance;
        LATEST = defaultInstance;
        try {
            init();
        } catch (GeneralSecurityException e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    private MacConfig() {
    }

    @Deprecated
    public static void init() {
        register();
    }

    public static void register() {
        HmacKeyManager.register(true);
        AesCmacKeyManager.register(true);
        MacWrapper.register();
    }

    @Deprecated
    public static void registerStandardKeyTypes() {
        register();
    }
}
