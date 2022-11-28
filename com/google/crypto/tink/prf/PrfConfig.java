package com.google.crypto.tink.prf;
/* loaded from: classes2.dex */
public final class PrfConfig {
    public static final String PRF_TYPE_URL = new HkdfPrfKeyManager().getKeyType();

    private PrfConfig() {
    }

    public static void register() {
        AesCmacPrfKeyManager.register(true);
        HkdfPrfKeyManager.register(true);
        HmacPrfKeyManager.register(true);
        PrfSetWrapper.register();
    }
}
