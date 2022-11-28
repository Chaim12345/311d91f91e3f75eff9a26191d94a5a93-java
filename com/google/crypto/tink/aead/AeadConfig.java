package com.google.crypto.tink.aead;

import com.google.crypto.tink.mac.MacConfig;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;
/* loaded from: classes2.dex */
public final class AeadConfig {
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;
    public static final String AES_CTR_HMAC_AEAD_TYPE_URL = new AesCtrHmacAeadKeyManager().getKeyType();
    public static final String AES_GCM_TYPE_URL = new AesGcmKeyManager().getKeyType();
    public static final String AES_GCM_SIV_TYPE_URL = new AesGcmSivKeyManager().getKeyType();
    public static final String AES_EAX_TYPE_URL = new AesEaxKeyManager().getKeyType();
    public static final String KMS_AEAD_TYPE_URL = new KmsAeadKeyManager().getKeyType();
    public static final String KMS_ENVELOPE_AEAD_TYPE_URL = new KmsEnvelopeAeadKeyManager().getKeyType();
    public static final String CHACHA20_POLY1305_TYPE_URL = new ChaCha20Poly1305KeyManager().getKeyType();
    public static final String XCHACHA20_POLY1305_TYPE_URL = new XChaCha20Poly1305KeyManager().getKeyType();

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

    private AeadConfig() {
    }

    @Deprecated
    public static void init() {
        register();
    }

    public static void register() {
        MacConfig.register();
        AesCtrHmacAeadKeyManager.register(true);
        AesEaxKeyManager.register(true);
        AesGcmKeyManager.register(true);
        AesGcmSivKeyManager.register(true);
        ChaCha20Poly1305KeyManager.register(true);
        KmsAeadKeyManager.register(true);
        KmsEnvelopeAeadKeyManager.register(true);
        XChaCha20Poly1305KeyManager.register(true);
        AeadWrapper.register();
    }

    @Deprecated
    public static void registerStandardKeyTypes() {
        register();
    }
}
