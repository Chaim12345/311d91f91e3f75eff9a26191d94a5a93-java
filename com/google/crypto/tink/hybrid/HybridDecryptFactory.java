package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Registry;
@Deprecated
/* loaded from: classes2.dex */
public final class HybridDecryptFactory {
    @Deprecated
    public static HybridDecrypt getPrimitive(KeysetHandle keysetHandle) {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static HybridDecrypt getPrimitive(KeysetHandle keysetHandle, KeyManager<HybridDecrypt> keyManager) {
        Registry.registerPrimitiveWrapper(new HybridDecryptWrapper());
        return (HybridDecrypt) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, HybridDecrypt.class));
    }
}
