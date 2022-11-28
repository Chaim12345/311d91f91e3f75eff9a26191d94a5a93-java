package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
@Deprecated
/* loaded from: classes2.dex */
public final class PublicKeyVerifyFactory {
    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle) {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle, KeyManager<PublicKeyVerify> keyManager) {
        Registry.registerPrimitiveWrapper(new PublicKeyVerifyWrapper());
        return (PublicKeyVerify) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, PublicKeyVerify.class));
    }
}
