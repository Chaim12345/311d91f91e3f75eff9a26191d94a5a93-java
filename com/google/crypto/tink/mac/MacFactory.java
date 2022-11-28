package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
@Deprecated
/* loaded from: classes2.dex */
public final class MacFactory {
    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle) {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle, KeyManager<Mac> keyManager) {
        Registry.registerPrimitiveWrapper(new MacWrapper());
        return (Mac) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, Mac.class));
    }
}
