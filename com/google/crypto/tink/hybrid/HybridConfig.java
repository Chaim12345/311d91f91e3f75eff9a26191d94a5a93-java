package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;
/* loaded from: classes2.dex */
public final class HybridConfig {
    public static final String ECIES_AEAD_HKDF_PUBLIC_KEY_TYPE_URL = new EciesAeadHkdfPublicKeyManager().getKeyType();
    public static final String ECIES_AEAD_HKDF_PRIVATE_KEY_TYPE_URL = new EciesAeadHkdfPrivateKeyManager().getKeyType();
    @Deprecated
    public static final RegistryConfig TINK_1_0_0 = RegistryConfig.getDefaultInstance();
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

    @Deprecated
    public static void init() {
        register();
    }

    public static void register() {
        AeadConfig.register();
        EciesAeadHkdfPrivateKeyManager.registerPair(true);
        HybridDecryptWrapper.register();
        HybridEncryptWrapper.register();
    }
}
