package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
@Deprecated
/* loaded from: classes2.dex */
public final class PublicKeySignFactory {
    @Deprecated
    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle) {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle, KeyManager<PublicKeySign> keyManager) {
        Registry.registerPrimitiveWrapper(new PublicKeySignWrapper());
        return (PublicKeySign) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, PublicKeySign.class));
    }
}
