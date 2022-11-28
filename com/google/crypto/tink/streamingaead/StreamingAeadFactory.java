package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
@Deprecated
/* loaded from: classes2.dex */
public final class StreamingAeadFactory {
    public static StreamingAead getPrimitive(KeysetHandle keysetHandle) {
        return getPrimitive(keysetHandle, null);
    }

    public static StreamingAead getPrimitive(KeysetHandle keysetHandle, KeyManager<StreamingAead> keyManager) {
        Registry.registerPrimitiveWrapper(new StreamingAeadWrapper());
        return (StreamingAead) Registry.wrap(Registry.getPrimitives(keysetHandle, keyManager, StreamingAead.class));
    }
}
